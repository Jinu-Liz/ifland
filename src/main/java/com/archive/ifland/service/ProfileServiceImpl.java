package com.archive.ifland.service;

import com.archive.ifland.domain.Member;
import com.archive.ifland.domain.Profile;
import com.archive.ifland.domain.ProfileComment;
import com.archive.ifland.dto.ProfileCommentResponse;
import com.archive.ifland.dto.ProfileDto;
import com.archive.ifland.repository.MemberRepository;
import com.archive.ifland.repository.ProfileCommentRepository;
import com.archive.ifland.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;
  private final ProfileCommentRepository profileCommentRepository;
  private final MemberRepository memberRepository;

  @Override
  public List<ProfileDto> selectProfiles() {
    List<Profile> profiles = profileRepository.findAll();
    List<ProfileDto> resultList = profiles.stream()
      .map(ProfileDto::new)
      .collect(Collectors.toList());
    return resultList;
  }

  @Override
  public void plusLikeCount(Long id) {
    Optional<Profile> profileOptional = profileRepository.findById(id);
    profileOptional.ifPresent(profile -> {
      profile.plusLikeCount();
      profileRepository.save(profile);
    });
  }

  @Override
  public void minusLikeCount(Long id) {
    Optional<Profile> profileOptional = profileRepository.findById(id);
    profileOptional.ifPresent(profile -> {
      profile.minusLikeCount();
      profileRepository.save(profile);
    });
  }

  @Override
  public ProfileCommentResponse writeComment(String contents) {
    ProfileCommentResponse result = null;
    Profile profile = profileRepository.findAll().get(0);
    Member member = memberRepository.findAll().get(0);
    if (StringUtils.hasText(contents)) {
      ProfileComment newProfileComment = new ProfileComment(profile, member, contents);
      Long id = profileCommentRepository.save(newProfileComment).getId();

      ProfileComment profileComment = profileCommentRepository.getById(id);
      result = new ProfileCommentResponse(profileComment);
    }

    return result;
  }
}
