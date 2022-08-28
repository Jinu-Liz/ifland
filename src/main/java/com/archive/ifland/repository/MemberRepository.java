package com.archive.ifland.repository;

import com.archive.ifland.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  boolean existsByEmail(String email);

  boolean existsByIflandNickName(String nickName);

}
