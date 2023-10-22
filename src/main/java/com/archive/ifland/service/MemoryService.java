package com.archive.ifland.service;

import com.archive.ifland.domain.*;
import com.archive.ifland.dto.CommentWriteForm;
import com.archive.ifland.dto.MemoryDto;
import com.archive.ifland.repository.MemberRepository;
import com.archive.ifland.repository.MemoryCommentRepository;
import com.archive.ifland.repository.MemoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static com.archive.ifland.domain.QMember.member;
import static com.archive.ifland.domain.QMemoryComment.memoryComment;

@RequiredArgsConstructor
@Service
public class MemoryService {

  private final MemoryRepository memoryRepository;

  private final MemberRepository memberRepository;

  private final MemoryCommentRepository memoryCommentRepository;

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

  @Transactional
  public void writeComment(CommentWriteForm commentData) {
    String contents = commentData.getContents();
    if (StringUtils.hasText(contents)) {
      Memory memory = memoryRepository.findById(commentData.getContentId()).orElseThrow();
      Member member = memberRepository.findById(commentData.getMemberId()).orElseThrow();
      MemoryComment newProfileComment = new MemoryComment(memory, member, contents);

      memoryCommentRepository.save(newProfileComment);
    }
  }
}
