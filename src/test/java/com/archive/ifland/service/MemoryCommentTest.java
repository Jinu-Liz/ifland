package com.archive.ifland.service;

import com.archive.ifland.domain.Memory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemoryCommentTest {

  @Autowired
  MemoryService memoryService;

  @Test
  void selMemory() {
    Memory memory = memoryService.selMemory(1L);
    System.out.println(memory);
  }
}
