package com.archive.ifland.dto;

import com.archive.ifland.domain.Like;
import lombok.Data;

@Data
public class LikeDto {

  private Long id;

  private String likeThing;

  public LikeDto(Like like) {
    this.id = like.getId();
    this.likeThing = like.getLike();
  }
}
