package com.eleven.mvp_back.domain.dto.request.caregiver;

import com.eleven.mvp_back.domain.entity.caregiver.Caregiver;
import com.eleven.mvp_back.domain.entity.caregiver.CaregiverAvailableDay;
import com.eleven.mvp_back.domain.enums.Weekday;
import jakarta.validation.constraints.NotNull;

public record CaregiverAvailableDayRequest(
        @NotNull(message = "가능 요일은 필수 입력값입니다.")
        Weekday availableDay
) {
        public CaregiverAvailableDay toEntity(Caregiver caregiver) {
                return CaregiverAvailableDay.builder()
                        .caregiver(caregiver)
                        .availableDay(availableDay)
                        .build();
        }
}
