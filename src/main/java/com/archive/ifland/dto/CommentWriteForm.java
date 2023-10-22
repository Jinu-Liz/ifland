package com.archive.ifland.dto;

import lombok.Data;

@Data
public class CommentWriteForm {

  private Long contentId;

  private Long memberId;

  private String contents;

}
