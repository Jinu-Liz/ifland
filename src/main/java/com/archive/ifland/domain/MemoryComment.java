package com.archive.ifland.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class MemoryComment extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "comment_id")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "memory_id")
  private Memory memory;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  private String contents;

  private Long likeCount;

  public MemoryComment(Memory memory, Member member, String contents) {
    this.memory = memory;
    this.member = member;
    this.contents = contents;
  }

}
