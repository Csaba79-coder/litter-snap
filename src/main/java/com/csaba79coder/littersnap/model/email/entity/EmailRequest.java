package com.csaba79coder.littersnap.model.email.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class contains the email request entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    /**
     * The email request entity fields.
     * <p>
     *     emailTo: the email address to send the email to
     *     subject: the subject of the email
     *     body: the body of the email
     * </p>
     */
    private String emailTo;
    private String subject;
    private String body;
}
