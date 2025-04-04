package com.bank.statement.service.impl;

import com.bank.statement.dto.response.StatementResponse;
import com.bank.statement.exception.LLMServiceException;
import com.bank.statement.service.LLMService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements LLMService {

    private final WebClient openAiWebClient;
    private final ObjectMapper objectMapper;

    @Value("classpath:prompts/bank-statement-prompt.txt")
    private Resource statementPromptTemplate;

    @Value("classpath:prompts/password-prompt.txt")
    private Resource passwordPromptTemplate;

    @Value("${openai.api.model}")
    private String model;

    @Override
    public StatementResponse parseStatement(String extractedText) throws LLMServiceException {
        try {
            String prompt = String.format("""
                Analyze this bank statement and return JSON with:
                - customerName (string)
                - email (string or null)
                - accountNumber (string)
                - openingBalance (number)
                - closingBalance (number)
                - statementPeriod (object with startDate and endDate)
                
                Statement Content:
                %s
                """, extractedText);

            Map<String, Object> requestBody = Map.of(
                    "model", model,
                    "messages", List.of(Map.of(
                            "role", "user",
                            "content", prompt
                    )),
                    "temperature", 0.2,
                    "response_format", Map.of("type", "json_object")
            );

            String responseJson = openAiWebClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            String content = objectMapper.readTree(responseJson)
                    .path("choices").get(0)
                    .path("message").path("content").asText();

            return objectMapper.readValue(content, StatementResponse.class);

        } catch (Exception e) {
            log.error("OpenAI API call failed", e);
            throw new LLMServiceException("Failed to parse bank statement", e);
        }
    }

    @Override
    public String generatePassword(String firstName, String dob, String accountType) throws LLMServiceException {
        try {
            String prompt = String.format("""
                Generate a secure password with:
                1. First character of %s (capitalized)
                2. Last 2 digits of %s
                3. Special character from @$!%%*?&
                4. First 2 letters of %s (lowercase)
                5. Day from %s
                
                Return ONLY the password without any additional text.
                """, firstName, dob.split("-")[0], accountType, dob);

            Map<String, Object> requestBody = Map.of(
                    "model", model,
                    "messages", List.of(Map.of(
                            "role", "user",
                            "content", prompt
                    )),
                    "temperature", 0.1,
                    "max_tokens", 20
            );

            String responseJson = openAiWebClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return objectMapper.readTree(responseJson)
                    .path("choices").get(0)
                    .path("message").path("content").asText()
                    .trim();

        } catch (Exception e) {
            log.error("Password generation failed", e);
            throw new LLMServiceException("Failed to generate password", e);
        }
    }
}