package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.email.entity.EmailRequest;
import com.csaba79coder.littersnap.model.email.service.LitterSnapEmailSenderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmailController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class EmailController {

    /**
     * The litterSnapEmailSenderService field.
     * <p>
     *     This field is used to send emails.
     * </p>
     */
    private final LitterSnapEmailSenderServiceImpl litterSnapEmailSenderService;

    /**
     * This method is used to send a test email.
     * @param emailRequest the email request
     * @return the response entity
     */
    @PostMapping(value = "/admin/email/test",
            produces = "application/json")
    public ResponseEntity sendTestEmail(@RequestBody EmailRequest emailRequest) {
        boolean emailSent = litterSnapEmailSenderService.sendEmailHtml(emailRequest);
        if (emailSent) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}