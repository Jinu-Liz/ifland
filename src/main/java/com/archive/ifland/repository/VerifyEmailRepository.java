package com.archive.ifland.repository;

import com.archive.ifland.domain.VerifyEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerifyEmailRepository extends JpaRepository<VerifyEmail, Long> {
  Optional<VerifyEmail> findByAuthCode(String auth);

}
