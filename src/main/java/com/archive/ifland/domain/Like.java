package com.archive.ifland.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Getter
@Entity
@Table(name = "profile_like")
public class Like {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "like_id")
  private Long id;

  @Column(name = "like_thing")
  private String like;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "profile_id")
  private Profile profile;
}
