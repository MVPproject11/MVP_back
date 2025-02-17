package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.Elder;
import com.eleven.mvp_back.domain.entity.ElderCareDays;
import com.eleven.mvp_back.domain.enums.Weekday;
import jakarta.validation.constraints.NotNull;

public record ElderCareDaysRequest(
        @NotNull(message = "요일은 필수입력 항목입니다.")
        Weekday dayOfWeek
) {
    public ElderCareDays toEntity(Elder elder) {
        return ElderCareDays.builder()
                .elder(elder)
                .dayOfWeek(dayOfWeek)
                .build();
    }
}
