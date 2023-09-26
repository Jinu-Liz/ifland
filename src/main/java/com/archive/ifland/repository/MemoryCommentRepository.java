package com.archive.ifland.repository;

import com.archive.ifland.domain.MemoryComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryCommentRepository extends JpaRepository<MemoryComment, Long> {
}
