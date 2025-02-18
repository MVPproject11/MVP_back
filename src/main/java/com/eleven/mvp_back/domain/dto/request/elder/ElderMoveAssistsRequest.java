package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.elder.Elder;
import com.eleven.mvp_back.domain.entity.elder.ElderMoveAssist;
import jakarta.validation.constraints.NotBlank;

public record ElderMoveAssistsRequest(
        @NotBlank(message = "이동 지원 항목은 필수 입력 항목입니다.")
        String moveAssist
) {
    public ElderMoveAssist toEntity(Elder elder) {
        return ElderMoveAssist.builder()
                .elder(elder)
                .moveServiceName(moveAssist)
                .build();
    }
}
