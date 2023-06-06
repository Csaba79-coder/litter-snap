package com.csaba79coder.littersnap.exception;

import com.csaba79coder.littersnap.value.ErrorCode;
import jakarta.mail.SendFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.csaba79coder.littersnap.value.ErrorCode.LS_001;
import static com.csaba79coder.littersnap.value.ErrorCode.LS_002;
import static com.csaba79coder.littersnap.value.ErrorCode.LS_003;
import static com.csaba79coder.littersnap.value.ErrorCode.LS_004;
import static com.csaba79coder.littersnap.value.ErrorCode.LS_005;

/**
 * This class contains the exception handlers for the application.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handles NoSuchElementException.
     *
     * @param ex the exception to handle
     * @return the response entity with the error message
     */
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(LS_001, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles InputMismatchException.
     *
     * @param ex the exception to handle
     * @return the response entity with the error message
     */
    @ExceptionHandler(value = {InputMismatchException.class})
    public ResponseEntity<Object> handleInvalidInputException(InputMismatchException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(LS_002, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MyValidationException with a single string message.
     *
     * @param ex the exception to handle
     * @return the response entity with the error message
     */
    @ExceptionHandler(value = {MyValidationException.class})
    public ResponseEntity<Object> handleInvalidPasswordOrUsernameException(MyValidationException ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>(responseBodyWithMessage(LS_003, message), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles SendFailedException.
     *
     * @param ex the exception to handle
     * @return the response entity with the error message
     */
    @ExceptionHandler(value = {SendFailedException.class})
    public ResponseEntity<Object> handleSendFailedException(SendFailedException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(LS_004, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IllegalArgumentException.
     *
     * @param ex the exception to handle
     * @return the response entity with the error message
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(LS_005, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Creates the response body with the error message.
     *
     * @param code the error code
     * @param message the error message
     * @return the response body with the error message
     */
    private String responseBodyWithMessage(ErrorCode code, String message) {
        return Map.of(code, message).toString();
    }
}
