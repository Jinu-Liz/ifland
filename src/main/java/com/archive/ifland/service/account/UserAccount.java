package com.archive.ifland.service.account;

import com.archive.ifland.domain.Member;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserAccount extends User {

  private Member member;

  public UserAccount(Member member) {
    super(member.getEmail(), member.getPassword(), List.of(new SimpleGrantedAuthority(UserRole.USER.getCode())));
    this.member = member;
  }

}
