package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.elder.Elder;
import com.eleven.mvp_back.domain.entity.elder.ElderExcretionAssist;
import jakarta.validation.constraints.NotBlank;

public record ElderExcretionAssistsRequest(
        @NotBlank(message = "배변 지원 항목은 필수 입력 항목입니다.")
        String excretionAssist
) {
    public ElderExcretionAssist toEntity(Elder elder) {
        return ElderExcretionAssist.builder()
                .elder(elder)
                .excretionServiceName(excretionAssist)
                .build();
    }
}
