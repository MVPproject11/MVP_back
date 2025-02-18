package com.eleven.mvp_back.domain.repository.caregiver;

import com.eleven.mvp_back.domain.entity.caregiver.Caregiver;
import com.eleven.mvp_back.domain.entity.caregiver.CaregiverLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaregiverLocationRepository extends JpaRepository<CaregiverLocation, Long> {
}
