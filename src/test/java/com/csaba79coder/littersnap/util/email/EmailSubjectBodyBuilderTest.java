package com.csaba79coder.littersnap.util.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for EmailSubjectBodyBuilder class.
 */
class EmailSubjectBodyBuilderTest {

    /**
     * Test case for buildEmailSubject method.
     */
    @Test
    @DisplayName("testBuildEmail")
    public void testBuildEmail() {
        String url = "https://example.com";
        String firstName = "John";

        // Test case 1: RegistrationController
        String expectedEmail1 = "Dear John!<br><br>" +
                "<p>Welcome to the LitterSnap!</p>" +
                "<br>" +
                "<p>Thank you for registering on our website!</p>" +
                "<p>Click the link below for confirming your registration:</p>" +
                "<p><a href=\"" + url + "\">Registration confirmation</a></p>" +
                "<p>Your link is valid for 24 hours, after it is expired,</p>" +
                "<p>with clicking on the link you can ask for password reset!</p>" +
                "<br>" +
                "<p>For further information please contact us!</p>" +
                "<p>E-mail: info.littersnap@gmail.com</p>" +
                "<br>" +
                "<p>Ignore this email in case you have not made the registration request.";
        String actualEmail1 = EmailSubjectBodyBuilder.buildEmail(url, "RegistrationController", firstName);
        assertEquals(expectedEmail1, actualEmail1);

        // Test case 2: ForgottenPasswordController
        String expectedEmail2 = "<p>Hello John, </p>" +
                "<p>You have requested to reset your password.</p>" +
                "<p>Click the link below to change your password (time limit: 10 minutes):</p>" +
                "<p><a href=\"" + url + "\">Change my password</a></p>" +
                "<br>" +
                "<p>Ignore this email if you do remember your password, " +
                "or you have not made the request.</p>";
        String actualEmail2 = EmailSubjectBodyBuilder.buildEmail(url, "ForgottenPasswordController", firstName);
        assertEquals(expectedEmail2, actualEmail2);

        // Test case 3: UserController
        String expectedEmail3 = "Dear John!<br><br>" +
                "<p>Welcome to the LitterSnap!</p>" +
                "<br>" +
                "<p>Thank you for registering on our website!</p>" +
                "<p>Manual registration is done regarding your request!</p>" +
                "<p>Click the link below for set your password and activate your profile and you will be able to login!</p>" +
                "<p><a href=\"" + url + "\">Complete your manual registration</a></p>" +
                "<p>With clicking on the link, and follow password reset procedure you are able to use your profile!</p>" +
                "<br>" +
                "<p>For further information please contact us!</p>" +
                "<p>E-mail: info.littersnap@gmail.com</p>" +
                "<br>" +
                "<p>Ignore this email in case you have not asked for manual registration request.";
        String actualEmail3 = EmailSubjectBodyBuilder.buildEmail(url, "UserController", firstName);
        assertEquals(expectedEmail3, actualEmail3);

        // Test case 4: Invalid className
        assertNull(EmailSubjectBodyBuilder.buildEmail(url, "InvalidClass", firstName));
    }

    /**
     * Test case for createEmailSubject method.
     */
    @Test
    @DisplayName("testCreateEmailSubject")
    public void testCreateEmailSubject() {
        // Test case 1: RegistrationController
        String expectedSubject1 = "LitterSnap - Please confirm your registration on the link bellow";
        String actualSubject1 = EmailSubjectBodyBuilder.createEmailSubject("RegistrationController");
        assertEquals(expectedSubject1, actualSubject1);

        // Test case 2: ForgottenPasswordController
        String expectedSubject2 = "LitterSnap - Forgotten Password";
        String actualSubject2 = EmailSubjectBodyBuilder.createEmailSubject("ForgottenPasswordController");
        assertEquals(expectedSubject2, actualSubject2);

        // Test case 3: UserController
        String expectedSubject3 = "LitterSnap - Please create your new password to complete your manual registration";
        String actualSubject3 = EmailSubjectBodyBuilder.createEmailSubject("UserController");
        assertEquals(expectedSubject3, actualSubject3);

        // Test case 4: Invalid className
        assertNull(EmailSubjectBodyBuilder.createEmailSubject("InvalidClass"));
    }
}