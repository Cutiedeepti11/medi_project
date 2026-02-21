package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static void validateNotEmpty(String value, String fieldName)
            throws InvalidDataException {

        if (value == null || value.trim().isEmpty()) {
            throw new InvalidDataException(fieldName + " cannot be empty");
        }
    }

    public static void validateEmail(String email)
            throws InvalidDataException {

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidDataException("Invalid email format");
        }
    }

    public static void validatePhone(String phone)
            throws InvalidDataException {

        if (phone == null || phone.length() != 10) {
            throw new InvalidDataException("Phone number must be 10 digits");
        }
    }

    public static void validateAmount(double amount)
            throws InvalidDataException {

        if (amount < 0) {
            throw new InvalidDataException("Amount cannot be negative");
        }
    }
}
