package com.eleven.mvp_back.domain.repository;

import com.eleven.mvp_back.domain.entity.matching.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
}
