package com.example.demo.service;

import com.example.demo.model.Request.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;

@Service
public class EmailService {
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(EmailDetails emailDetails, String templateName) {
        try {
            Context context = new Context();
            context.setVariable("name", emailDetails.getReceiver().getUsername());
            context.setVariable("email", emailDetails.getReceiver().getEmail());
            context.setVariable("password", emailDetails.getPassword()); // Add password to the context
            context.setVariable("button", "Go to home page");
            context.setVariable("link", emailDetails.getLink());

            // Process the template with the updated context
            String template = templateEngine.process(templateName, context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("phuocnntse182664@fpt.edu.vn");
            mimeMessageHelper.setTo(emailDetails.getReceiver().getEmail());
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            mimeMessageHelper.setText(template, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }

}
