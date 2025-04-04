package com.bank.statement.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Utility for generating secure passwords
 */
@Component
public class PasswordGenerator {

    private static final String SPECIAL_CHARS = "@$!%*?&";

    public String generateSecurePassword(String firstName, String dob, String accountType) {
        LocalDate birthDate = LocalDate.parse(dob, DateTimeFormatter.ISO_DATE);

        char specialChar = SPECIAL_CHARS.charAt(
                (firstName.length() + accountType.length()) % SPECIAL_CHARS.length());

        return String.format("%s%d%s%c%s",
                firstName.substring(0, 1).toUpperCase(),
                birthDate.getYear() % 100,
                accountType.substring(0, 1).toLowerCase(),
                specialChar,
                birthDate.getDayOfMonth());
    }
}
