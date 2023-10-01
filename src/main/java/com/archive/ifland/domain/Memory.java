package com.archive.ifland.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Memory extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "memory_id")
  private Long id;

  @OneToMany(mappedBy = "memory")
  private List<MemoryComment> comments = new ArrayList<>();

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  private String image;

  private String title;

  private String content;

  private Long likeCount;

  private Long view;

}
