package com.eleven.mvp_back.domain.dto.request.caregiver;

import com.eleven.mvp_back.domain.entity.Caregiver;
import com.eleven.mvp_back.domain.entity.CaregiverLocation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CaregiverLocationRequest(
        @NotBlank(message = "도시는 필수 입력값입니다.")
        @Size(max = 10, message = "도시는 최대 20자까지 입력 가능합니다.")
        String city,

        @NotBlank(message = "구는 필수 입력값입니다.")
        @Size(max = 10, message = "구는 최대 10자까지 입력 가능합니다.")
        String district,

        @NotBlank(message = "동은 필수 입력값입니다.")
        @Size(max = 10, message = "동은 최대 10자까지 입력 가능합니다.")
        String dong
) {
        public CaregiverLocation toEntity(Caregiver caregiver) {
                return CaregiverLocation.builder()
                        .caregiver(caregiver)
                        .city(city)
                        .district(district)
                        .dong(dong)
                        .build();
        }
}
