package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.elder.Elder;
import com.eleven.mvp_back.domain.entity.elder.ElderDailyLivingAssist;
import jakarta.validation.constraints.NotBlank;

public record ElderDailyLivingAssistsRequest(
        @NotBlank(message = "일상 생활 지원 항목은 필수 입력 항목입니다.")
        String dailyLivingAssist
) {
    public ElderDailyLivingAssist toEntity(Elder elder) {
        return ElderDailyLivingAssist.builder()
                .elder(elder)
                .dailyLivingServiceName(dailyLivingAssist)
                .build();
    }
}