package com.archive.ifland.dto;

import com.archive.ifland.domain.Memory;
import com.archive.ifland.domain.MemoryComment;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class MemoryDto {

  private Long id;

  private String iflandNickName;

  private List<MemoryCommentDto> comments;

  private String image;

  private String title;

  private String contents;

  private long likeCount;

  private long viewCount;

  public MemoryDto(Memory memory) {

    this.id = memory.getId();
    this.iflandNickName = memory.getMember().getIflandNickName();
    this.title = memory.getTitle();
    this.contents = memory.getContent();
    this.image = "/images/memory/" + memory.getImage();
    this.likeCount = memory.getLikeCount();
    this.viewCount = memory.getView();

    List<MemoryCommentDto> commentList = new ArrayList<>();
    for (MemoryComment comment : memory.getComments()) {
      MemoryCommentDto commentDto = new MemoryCommentDto(comment);
      commentList.add(commentDto);
    }

    Collections.reverse(commentList);   // List 뒤집어서 최신순 정렬.

    this.comments = commentList;
  }
}
