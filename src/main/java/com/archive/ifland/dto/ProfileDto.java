package com.archive.ifland.dto;

import com.archive.ifland.domain.Hate;
import com.archive.ifland.domain.Like;
import com.archive.ifland.domain.Profile;
import com.archive.ifland.domain.Tag;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfileDto {

  private Long id;

  private String iflandNickName;

  private String image;

  private String contents;

  private String instagram;

  private String facebook;

  private String blog;

  private List<Like> likes;

  private List<Hate> hates;

  private List<TagDto> tags;

  private int likeCount;

  private int viewCount;

  public ProfileDto(Profile profile) {
    this.id = profile.getId();
    this.iflandNickName = profile.getIflandNickName();
    this.image = StringUtils.hasText(profile.getImage()) ? profile.getImage() : "/images/profile/basic-profile-img.png";
    this.contents = profile.getContents();
    this.instagram = profile.getSns().getInstagram();
    this.facebook = profile.getSns().getFacebook();
    this.blog = profile.getSns().getBlog();
    this.likes = profile.getLikes();
    this.hates = profile.getHates();

    List<TagDto> tagDtoList = new ArrayList<>();
    for (Tag tag : profile.getTags()) {
      TagDto tagDto = new TagDto();
      String rgba = StringUtils.hasText(tag.getRgba()) ? tag.getRgba() : "255, 255, 255, 0.2";
      tagDto.setId(tag.getId());
      tagDto.setTag(tag.getTag());
      tagDto.setRgba(rgba);
      tagDtoList.add(tagDto);
    }

    this.tags = tagDtoList;
    this.likeCount = profile.getLikeCount();
    this.viewCount = profile.getViewCount();
  }
}
