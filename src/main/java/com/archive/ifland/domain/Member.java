package com.archive.ifland.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "member_id")
  private Long id;

  private String name;

  private String iflandNickName;

  private String password;

  public Member(String name, String iflandNickName, String password) {
    this.name = name;
    this.iflandNickName = iflandNickName;
    this.password = password;
  }

}
