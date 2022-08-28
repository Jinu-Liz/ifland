package com.archive.ifland.service.email;

import com.archive.ifland.config.EmailHandler;
import com.archive.ifland.dto.MemberDto;
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

  public void sendMailWithFiles(MemberDto member) throws MessagingException, IOException {
    EmailHandler mailHandler = new EmailHandler(mailSender);

    mailHandler.setTo(member.getEmail());
    mailHandler.setSubject("[인증] 이프랜더 위키 인증메일 입니다.");

    StringBuilder html = new StringBuilder();
    html.append("<div style='font-family:arial; width:100%; text-align:center; border:4px solid #888; border-radius:5px; width:500px; max-width:500px; margin:auto; padding:20px'>");
    html.append(" <p>이프랜더 위키 회원가입을 환영합니다, <strong>").append(member.getIflandNickName()).append("</strong> 님!</p>");
    html.append(" <p><a href='").append(member.getVerifiedLink()).append("' target='_blank'>여기를 눌러 인증을 완료해 주세요</a></p>");
    html.append("</div>");
    mailHandler.setText(html.toString(), true);
    mailHandler.send();
  }

}
