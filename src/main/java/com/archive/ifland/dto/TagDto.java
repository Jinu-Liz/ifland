package com.archive.ifland.dto;

import com.archive.ifland.domain.Tag;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class TagDto {

  private Long id;

  private String tag;

  private String rgba;

  public TagDto(Tag tag) {
    this.id = tag.getId();
    this.tag = tag.getTag();
    this.rgba = StringUtils.hasText(tag.getRgba()) ? tag.getRgba() : "255, 255, 255, 0.2";
  }
}
