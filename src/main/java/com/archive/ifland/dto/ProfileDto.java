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

  private String kakaoView;

  private String openKakao;

  private List<LikeDto> likes;

  private String likesToString;

  private List<HateDto> hates;

  private String hatesToString;

  private List<TagDto> tags;

  private int likeCount;

  private int viewCount;

  public ProfileDto(Profile profile) {
    this.id = profile.getId();
    this.iflandNickName = profile.getIflandNickName();
    this.image = StringUtils.hasText(profile.getImage()) ? profile.getImage() : "/images/profile/basic-profile-img.png";
    this.contents = profile.getContents();
    this.instagram = profile.getInstagram();
    this.facebook = profile.getFacebook();
    this.blog = profile.getBlog();
    this.kakaoView = profile.getKakaoView();
    this.openKakao = profile.getOpenKakao();

    List<TagDto> tagDtoList = new ArrayList<>();
    for (Tag tag : profile.getTags()) {
      TagDto tagDto = new TagDto();
      String rgba = StringUtils.hasText(tag.getRgba()) ? tag.getRgba() : "255, 255, 255, 0.2";
      tagDto.setId(tag.getId());
      tagDto.setTag(tag.getTag());
      tagDto.setRgba(rgba);
      tagDtoList.add(tagDto);
    }

    List<LikeDto> likeDtoList = new ArrayList<>();
    StringBuilder likeSb = new StringBuilder();
    for (int i = 0; i < profile.getLikes().size(); i++) {
      Like like = profile.getLikes().get(i);
      LikeDto likeDto = new LikeDto();
      likeDto.setId(like.getId());
      likeDto.setLikeThing(like.getLike());
      likeDtoList.add(likeDto);

      if (i != 0) likeSb.append(", ");
      likeSb.append(like.getLike());
    }

    List<HateDto> hateDtoList = new ArrayList<>();
    StringBuilder hateSb = new StringBuilder();
    for (int i = 0; i < profile.getHates().size(); i++) {
      Hate hate = profile.getHates().get(i);
      HateDto hateDto = new HateDto();
      hateDto.setId(hate.getId());
      hateDto.setHateThing(hate.getHate());
      hateDtoList.add(hateDto);

      if (i != 0) hateSb.append(", ");
      hateSb.append(hate.getHate());
    }

    this.tags = tagDtoList;
    this.likes = likeDtoList;
    this.hates = hateDtoList;
    this.likesToString = likeSb.toString();
    this.hatesToString = hateSb.toString();
    this.likeCount = profile.getLikeCount();
    this.viewCount = profile.getViewCount();
  }
}
