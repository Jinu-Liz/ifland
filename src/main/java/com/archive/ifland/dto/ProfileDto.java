package com.archive.ifland.dto;

import com.archive.ifland.domain.*;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class ProfileDto {

  private Long id;

  private String iflandNickName;

  private String ifStartYear;

  private String ifStartMonth;

  private String mbti;

  private String team;

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

  private List<ProfileCommentDto> comments;

  private int likeCount;

  private int viewCount;

  public ProfileDto(Profile profile) {
    this.id = profile.getId();
    this.iflandNickName = profile.getIflandNickName();
    this.ifStartYear = profile.getIfStartYear();
    this.ifStartMonth = profile.getIfStartMonth();
    this.mbti = profile.getMbti();
    this.team = profile.getTeam();
    this.image = StringUtils.hasText(profile.getImage()) ? profile.getImage() : "/images/profile/basic-profile-img.png";
    this.contents = profile.getContents();
    this.instagram = profile.getInstagram();
    this.facebook = profile.getFacebook();
    this.blog = profile.getBlog();
    this.kakaoView = profile.getKakaoView();
    this.openKakao = profile.getOpenKakao();

    List<TagDto> tagDtoList = new ArrayList<>();
    for (Tag tag : profile.getTags()) {
      TagDto tagDto = new TagDto(tag);
      tagDtoList.add(tagDto);
    }

    List<ProfileCommentDto> commentList = new ArrayList<>();
    for (ProfileComment comment : profile.getComments()) {
      ProfileCommentDto commentDto = new ProfileCommentDto(comment);
      commentList.add(commentDto);
    }

    Collections.reverse(commentList);   // List 뒤집어서 최신순 정렬.

    List<LikeDto> likeDtoList = new ArrayList<>();
    StringBuilder likeSb = new StringBuilder();
    for (int i = 0; i < profile.getLikes().size(); i++) {
      Like like = profile.getLikes().get(i);
      LikeDto likeDto = new LikeDto(like);
      likeDtoList.add(likeDto);

      if (i != 0) likeSb.append(", ");
      likeSb.append(like.getLike());
    }

    List<HateDto> hateDtoList = new ArrayList<>();
    StringBuilder hateSb = new StringBuilder();
    for (int i = 0; i < profile.getHates().size(); i++) {
      Hate hate = profile.getHates().get(i);
      HateDto hateDto = new HateDto(hate);
      hateDtoList.add(hateDto);

      if (i != 0) hateSb.append(", ");
      hateSb.append(hate.getHate());
    }

    this.tags = tagDtoList;
    this.likes = likeDtoList;
    this.hates = hateDtoList;
    this.likesToString = likeSb.toString();
    this.hatesToString = hateSb.toString();
    this.comments = commentList;
    this.likeCount = profile.getLikeCount();
    this.viewCount = profile.getViewCount();
  }
}
