package com.archive.ifland.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@DynamicInsert    // insert시 null컬럼 제외
@Entity
public class VerifyEmail {

  @Id @GeneratedValue(strategy = IDENTITY)
  @Column(name = "email_id")
  private Long id;

  private String authCode;

  private String isVerified;

  public VerifyEmail(String authCode) {
    this.authCode = authCode;
  }

  public void confirmVerified() {
    isVerified = "Y";
  }
}
