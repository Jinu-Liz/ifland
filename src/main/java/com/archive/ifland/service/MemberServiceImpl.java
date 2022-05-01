package com.archive.ifland.service;

import com.archive.ifland.CommonUtils;
import com.archive.ifland.controller.MemberForm;
import com.archive.ifland.domain.Member;
import com.archive.ifland.domain.VerifyEmail;
import com.archive.ifland.dto.MemberDto;
import com.archive.ifland.repository.MemberRepository;
import com.archive.ifland.repository.VerifyEmailRepository;
import com.archive.ifland.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final VerifyEmailRepository veRepository;
  private final EmailService emailService;
  private final CommonUtils commonUtils;

  @Transactional
  public void createMember(MemberForm memberForm) {

    try {

      VerifyEmail verifyEmail = new VerifyEmail(commonUtils.makeRandomCode());
      veRepository.save(verifyEmail);

      Member member = new Member(memberForm, verifyEmail);
      memberRepository.save(member);

      MemberDto memberDto = new MemberDto(member);

      emailService.sendMailWithFiles(memberDto);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
