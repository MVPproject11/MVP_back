package com.eleven.mvp_back.domain.dto.response.elder;

import com.eleven.mvp_back.domain.enums.Weekday;

public record ElderCareDaysResponse(
        Weekday dayOfWeek
) {
}
