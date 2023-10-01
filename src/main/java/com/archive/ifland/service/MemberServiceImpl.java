package com.archive.ifland.service;

import com.archive.ifland.CommonUtils;
import com.archive.ifland.controller.MemberForm;
import com.archive.ifland.domain.Member;
import com.archive.ifland.domain.VerifyEmail;
import com.archive.ifland.dto.MemberDto;
import com.archive.ifland.dto.response.AuthResponse;
import com.archive.ifland.repository.MemberRepository;
import com.archive.ifland.repository.VerifyEmailRepository;
import com.archive.ifland.service.account.UserAccount;
import com.archive.ifland.service.account.UserRole;
import com.archive.ifland.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

  private final MemberRepository memberRepository;

  private final VerifyEmailRepository veRepository;

  private final EmailService emailService;

  @Transactional
  public void createMember(MemberForm memberForm) {

    try {

      VerifyEmail verifyEmail = new VerifyEmail(CommonUtils.makeRandomCode());
      veRepository.save(verifyEmail);

      Member member = Member.builder()
        .email(memberForm.getEmail())
        .auth(UserRole.USER.name())
        .password(passwordEncoder().encode(memberForm.getPassword()))
        .iflandNickName(memberForm.getIflandNickName())
        .verifyEmail(verifyEmail)
        .build();

      memberRepository.save(member);

      MemberDto memberDto = new MemberDto(member);

      emailService.sendMailWithFiles(memberDto);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public AuthResponse sendEmailForNewPassword(String account) {

    AuthResponse authResponse = new AuthResponse();
    try {

      boolean existAccount = memberRepository.existsByEmail(account);

      if (existAccount) {
        String authCode = CommonUtils.makeRandomCode();
        VerifyEmail verifyEmail = new VerifyEmail(authCode);
        veRepository.save(verifyEmail);

        Member member = memberRepository.findByEmail(account);
        String passwordLink = "http://localhost:9090/mail/auth/new-password?user=" + member.getId() + "&authCode=" + authCode;

        MemberDto memberDto = new MemberDto(member);
        memberDto.setNewPasswordLink(passwordLink);
        emailService.sendMailForNewPassword(memberDto);

        authResponse.setStatus("SUCCESS");
        authResponse.setSuccess("비밀번호 변경 메일이 전송되었습니다.");
        return authResponse;
      }

      authResponse.setStatus("FAIL");
      authResponse.setError("해당 계정이 존재하지 않습니다.");
      return authResponse;
    } catch (Exception e) {
      e.printStackTrace();
      authResponse.setStatus("FAIL");
      authResponse.setError("에러가 발생하였습니다. 관리자에게 문의하세요.");
      return authResponse;
    }
  }

  public AuthResponse changePassword(Long id, String password) {
    AuthResponse authResponse = new AuthResponse();

    try {
      Optional<Member> member = memberRepository.findById(id);
      member.ifPresent(m -> {
        m.changePassword(passwordEncoder().encode(password));
        memberRepository.save(m);
      });

      authResponse.setStatus("SUCCESS");
      authResponse.setSuccess("비밀번호 변경이 완료되었습니다.");
      return authResponse;
    } catch (Exception e) {
      e.printStackTrace();
      authResponse.setStatus("FAIL");
      authResponse.setError("에러가 발생하였습니다. 관리자에게 문의하세요.");
      return authResponse;
    }
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Member member = memberRepository.findByEmail(email);
    if (ObjectUtils.isEmpty(member)) throw new UsernameNotFoundException(email);

    return new UserAccount(member);
  }

  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
