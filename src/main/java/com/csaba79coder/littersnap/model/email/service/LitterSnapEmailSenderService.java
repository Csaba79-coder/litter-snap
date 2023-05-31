package com.csaba79coder.littersnap.model.email.service;

import com.csaba79coder.littersnap.model.email.entity.EmailRequest;
import org.springframework.stereotype.Service;

@Service
public interface LitterSnapEmailSenderService {

    boolean sendEmailHtml(EmailRequest emailRequest);
}
