package com.archive.ifland.controller.api;

import com.archive.ifland.dto.CommentWriteForm;
import com.archive.ifland.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memorize")
public class MemoryApiController {

  private final MemoryService memoryService;

  @PostMapping("/comment")
  public void writeComment(@RequestBody CommentWriteForm commentData) {
    memoryService.writeComment(commentData);
  }
}
