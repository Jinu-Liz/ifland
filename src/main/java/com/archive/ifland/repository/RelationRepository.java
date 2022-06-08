package com.archive.ifland.repository;

import com.archive.ifland.domain.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<Relation, Long> {
}
