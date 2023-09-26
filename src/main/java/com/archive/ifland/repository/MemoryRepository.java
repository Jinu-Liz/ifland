package com.archive.ifland.repository;


import com.archive.ifland.domain.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
}
