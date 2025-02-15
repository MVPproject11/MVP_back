package com.eleven.mvp_back.domain.repository;

import com.eleven.mvp_back.domain.entity.ElderCareDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElderCareDaysRepository extends JpaRepository<ElderCareDays, Long> {
}
