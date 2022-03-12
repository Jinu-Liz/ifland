package com.archive.ifland.dto;

import com.archive.ifland.domain.Profile;
import lombok.Data;

@Data
public class ProfileDto {

  private Long id;

  private String iflandNickName;

  private String image;

  private String contents;

  private String instagram;

  private String facebook;

  private String blog;

  private int likeCount;

  public ProfileDto(Profile profile) {
    this.id = profile.getId();
    this.iflandNickName = profile.getIflandNickName();
    this.image = profile.getImage();
    this.contents = profile.getContents();
    this.instagram = profile.getSns().getInstagram();
    this.facebook = profile.getSns().getFacebook();
    this.blog = profile.getSns().getBlog();
    this.likeCount = profile.getLikeCount();
  }
}
