package com.eleven.mvp_back.domain.repository.caregiver;

import com.eleven.mvp_back.domain.entity.caregiver.Caregiver;
import com.eleven.mvp_back.domain.entity.caregiver.CaregiverAvailableDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaregiverAvailableDayRepository extends JpaRepository<CaregiverAvailableDay, Long> {
    void deleteByCaregiver(Caregiver caregiver);
}
