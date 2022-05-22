package com.archive.ifland.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity @Getter
public class Tag extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "tag_id")
  private Long id;

  private String tag;

  private String rgba;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "profile_id")
  private Profile profile;
}
