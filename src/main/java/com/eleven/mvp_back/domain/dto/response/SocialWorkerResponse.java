package com.eleven.mvp_back.domain.dto.response;

import com.eleven.mvp_back.domain.entity.SocialWorker;

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
    public static SocialWorkerResponse fromEntity(SocialWorker socialWorker) {
        return new SocialWorkerResponse(
                socialWorker.getId(), 
                socialWorker.getCenterName(),
                socialWorker.getPhoneNumber(),
                socialWorker.isOwnBathCar(),
                socialWorker.getCenterAddress(),
                socialWorker.getCenterGrade(),
                socialWorker.getOperationPeriod(),
                socialWorker.getIntroduction(),
                socialWorker.getSocialworkerProfile()
        );
    }
}
