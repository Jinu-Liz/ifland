package com.archive.ifland.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Getter
@Entity
public class Like {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "like_id")
  private Long id;

  private String like;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "profile_id")
  private Profile profile;
}
