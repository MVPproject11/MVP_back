package com.eleven.mvp_back.domain.dto.response.caregiver;

import com.eleven.mvp_back.domain.enums.Gender;

import java.time.LocalTime;
import java.util.List;

public record CaregiverResponse(
        Long id,
        String name,
        Gender gender,
        String phoneNumber,
        String caregiverProfile,
        Boolean ownCar,
        Boolean dementiaTraining,
        Integer minDesireWage,
        Integer maxDesiredWage,
        Integer careerPeriod,
        String mainCareer,
        String introduction,
        LocalTime availableStartTime,
        LocalTime availableEndTime,
        List<CaregiverAvailableDayResponse> availableDays,
        List<CaregiverLocationResponse> locations,
        List<CertificationResponse> certifications
) {
}
