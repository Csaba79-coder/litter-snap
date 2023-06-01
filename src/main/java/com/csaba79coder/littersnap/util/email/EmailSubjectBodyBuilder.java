package com.csaba79coder.littersnap.util.email;

public class EmailSubjectBodyBuilder {

    public static String buildEmail(String url, String className, String firstName) {
        return switch (className) {
            case "RegistrationController" -> "Dear " + firstName + "!<br><br>" +
                    "<p>Welcome to the LitterSnap!</p>"
                    + "<br>"
                    + "<p>Thank you for registering on our website!</p>"
                    + "<p>Click the link below for confirming your registration:</p>"
                    + "<p><a href=\"" + url + "\">Registration confirmation</a></p>"
                    + "<p>Your link is valid for 24 hours, after it is expired,</p>"
                    + "<p>with clicking on the link you can ask for password reset!</p>"
                    + "<br>"
                    + "<p>For further information please contact us!</p>"
                    + "<p>E-mail: info.littersnap@gmail.com</p>"
                    + "<br>"
                    + "<p>Ignore this email in case you have not made the registration request.";
            case "ForgottenPasswordController" -> "<p>Hello " + firstName + ", </p>"
                    + "<p>You have requested to reset your password.</p>"
                    + "<p>Click the link below to change your password (time limit: 10 minutes):</p>"
                    + "<p><a href=\"" + url + "\">Change my password</a></p>"
                    + "<br>"
                    + "<p>Ignore this email if you do remember your password, "
                    + "or you have not made the request.</p>";
            case "UserController" -> "Dear " + firstName + "!<br><br>" +
                    "<p>Welcome to the LitterSnap!</p>"
                    + "<br>"
                    + "<p>Thank you for registering on our website!</p>"
                    + "<p>Manual registration is done regarding your request!</p>"
                    + "<p>Click the link below for set your password and activate your profile and you will be able to login!</p>"
                    + "<p><a href=\"" + url + "\">Complete your manual registration</a></p>"
                    + "<p>With clicking on the link, and follow password reset procedure you are able to use your profile!</p>"
                    + "<br>"
                    + "<p>For further information please contact us!</p>"
                    + "<p>E-mail: info.littersnap@gmail.com</p>"
                    + "<br>"
                    + "<p>Ignore this email in case you have not asked for manual registration request.";
            default -> null;
        };
    }

    public static String createEmailSubject(String className) {
        return switch (className) {
            case "RegistrationController" ->
                    "LitterSnap - Please confirm your registration on the link bellow";
            case "ForgottenPasswordController" -> "LitterSnap - Forgotten Password";
            case "UserController" ->
                    "LitterSnap - Please create your new password to complete your manual registration";
            default -> null;
        };
    }
}
