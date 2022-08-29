package com.archive.ifland.service.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

  USER("ROLE_USER", "일반 회원"),
  ADMIN("ROLE_ADMIN", "관리자");

  private final String code;
  private final String name;
}
