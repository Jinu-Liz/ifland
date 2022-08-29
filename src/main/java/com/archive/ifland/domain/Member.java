package com.archive.ifland.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "member_id")
  private Long id;

  private String email;

  private String iflandNickName;

  private String password;

  private String auth;

  @OneToOne(fetch = LAZY)
  @JoinColumn(name = "email_id")
  private VerifyEmail verifyEmail;

  public void setVerified(boolean verified) {
    if (verified) this.verifyEmail.confirmVerified();
  }

}
