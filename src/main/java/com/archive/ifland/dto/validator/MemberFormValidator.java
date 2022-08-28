package com.archive.ifland.dto.validator;

import com.archive.ifland.controller.MemberForm;
import com.archive.ifland.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MemberFormValidator implements Validator {

  private final MemberRepository memberRepository;

  @Override
  public boolean supports(Class<?> clazz) {

    return clazz.isAssignableFrom(MemberForm.class);  // 어떤 타입의 인스턴스를 검증할 것인가?
  }

  @Override
  public void validate(Object target, Errors errors) {
    MemberForm memberForm = (MemberForm) target;

    if (memberRepository.existsByEmail(memberForm.getEmail())) {
      errors.rejectValue("email", "invalid email", "이미 가입된 이메일입니다.");
    }

    if (memberRepository.existsByIflandNickName(memberForm.getIflandNickName())) {
      errors.rejectValue("iflandNickName", "invalid iflandNickName", "이미 사용중인 닉네임입니다.");
    }

  }
}
