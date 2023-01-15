package com.archive.ifland.dto;

import com.archive.ifland.domain.Member;
import lombok.Data;

@Data
public class MemberDto {

  private String email;

  private String iflandNickName;

  private String verifiedLink;

  private String newPasswordLink;

  public MemberDto(Member member) {
    this.email = member.getEmail();
    this.iflandNickName = member.getIflandNickName();
    this.verifiedLink = "http://localhost:9090/mail/auth/confirm?user=" + member.getId() + "&authCode=" + member.getVerifyEmail().getAuthCode();
    this.newPasswordLink = "http://localhost:9090/mail/auth/new-password?user=" + member.getId() + "&authCode=" + member.getVerifyEmail().getAuthCode();
  }
}
