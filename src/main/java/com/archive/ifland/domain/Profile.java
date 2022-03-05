package com.archive.ifland.domain;

import com.archive.ifland.controller.ProfileForm;
import com.archive.ifland.exception.NotEnoughCountException;
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

  private String iflandNickName;

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

  public Profile(ProfileForm profileForm) {
    this.iflandNickName = profileForm.getIflandNickName();
    this.image = profileForm.getImage();
    this.contents = profileForm.getContents();
    this.likes = profileForm.getLikes();
    this.hates = profileForm.getHates();

    SNS sns = new SNS();
    sns.setInstagram(profileForm.getInstagram());
    sns.setFacebook(profileForm.getFacebook());
    sns.setBlog(profileForm.getBlog());

    this.sns = sns;
    this.tags = profileForm.getTags();
  }

  public Profile() {}

  public void plusLikeCount() {
    this.likeCount = this.likeCount + 1;
  }

  public void minusLikeCount() {
    int resultCnt = this.likeCount - 1;
    if (resultCnt < 0) throw new NotEnoughCountException("좋아요 수가 0보다 작음");
    this.likeCount = resultCnt;
  }
}
