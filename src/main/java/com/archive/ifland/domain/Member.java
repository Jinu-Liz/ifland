package com.archive.ifland.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private Long id;

  private String name;

  private String ifrandNickName;

  private String password;

  public Member(String name, String ifrandNickName, String password) {
    this.name = name;
    this.ifrandNickName = ifrandNickName;
    this.password = password;
  }

}
