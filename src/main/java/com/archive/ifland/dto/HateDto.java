package com.archive.ifland.dto;

import com.archive.ifland.domain.Hate;
import lombok.Data;

@Data
public class HateDto {

  private Long id;

  private String hateThing;

  public HateDto(Hate hate) {
    this.id = hate.getId();
    this.hateThing = hate.getHate();
  }
}
