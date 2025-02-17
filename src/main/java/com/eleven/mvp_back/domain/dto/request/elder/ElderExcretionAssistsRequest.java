package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.Elder;
import com.eleven.mvp_back.domain.entity.ElderExcretionAssist;

public record ElderExcretionAssistsRequest(
        String excretionAssist
) {
    public ElderExcretionAssist toEntity(Elder elder) {
        return ElderExcretionAssist.builder()
                .elder(elder)
                .excretionServiceName(excretionAssist)
                .build();
    }
}
