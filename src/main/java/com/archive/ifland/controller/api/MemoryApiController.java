package com.archive.ifland.controller.api;

import com.archive.ifland.dto.CommentWriteForm;
import com.archive.ifland.dto.MemoryDto;
import com.archive.ifland.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memorize")
public class MemoryApiController {

  private final MemoryService memoryService;

  @PostMapping("/comment")
  public void writeComment(@RequestBody CommentWriteForm commentData) {
    memoryService.writeComment(commentData);
  }

  @GetMapping("/detail/{id}")
  public MemoryDto detail(@PathVariable Long id) {

    return memoryService.selMemory(id);
  }
}
