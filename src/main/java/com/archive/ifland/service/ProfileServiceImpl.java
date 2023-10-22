package com.archive.ifland.service;

import com.archive.ifland.domain.Member;
import com.archive.ifland.domain.Profile;
import com.archive.ifland.domain.ProfileComment;
import com.archive.ifland.dto.CommentWriteForm;
import com.archive.ifland.dto.ProfileDto;
import com.archive.ifland.exception.NotEnoughCountException;
import com.archive.ifland.repository.MemberRepository;
import com.archive.ifland.repository.ProfileCommentRepository;
import com.archive.ifland.repository.ProfileRepository;
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

import static com.archive.ifland.domain.QHate.hate1;
import static com.archive.ifland.domain.QLike.like1;
import static com.archive.ifland.domain.QProfile.profile;
import static com.archive.ifland.domain.QProfileComment.profileComment;
import static com.archive.ifland.domain.QRelation.relation;
import static com.archive.ifland.domain.QTag.tag1;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;
  private final ProfileCommentRepository profileCommentRepository;
  private final MemberRepository memberRepository;
  private final JPAQueryFactory queryFactory;

  @Override
  public List<ProfileDto> selectProfiles() {
    return selectProfiles(0);
  }

  @Override
  public List<ProfileDto> selectProfiles(int count) {
    List<Profile> profiles;
    if (count <= 0) {
      profiles = queryFactory
        .selectFrom(profile)
        .leftJoin(profile.tags, tag1)
        .fetchJoin()
        .distinct()
        .fetch();
    } else {
      profiles = queryFactory
        .selectFrom(profile)
        .leftJoin(profile.tags, tag1)
        .orderBy(profile.createdDate.desc())
        .fetchJoin()
        .distinct()
        .limit(count)
        .fetch();
    }

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
    profileOptional.ifPresent(pf -> {

      queryFactory
        .update(profile)
        .set(profile.likeCount, (pf.getLikeCount() + 1))
        .where(profile.id.eq(pf.getId()))
        .execute();
    });
  }

  @Override
  public void minusLikeCount(Long id) {
    Optional<Profile> profileOptional = profileRepository.findById(id);
    profileOptional.ifPresent(pf -> {
      if (pf.getLikeCount() <= 0) throw new NotEnoughCountException("좋아요 수가 0보다 작음");

      queryFactory
        .update(profile)
        .set(profile.likeCount, (pf.getLikeCount() - 1))
        .where(profile.id.eq(pf.getId()))
        .execute();
    });
  }

  @Override
  public void plusViewCount(Long id) {
    Optional<Profile> profileOptional = profileRepository.findById(id);
    profileOptional.ifPresent(pf -> {
      queryFactory
        .update(profile)
          .set(profile.viewCount, (pf.getViewCount() + 1))
          .where(profile.id.eq(pf.getId()))
          .execute();
    });
  }

  @Override
  public void writeComment(CommentWriteForm commentData) {
    String contents = commentData.getContents();
    if (StringUtils.hasText(contents)) {
      Profile profile = profileRepository.findById(commentData.getProfileId()).orElseThrow();
      Member member = memberRepository.findById(commentData.getMemberId()).orElseThrow();
      ProfileComment newProfileComment = new ProfileComment(profile, member, contents);
      profileCommentRepository.save(newProfileComment);
    }
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

      if (result.size() < 2) result.addAll(getRandomProfile(1, id, friendIdList));

      return result;
    } else {
      return getRandomProfile(2, id);
    }
  }

  private BooleanExpression containsKeyword(String keyword) {
    if (!StringUtils.hasText(keyword)) return null;

    return profile.iflandNickName.contains(keyword);
  }

  // 자신을 제외한 1명 추가
  private List<ProfileDto> getRandomProfile(int num, Long id) {
    return getRandomProfile(num, id, new ArrayList<>());
  }

  private List<ProfileDto> getRandomProfile(int num, Long id, List<Long> fList) {
    List<ProfileDto> profileDtoList = selectProfiles();
    Collections.shuffle(profileDtoList);

    return profileDtoList.stream()
      .filter(pfd -> !id.equals(pfd.getId()))
      .filter(pfd -> !fList.contains(pfd.getId()))
      .limit(num)
      .collect(Collectors.toList());
  }
}
