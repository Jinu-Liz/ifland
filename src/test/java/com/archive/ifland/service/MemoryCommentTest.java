package com.archive.ifland.service;

import com.archive.ifland.dto.MemoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemoryCommentTest {

  @Autowired
  MemoryService memoryService;

  @Test
  void selMemory() {
    Long id = 1L;
    MemoryDto memory = memoryService.selMemory(id);
    System.out.println(memory);
  }
}
