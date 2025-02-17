package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.Elder;
import com.eleven.mvp_back.domain.entity.ElderMoveAssist;

public record ElderMoveAssistsRequest(
        String moveAssist
) {
    public ElderMoveAssist toEntity(Elder elder) {
        return ElderMoveAssist.builder()
                .elder(elder)
                .moveServiceName(moveAssist)
                .build();
    }
}
