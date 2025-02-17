package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.Elder;
import com.eleven.mvp_back.domain.entity.ElderMealAssist;

public record ElderMealAssistsRequset(
        String mealAssist
) {
    public ElderMealAssist toEntity(Elder elder) {
        return ElderMealAssist.builder()
                .elder(elder)
                .mealServiceName(mealAssist)
                .build();
    }
}
