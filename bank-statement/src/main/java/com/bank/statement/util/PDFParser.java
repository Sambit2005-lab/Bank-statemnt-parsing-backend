package com.bank.statement.util;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility for PDF text extraction
 */
@Component
public class PDFParser {

    public String extractText(MultipartFile file, String password) throws IOException {
        PdfDocumentReaderConfig config = PdfDocumentReaderConfig.builder()
                .withPageExtractedTextFormatter(new ExtractedTextFormatter.Builder()
                        .withNumberOfTopTextLinesToDelete(0)
                        .withNumberOfBottomTextLinesToDelete(0)
                        .build())
                .build();

        PagePdfDocumentReader reader = new PagePdfDocumentReader(file.getResource(), config);
        List<Document> documents = reader.get();

        return documents.stream()
                .map(Document::getText)  // Changed from getContent() to getText()
                .collect(Collectors.joining("\n"));
    }
}
