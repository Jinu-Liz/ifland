package com.archive.ifland.service;

import com.archive.ifland.domain.Memory;
import com.archive.ifland.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemoryService {

  private final MemoryRepository memoryRepository;

  @Transactional
  public Memory selMemory(Long memoryId) {
    Memory memory = memoryRepository.findById(memoryId).get();

    return memory;
  }
}
