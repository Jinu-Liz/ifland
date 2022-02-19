package com.archive.ifland.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Getter
@Entity
public class Profile extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "profile_id")
  private Long id;

  private String ifland_id;

  private String image;

  private String contents;

  @OneToMany(mappedBy = "profile")
  private List<Like> likes = new ArrayList<>();

  @OneToMany(mappedBy = "profile")
  private List<Hate> hates = new ArrayList<>();

  @Embedded
  private SNS sns;

  private int likeCount;

  @OneToMany(mappedBy = "profile")
  private List<ProfileComment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "profile")
  private List<Tag> tags = new ArrayList<>();

}
