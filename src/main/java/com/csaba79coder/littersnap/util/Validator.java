package com.csaba79coder.littersnap.util;

import java.util.regex.Pattern;

/**
 * This class contains a method for validating a string against a regular expression.
 */
public class Validator {

    /**
     * Validates the given pattern against the given regular expression.
     *
     * @param pattern      the pattern to validate
     * @param regexPattern the regular expression to validate against
     * @return true if the pattern matches the regular expression, false otherwise
     */
    public static boolean patternMatches(String pattern, String regexPattern) {
        if (pattern == null) {
            return false;
        }
        return Pattern.compile(regexPattern)
                .matcher(pattern)
                .matches();
    }

    /**
     * private constructor to prevent instantiation
     */
    private Validator() {
    }
}