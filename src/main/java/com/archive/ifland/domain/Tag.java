package com.archive.ifland.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity @Getter
public class Tag {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String tag;

  @ManyToOne(fetch = LAZY)
  private Profile profile;
}
