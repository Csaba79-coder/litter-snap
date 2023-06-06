package com.csaba79coder.littersnap.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases for the Validator class.
 */
class ValidatorTest {

    /**
     * This test provide a JUnit test for Validator class.
     * Tests the validateEmail method of the Validator class.
     *
     * <p>Given a valid email address, the method should return true.</p>
     *
     * <p>The test asserts that the method returns true.</p>
     */
    @Test
    @DisplayName("validatorForRegexPatternMatches")
    public void testPatternMatches() {
        // Test case 1: Valid pattern and matching regex pattern
        String pattern1 = "abc";
        String regexPattern1 = ".*";
        assertTrue(Validator.patternMatches(pattern1, regexPattern1));

        // Test case 2: Valid pattern and non-matching regex pattern
        String pattern2 = "abc";
        String regexPattern2 = "\\d+";
        assertFalse(Validator.patternMatches(pattern2, regexPattern2));

        // Test case 3: Null pattern
        String pattern3 = null;
        String regexPattern3 = ".*";
        assertFalse(Validator.patternMatches(pattern3, regexPattern3));
    }
}