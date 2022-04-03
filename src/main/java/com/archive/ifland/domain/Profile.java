package com.archive.ifland.domain;

import com.archive.ifland.controller.ProfileForm;
import com.archive.ifland.exception.NotEnoughCountException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
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

//  @Embedded
//  private SNS sns;

  private String instagram;

  private String facebook;

  private String blog;

  private int likeCount;

  @Column(name = "view")
  private int viewCount;

  @OneToMany(mappedBy = "profile")
  private List<ProfileComment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "profile")
  private List<Tag> tags = new ArrayList<>();

  public Profile(ProfileForm profileForm) {
    iflandNickName = profileForm.getIflandNickName();
    image = profileForm.getImage();
    contents = profileForm.getContents();
    likes = profileForm.getLikes();
    hates = profileForm.getHates();
    instagram = profileForm.getInstagram();
    facebook = profileForm.getFacebook();
    blog = profileForm.getBlog();
    tags = profileForm.getTags();
  }

  public void plusLikeCount() {
    this.likeCount = this.likeCount + 1;
  }

  public void minusLikeCount() {
    int resultCnt = this.likeCount - 1;
    if (resultCnt < 0) throw new NotEnoughCountException("좋아요 수가 0보다 작음");
    this.likeCount = resultCnt;
  }

  public void plusViewCount() {
    this.viewCount = this.viewCount + 1;
  }

}
