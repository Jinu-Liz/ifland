package com.archive.ifland.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity @Getter
public class Tag {

  @Id @GeneratedValue
  private Long id;

  private String tag;

  @ManyToOne(fetch = LAZY)
  private Profile profile;
}
