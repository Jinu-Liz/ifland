package com.archive.ifland.service;

import com.archive.ifland.dto.ProfileCommentResponse;
import com.archive.ifland.dto.ProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {

  List<ProfileDto> selectProfiles();

  List<ProfileDto> selectProfiles(int count);

  void plusLikeCount(Long id);

  void minusLikeCount(Long id);

  void plusViewCount(Long id);

  ProfileCommentResponse writeComment(String contents);

  Page<ProfileDto> iflanderList(Pageable pageable, String keyword);

  ProfileDto findIflander(Long id);

}
