package com.archive.ifland.service.email;

import com.archive.ifland.config.EmailHandler;
import com.archive.ifland.dto.email.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender mailSender;

  public void sendMail(EmailDto email) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email.getAddress());
    message.setSubject(email.getTitle());
    message.setText(email.getMessage());

    mailSender.send(message);
  }

  public void sendMailWithFiles(EmailDto email) throws MessagingException, IOException {
    EmailHandler mailHandler = new EmailHandler(mailSender);

    mailHandler.setTo(email.getAddress());
    mailHandler.setSubject(email.getTitle());

    String htmlContent = "<p>" + email.getMessage() + "<p> <img src='cid:google-logo'>";
    mailHandler.setText(htmlContent, true);
    mailHandler.setAttach("test.txt", "static/test.txt");
    mailHandler.setInline("google-logo", "static/google-logo.png");
    mailHandler.send();
  }
}
