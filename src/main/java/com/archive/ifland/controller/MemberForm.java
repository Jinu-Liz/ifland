package com.archive.ifland.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberForm {

  @NotBlank(message = "이메일을 입력해 주세요.")
  @Email(message = "이메일 형식으로 입력해 주세요.")
  private String email;

  @NotBlank(message = "닉네임을 입력해 주세요.")
  private String iflandNickName;

  @NotBlank(message = "패스워드를 입력해 주세요.")
  private String password;

}
