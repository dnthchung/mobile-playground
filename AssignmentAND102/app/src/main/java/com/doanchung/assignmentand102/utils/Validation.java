package com.doanchung.assignmentand102.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    // Regular expression for full name (2 words or more)
    private static final String FULL_NAME_REGEX = "^\\S+\\s+\\S+";

    // Regular expression for email (contains @ and no whitespace)
    private static final String EMAIL_REGEX = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";

    // Regular expression for phone number (starts with 0 and contains 10 digits)
    private static final String PHONE_REGEX = "^0\\d{9}$";

    public static boolean isEmailValid(String email) {
        // Use the provided Android Patterns for email validation
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    public static boolean isFullNameValid(String fullName) {
        Pattern pattern = Pattern.compile(FULL_NAME_REGEX);
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }


}
