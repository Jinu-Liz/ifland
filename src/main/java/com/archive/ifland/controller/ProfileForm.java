package com.archive.ifland.controller;

import com.archive.ifland.domain.Hate;
import com.archive.ifland.domain.Like;
import com.archive.ifland.domain.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ProfileForm {

  @NotEmpty(message = "닉네임은 반드시 입력해야 합니다.")
  private String iflandNickName;

  private String image;

  private List<Like> likes = new ArrayList<>();

  private List<Hate> hates = new ArrayList<>();

  @NotEmpty(message = "내용은 반드시 입력해야 합니다.")
  private String contents;

  private String instagram;

  private String facebook;

  private String blog;

  private String kakaoView;

  private String openKakao;

  private List<Tag> tags = new ArrayList<>();

}
