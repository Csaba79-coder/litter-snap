package com.csaba79coder.littersnap.exception;

import com.csaba79coder.littersnap.value.ErrorCode;
import jakarta.mail.SendFailedException;
import org.modelmapper.ValidationException;
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

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(LS_001, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InputMismatchException.class})
    public ResponseEntity<Object> handleInvalidInputException(InputMismatchException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(LS_002, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleInvalidPasswordOrUsernameException(ValidationException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(LS_003, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SendFailedException.class})
    public ResponseEntity<Object> handleSendFailedException(SendFailedException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(LS_003, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private String responseBodyWithMessage(ErrorCode code, String message) {
        return Map.of(code, message).toString();
    }
}
