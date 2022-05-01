package com.archive.ifland.dto;

import com.archive.ifland.domain.Member;
import lombok.Data;

@Data
public class MemberDto {

  private String email;

  private String iflandNickName;

  private String verifiedLink;

  public MemberDto(Member member) {
    this.email = member.getEmail();
    this.iflandNickName = member.getIflandNickName();
    this.verifiedLink = "http://localhost:9090/api/v1/auth/confirm?user=" + member.getId();
  }
}
