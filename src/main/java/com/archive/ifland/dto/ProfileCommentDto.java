package com.archive.ifland.dto;

import com.archive.ifland.domain.ProfileComment;
import lombok.Data;

@Data
public class ProfileCommentDto {

  private Long commentId;

  private String iflandNickName;

  private String contents;

  private String createdDate;

  public ProfileCommentDto(ProfileComment profileComment) {
    this.commentId = profileComment.getId();
    this.iflandNickName = profileComment.getMember().getIflandNickName();
    this.contents = profileComment.getContents();
    this.createdDate = profileComment.getCreatedDate();
  }

}
