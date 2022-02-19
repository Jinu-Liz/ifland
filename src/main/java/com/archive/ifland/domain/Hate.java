package com.archive.ifland.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Getter
public class Hate {

  @Id
  @GeneratedValue
  @Column(name = "hate_id")
  private Long id;

  private String like;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "profile_id")
  private Profile profile;

}
