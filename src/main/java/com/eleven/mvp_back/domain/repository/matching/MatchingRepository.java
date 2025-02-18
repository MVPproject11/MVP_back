package com.eleven.mvp_back.domain.repository.matching;

import com.eleven.mvp_back.domain.entity.matching.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    List<Matching> findAllByCaregiver_Id(Long caregiverId);
    List<Matching> findAllByJobpostSocialworkerElderSocialWorkerId(Long socialworkerId);
}
