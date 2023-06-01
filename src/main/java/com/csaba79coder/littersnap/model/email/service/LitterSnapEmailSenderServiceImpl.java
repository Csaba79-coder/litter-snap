package com.csaba79coder.littersnap.model.email.service;

import com.csaba79coder.littersnap.model.email.entity.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("email")
@RequiredArgsConstructor
@Slf4j
public class LitterSnapEmailSenderServiceImpl implements LitterSnapEmailSenderService {

    private final JavaMailSender mailSender;

    @Override
    public boolean sendEmailHtml(EmailRequest emailRequest) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

        try {
            message.setFrom("spring.mail.address.sender");
            message.setTo(emailRequest.getEmailTo());
            message.setSubject(emailRequest.getSubject());
            message.setText(emailRequest.getBody(), true);
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            String logMessage = String.format("Error while sending email to " + emailRequest.getEmailTo());
            log.info(logMessage);
            return new MessagingException(logMessage, e).getMessage().contains("Invalid Addresses");
        }
    }
}
