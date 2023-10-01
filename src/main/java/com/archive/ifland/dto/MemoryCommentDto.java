package com.archive.ifland.dto;

import com.archive.ifland.CommonUtils;
import com.archive.ifland.domain.MemoryComment;
import lombok.Data;

@Data
public class MemoryCommentDto {

  private Long commentId;

  private String iflandNickName;

  private String contents;

  private String createdDate;

  private String dayAgo;

  public MemoryCommentDto(MemoryComment memoryComment) {
    this.commentId = memoryComment.getId();
    this.iflandNickName = memoryComment.getMember().getIflandNickName();
    this.contents = memoryComment.getContents();
    this.createdDate = memoryComment.getCreatedDate();
    this.dayAgo = CommonUtils.getTimeAgo(this.createdDate);

  }
}
