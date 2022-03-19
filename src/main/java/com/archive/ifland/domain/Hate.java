package com.archive.ifland.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity @Getter
@Table(name = "profile_hate")
public class Hate {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "hate_id")
  private Long id;

  @Column(name = "hate_thing")
  private String hate;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "profile_id")
  private Profile profile;

}
