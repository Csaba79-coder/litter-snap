package com.csaba79coder.littersnap.model.email.service;

import com.csaba79coder.littersnap.model.email.entity.EmailRequest;
import org.springframework.stereotype.Service;

/**
 * This class contains the litter snap email sender service.
 */
@Service
public interface LitterSnapEmailSenderService {

    /**
     * This method sends an email.
     *
     * @param emailRequest the email request
     * @return true if the email was sent, false otherwise
     */
    boolean sendEmailHtml(EmailRequest emailRequest);
}
