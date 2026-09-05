package com.findmyvehicle.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


    /**
     * Send a simple plain-text email.
     */
    public void sendEmail(String to, String subject, String body) {

        String dateTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"))
                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a"));

        String finalBody = body
                + "\n\n"
                + "----------------------------------------\n"
                + "Date & Time: " + dateTime + " IST\n"
                + "----------------------------------------\n\n"
                + "Regards,\n"
                + "FindMyVehicle Team";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(finalBody);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to send email.", e);
        }
    }


    /**
     * Send an HTML email.
     */
    public void sendHtmlEmail(String to, String subject, String htmlBody) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            mailSender.send(message);

        } catch (MessagingException | MailException e) {
            throw new RuntimeException("Unable to send email.", e);
        }
    }

}
