package com.archive.ifland.service;

import com.archive.ifland.domain.Profile;
import com.archive.ifland.domain.ProfileComment;
import com.archive.ifland.domain.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.archive.ifland.domain.QHate.hate1;
import static com.archive.ifland.domain.QLike.like1;
import static com.archive.ifland.domain.QProfile.profile;
import static com.archive.ifland.domain.QProfileComment.profileComment;
import static com.archive.ifland.domain.QTag.tag1;

@SpringBootTest
@Transactional
class ProfileServiceImplTest {

  @Autowired
  ProfileService profileService;

  @Autowired
  JPAQueryFactory queryFactory;


  @Test
  void selectProfiles() {

    List<Profile> result =
    queryFactory
      .selectFrom(profile)
      .leftJoin(profile.tags, tag1)
      .fetchJoin()
      .distinct()
      .limit(6)
      .fetch();

    System.out.println("size1 = " + result.size());

    queryFactory
      .selectFrom(profile)
      .leftJoin(profile.comments, profileComment)
      .fetchJoin()
      .fetch();

    System.out.println("size2 = " + result.size());

    queryFactory
      .selectFrom(profile)
      .leftJoin(profile.likes, like1)
      .fetchJoin()
      .fetch();

    System.out.println("size3 = " + result.size());

    queryFactory
      .selectFrom(profile)
      .leftJoin(profile.hates, hate1)
      .fetchJoin()
      .fetch();

    System.out.println("size4 = " + result.size());

    for (Profile profile1 : result) {
      System.out.println("profile = " + profile1.getIflandNickName());
      List<Tag> tags = profile1.getTags();
      for (Tag tag : tags) {
        System.out.println("tag = " + tag.getTag());
      }

      List<ProfileComment> comments = profile1.getComments();
      for (ProfileComment comment : comments) {
        System.out.println("comment = " + comment.getContents());
      }
    }
  }
}