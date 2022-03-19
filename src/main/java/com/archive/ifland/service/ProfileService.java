package com.archive.ifland.service;

import com.archive.ifland.dto.ProfileCommentResponse;
import com.archive.ifland.dto.ProfileDto;

import java.util.List;

public interface ProfileService {

  List<ProfileDto> selectProfiles();

  void plusLikeCount(Long id);

  void minusLikeCount(Long id);

  void plusViewCount(Long id);

  ProfileCommentResponse writeComment(String contents);
}
