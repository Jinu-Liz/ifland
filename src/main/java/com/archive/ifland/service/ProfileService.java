package com.archive.ifland.service;

import com.archive.ifland.dto.ProfileCommentResponse;

import java.util.List;

public interface ProfileService {

  List<?> selectProfiles();

  void plusLikeCount(Long id);

  void minusLikeCount(Long id);

  ProfileCommentResponse writeComment(String contents);
}
