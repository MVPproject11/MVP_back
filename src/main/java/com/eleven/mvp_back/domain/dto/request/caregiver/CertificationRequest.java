package com.eleven.mvp_back.domain.dto.request.caregiver;

import com.eleven.mvp_back.domain.entity.caregiver.Caregiver;
import com.eleven.mvp_back.domain.entity.caregiver.Certification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CertificationRequest(
        @NotBlank(message = "자격증 타입은 필수 입력값입니다.")
        @Size(max = 20, message = "자격증 타입은 최대 20자까지 입력 가능합니다.")
        String certificationType,

        @NotBlank(message = "자격증 등급은 필수 입력값입니다.")
        @Size(max = 20, message = "자격증 등급은 최대 20자까지 입력 가능합니다.")
        String certificationGrade,

        @NotBlank(message = "자격증 번호는 필수 입력값입니다.")
        @Size(max = 20, message = "자격증 번호는 최대 20자까지 입력 가능합니다.")
        String certificationNumber
) {
        public Certification toEntity(Caregiver caregiver) {
                return Certification.builder()
                        .caregiver(caregiver)
                        .certificationType(certificationType)
                        .certificationGrade(certificationGrade)
                        .certificationNumber(certificationNumber)
                        .build();
        }
}
