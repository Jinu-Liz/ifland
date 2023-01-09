package com.archive.ifland.controller.api;

import com.archive.ifland.domain.Member;
import com.archive.ifland.exception.AuthException;
import com.archive.ifland.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailAuthController {

  private final MemberRepository memberRepository;

  @GetMapping("/auth/confirm")
  public String mailConfirm(@RequestParam(value = "user") Long id,
                            @RequestParam(value = "authCode") String authCode) {

    Optional<Member> member = memberRepository.findById(id);
    member.ifPresent(m -> {
      if (!m.getVerifyEmail().getAuthCode().equals(authCode)) throw new AuthException("인증 실패");
      m.setVerified(true);
      memberRepository.save(m);
    });

    return "auth/redirect";
  }

  @GetMapping("/auth/verified")
  public String verified() {
    return "auth/verify";
  }

  @GetMapping("/auth/verifying")
  public String verifying() {
    return "auth/verifying";
  }
}
