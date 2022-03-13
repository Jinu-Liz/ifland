package com.archive.ifland.domain;

import com.archive.ifland.controller.MemberForm;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
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

  public Member(MemberForm memberForm) {
    this.name = memberForm.getName();
    this.iflandNickName = memberForm.getIflandNickName();
    this.password = memberForm.getPassword();
  }

}
