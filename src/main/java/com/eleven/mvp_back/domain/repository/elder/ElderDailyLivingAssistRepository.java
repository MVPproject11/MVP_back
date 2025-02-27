package com.eleven.mvp_back.domain.repository.elder;

import com.eleven.mvp_back.domain.entity.elder.ElderDailyLivingAssist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElderDailyLivingAssistRepository extends JpaRepository<ElderDailyLivingAssist, Long> {
}
