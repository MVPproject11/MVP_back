package com.eleven.mvp_back.domain.dto.request.caregiver;

import com.eleven.mvp_back.domain.enums.Weekday;
import jakarta.validation.constraints.NotNull;

public record CaregiverAvailableDayRequest(
        @NotNull(message = "가능 요일은 필수 입력값입니다.")
        Weekday availableDay
) {
}
