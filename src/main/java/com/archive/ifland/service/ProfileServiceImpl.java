package com.archive.ifland.service;

import com.archive.ifland.domain.*;
import com.archive.ifland.dto.ProfileCommentResponse;
import com.archive.ifland.dto.ProfileDto;
import com.archive.ifland.repository.MemberRepository;
import com.archive.ifland.repository.ProfileCommentRepository;
import com.archive.ifland.repository.ProfileRepository;
import com.archive.ifland.repository.RelationRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.archive.ifland.domain.QHate.*;
import static com.archive.ifland.domain.QLike.*;
import static com.archive.ifland.domain.QProfile.*;
import static com.archive.ifland.domain.QProfileComment.*;
import static com.archive.ifland.domain.QRelation.relation;
import static com.archive.ifland.domain.QTag.*;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;
  private final ProfileCommentRepository profileCommentRepository;
  private final MemberRepository memberRepository;

  private final RelationRepository relationRepository;
  private final JPAQueryFactory queryFactory;

  @Override
  public List<ProfileDto> selectProfiles() {
//    List<Profile> profiles = profileRepository.findAll();
    List<Profile> profiles =
      queryFactory
        .selectFrom(profile)
        .leftJoin(profile.tags, tag1)
        .fetchJoin()
        .distinct()
        .fetch();

      queryFactory
        .selectFrom(profile)
        .leftJoin(profile.comments, profileComment)
        .fetchJoin()
        .fetch();

      queryFactory
        .selectFrom(profile)
        .leftJoin(profile.likes, like1)
        .fetchJoin()
        .fetch();

      queryFactory
        .selectFrom(profile)
        .leftJoin(profile.hates, hate1)
        .fetchJoin()
        .fetch();

    List<ProfileDto> resultList = profiles.stream()
      .map(ProfileDto::new)
      .collect(Collectors.toList());
    return resultList;
  }

  @Override
  public List<ProfileDto> selectProfiles(int count) {
//    List<Profile> profiles = profileRepository.findAll().subList(0, count);
    List<Profile> profiles =
      queryFactory
        .selectFrom(profile)
        .leftJoin(profile.tags, tag1)
        .orderBy(profile.createdDate.desc())
        .fetchJoin()
        .distinct()
        .limit(count)
        .fetch();

      queryFactory
        .selectFrom(profile)
        .leftJoin(profile.comments, profileComment)
        .fetchJoin()
        .fetch();

      queryFactory
        .selectFrom(profile)
        .leftJoin(profile.likes, like1)
        .fetchJoin()
        .fetch();

      queryFactory
        .selectFrom(profile)
        .leftJoin(profile.hates, hate1)
        .fetchJoin()
        .fetch();

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
  public void plusViewCount(Long id) {
    Optional<Profile> profileOptional = profileRepository.findById(id);
    profileOptional.ifPresent(profile -> {
      profile.plusViewCount();
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

  @Override
  public Page<ProfileDto> iflanderList(Pageable pageable, String keyword) {
    List<Profile> result = queryFactory
      .selectFrom(profile)
      .leftJoin(profile.tags, tag1)
      .fetchJoin()
      .where(containsKeyword(keyword))
      .distinct()
      .offset(pageable.getOffset())
      .limit(pageable.getPageSize())
      .fetch();

    List<Long> totalCount = queryFactory
      .select(profile.count())
      .from(profile)
      .where(containsKeyword(keyword))
      .fetch();

    List<ProfileDto> resultDto = result.stream().map(ProfileDto::new).collect(Collectors.toList());

    return new PageImpl<>(resultDto, pageable, totalCount.get(0));
  }

  @Override
  public ProfileDto findIflander(Long id) {
    Optional<Profile> optional = profileRepository.findById(id);
    plusViewCount(id);

    return optional.map(ProfileDto::new).orElseThrow();
  }

  @Override
  public List<ProfileDto> getRecommendList(Long id) {
    List<ProfileDto> result = new ArrayList<>();

    List<Long> friendIdList =
      queryFactory
      .select(relation.friendId)
      .from(relation)
      .where(relation.profileId.eq(id))
      .limit(2)
      .fetch();

    if (!friendIdList.isEmpty()) {
      List<Profile> profileList =
        queryFactory
        .selectFrom(profile)
        .where(profile.id.in(friendIdList))
        .fetch();

      for (Profile pf : profileList) {
        ProfileDto profileDto = new ProfileDto(pf);
        result.add(profileDto);
      }

    } else {
      List<ProfileDto> profileDtoList = selectProfiles();
      Collections.shuffle(profileDtoList);
      return profileDtoList.subList(0, 2);
    }

    return result;
  }

  private BooleanExpression containsKeyword(String keyword) {
    if (!StringUtils.hasText(keyword)) return null;

    return profile.iflandNickName.contains(keyword);
  }
}
