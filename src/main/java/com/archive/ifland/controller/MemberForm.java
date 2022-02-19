package com.archive.ifland.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

  @NotEmpty(message = "이름은 반드시 입력해야 합니다.")
  private String name;

  private String iflandNickName;

  @NotEmpty(message = "패스워드는 반드시 입력해야 합니다.")
  private String password;

}
