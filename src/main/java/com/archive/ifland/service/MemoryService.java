package com.archive.ifland.service;

import com.archive.ifland.domain.Memory;
import com.archive.ifland.domain.QMemory;
import com.archive.ifland.dto.MemoryDto;
import com.archive.ifland.repository.MemoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.archive.ifland.domain.QMember.member;
import static com.archive.ifland.domain.QMemoryComment.memoryComment;

@RequiredArgsConstructor
@Service
public class MemoryService {

  private final MemoryRepository memoryRepository;

  private final JPAQueryFactory queryFactory;

  @Transactional
  public MemoryDto selMemory(Long memoryId) {

    Memory memory = queryFactory
      .selectFrom(QMemory.memory)
      .where(QMemory.memory.id.eq(memoryId))
      .leftJoin(QMemory.memory.comments, memoryComment)
      .fetchJoin()
      .leftJoin(QMemory.memory.member, member)
      .fetchJoin()
      .fetchOne();

    assert memory != null;

    return new MemoryDto(memory);
  }
}
