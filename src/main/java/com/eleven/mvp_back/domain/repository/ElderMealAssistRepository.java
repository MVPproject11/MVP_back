package com.eleven.mvp_back.domain.repository;

import com.eleven.mvp_back.domain.entity.ElderMealAssist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElderMealAssistRepository extends JpaRepository<ElderMealAssist, Long> {
}
