package com.csaba79coder.littersnap.exception;

import jakarta.mail.SendFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains the unit tests for the ControllerExceptionHandler class.
 */
class ControllerExceptionHandlerTest {

    /**
     * The ControllerExceptionHandler instance to test.
     */
    @InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test No Such Element Exception.
     * Creates a response body with the given error code and message.
     *
     * errorCode: the error code to use
     * message: the message to use
     * @return the response body
     */
    @Test
    @DisplayName("Test handleNoSuchElementException")
    public void testHandleNoSuchElementException() {
        NoSuchElementException ex = new NoSuchElementException("Element not found");

        ResponseEntity<Object> response = controllerExceptionHandler.handleNoSuchElementException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("{LS_001=Element not found}", response.getBody());
    }

    /**
     * Test Invalid Input Exception.
     * Creates a response body with the given error code and message.
     *
     * errorCode: the error code to use
     * message: the message to use
     * @return the response body
     */
    @Test
    @DisplayName("Test handleInvalidInputException")
    public void testHandleInvalidInputException() {
        InputMismatchException ex = new InputMismatchException("Invalid input");

        ResponseEntity<Object> response = controllerExceptionHandler.handleInvalidInputException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{LS_002=Invalid input}", response.getBody());
    }

    /**
     * Test Invalid Password or Username Exception. Own exception created for that.
     * Creates a response body with the given error code and message.
     *
     * errorCode: the error code to use
     * message: the message to use
     * @return the response body
     */
    @Test
    @DisplayName("Test handleInvalidPasswordOrUsernameException")
    public void testHandleInvalidPasswordOrUsernameException() {
        MyValidationException ex = new MyValidationException("Invalid password or username");

        ResponseEntity<Object> response = controllerExceptionHandler.handleInvalidPasswordOrUsernameException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{LS_003=Invalid password or username}", response.getBody());
    }

    /**
     * Test Send Failed Exception for email smtp mail service.
     * Creates a response body with the given error code and message.
     *
     * errorCode: the error code to use
     * message: the message to use
     * @return the response body
     */
    @Test
    @DisplayName("Test handleSendFailedException")
    public void testHandleSendFailedException() {
        SendFailedException ex = new SendFailedException("Email send failed");

        ResponseEntity<Object> response = controllerExceptionHandler.handleSendFailedException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{LS_004=Email send failed}", response.getBody());
    }
    /**
     * Test Illegal Argument Exception.
     * Creates a response body with the given error code and message.
     *
     * errorCode: the error code to use
     * message: the message to use
     * @return the response body
     */
    @Test
    @DisplayName("Test handleIllegalArgumentException")
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Illegal argument");

        ResponseEntity<Object> response = controllerExceptionHandler.handleIllegalArgumentException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("{LS_005=Illegal argument}", response.getBody());
    }
}