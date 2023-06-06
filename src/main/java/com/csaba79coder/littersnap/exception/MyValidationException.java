package com.csaba79coder.littersnap.exception;

/**
 * This class contains a custom exception for validation errors.
 */
public class MyValidationException extends RuntimeException {

    /**
     * Constructor for MyValidationException.
     *
     * @param message the message to be displayed
     * checking email and password if it is valid.
     */
    public MyValidationException(String message) {
        super(message);
    }
}