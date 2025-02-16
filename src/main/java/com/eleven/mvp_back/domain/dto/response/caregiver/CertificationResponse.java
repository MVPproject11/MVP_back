package com.eleven.mvp_back.domain.dto.response.caregiver;

public record CertificationResponse(
        String certificationType,
        String certificationGrade,
        String certificationNumber
) {
}
