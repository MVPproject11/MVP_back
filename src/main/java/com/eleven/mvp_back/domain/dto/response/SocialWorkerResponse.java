package com.eleven.mvp_back.domain.dto.response;

import java.time.LocalDateTime;

public record SocialWorkerResponse(
        Long id,
        String centerName,
        String phoneNumber,
        Boolean ownBathCar,
        String centerAddress,
        String centerGrade,
        Integer operationPeriod,
        String introduction,
        String socialworkerProfile
) {
}
