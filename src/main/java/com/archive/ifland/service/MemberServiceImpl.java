package com.archive.ifland.service;

import com.archive.ifland.CommonUtils;
import com.archive.ifland.controller.MemberForm;
import com.archive.ifland.domain.Member;
import com.archive.ifland.domain.VerifyEmail;
import com.archive.ifland.dto.MemberDto;
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

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

  private final MemberRepository memberRepository;
  private final VerifyEmailRepository veRepository;
  private final EmailService emailService;
  private final CommonUtils commonUtils;

  @Transactional
  public void createMember(MemberForm memberForm) {

    try {

      VerifyEmail verifyEmail = new VerifyEmail(commonUtils.makeRandomCode());
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

  public void sendEmailForNewPassword(String account) {

    try {

      boolean existAccount = memberRepository.existsByEmail(account);

      if (existAccount) {
        VerifyEmail verifyEmail = new VerifyEmail(commonUtils.makeRandomCode());
        veRepository.save(verifyEmail);

        Member member = memberRepository.findByEmail(account);
        MemberDto memberDto = new MemberDto(member);

        emailService.sendMailForNewPassword(memberDto);

      }

    } catch (Exception e) {
      e.printStackTrace();
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
