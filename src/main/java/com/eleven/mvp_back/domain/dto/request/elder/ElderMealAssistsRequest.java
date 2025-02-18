package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.elder.Elder;
import com.eleven.mvp_back.domain.entity.elder.ElderMealAssist;
import jakarta.validation.constraints.NotBlank;

public record ElderMealAssistsRequest(
        @NotBlank(message = "식사 지원 항목은 필수 입력 항목입니다.")
        String mealAssist
) {
    public ElderMealAssist toEntity(Elder elder) {
        return ElderMealAssist.builder()
                .elder(elder)
                .mealServiceName(mealAssist)
                .build();
    }
}
