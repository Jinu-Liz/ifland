package com.archive.ifland.service;

import com.archive.ifland.dto.CommentWriteForm;
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

  void writeComment(CommentWriteForm contents);

  Page<ProfileDto> iflanderList(Pageable pageable, String keyword);

  ProfileDto findIflander(Long id);

  List<ProfileDto> getRecommendList(Long id);

}
