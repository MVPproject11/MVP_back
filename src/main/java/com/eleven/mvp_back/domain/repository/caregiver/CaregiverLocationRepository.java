package com.eleven.mvp_back.domain.repository.caregiver;

import com.eleven.mvp_back.domain.entity.CaregiverLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaregiverLocationRepository extends JpaRepository<CaregiverLocation, Long> {
}
