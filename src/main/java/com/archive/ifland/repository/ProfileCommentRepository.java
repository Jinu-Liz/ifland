package com.archive.ifland.repository;

import com.archive.ifland.domain.ProfileComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileCommentRepository extends JpaRepository<ProfileComment, Long> {
}
