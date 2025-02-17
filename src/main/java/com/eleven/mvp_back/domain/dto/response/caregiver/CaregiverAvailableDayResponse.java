package com.eleven.mvp_back.domain.dto.response.caregiver;

import com.eleven.mvp_back.domain.enums.Weekday;

public record CaregiverAvailableDayResponse(
        Weekday availableDay
) {
}
