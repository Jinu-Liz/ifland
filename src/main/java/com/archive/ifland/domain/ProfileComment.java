package com.archive.ifland.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity @Getter
public class ProfileComment extends BaseTimeEntity {

  @Id @GeneratedValue(strategy = IDENTITY)
  @Column(name = "comment_id")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "profile_id")
  private Profile profile;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  private String contents;

  public ProfileComment(Profile profile, Member member, String contents) {
    this.profile = profile;
    this.member = member;
    this.contents = contents;
  }

  protected ProfileComment() {

  }
}
