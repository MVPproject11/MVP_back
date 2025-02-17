package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.Elder;
import com.eleven.mvp_back.domain.entity.ElderDailyLivingAssist;

public record ElderDailyLivingAssistsRequest(
        String dailyLivingAssist
) {
    public ElderDailyLivingAssist toEntity(Elder elder) {
        return ElderDailyLivingAssist.builder()
                .elder(elder)
                .dailyLivingServiceName(dailyLivingAssist)
                .build();
    }
}