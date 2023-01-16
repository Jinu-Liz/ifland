package com.archive.ifland.controller.api;

import com.archive.ifland.domain.Member;
import com.archive.ifland.domain.VerifyEmail;
import com.archive.ifland.exception.AuthException;
import com.archive.ifland.repository.MemberRepository;
import com.archive.ifland.repository.VerifyEmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailAuthController {

  private final MemberRepository memberRepository;

  private final VerifyEmailRepository verifyEmailRepository;

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

  @GetMapping("/auth/new-password")
  public String newPassword(@RequestParam(value = "user") Long id,
                            @RequestParam(value = "authCode") String authCode,
                            Model model) {

    Optional<VerifyEmail> verify = verifyEmailRepository.findByAuthCode(authCode);
    if (verify.isEmpty()) return "auth/forbidden";

    verify.ifPresent(
      v -> {
        v.confirmVerified();
        verifyEmailRepository.save(v);
      });

    Optional<Member> member = memberRepository.findById(id);
    if (member.isPresent()) {
      model.addAttribute("memberId", id);
      return "auth/change-password";
    } else {
      return "auth/forbidden";
    }
  }

  @GetMapping("/auth/verified")
  public String verified() {
    return "auth/verify";
  }

  @GetMapping("/auth/verifying")
  public String verifying() {
    return "auth/verifying";
  }

  @GetMapping("/auth/change-password")
  public String changePassword() {
    return "auth/change-password";
  }

}
