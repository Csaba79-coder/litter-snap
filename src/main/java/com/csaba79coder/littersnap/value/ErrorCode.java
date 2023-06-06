package com.csaba79coder.littersnap.value;

/**
 * This enum contains the error codes used in the application.
 */
public enum ErrorCode {

    /**
     * Error codes for user related errors.
     * <p>
     *     LS_001: NoSuchElementException
     *     LS_002: InputMismatchException
     *     LS_003: ValidationException (password or email)
     *     LS_004: SendFailedException (email)
     *     LS_005: IllegalArgumentException
     */
    LS_001, LS_002, LS_003, LS_004, LS_005
}
