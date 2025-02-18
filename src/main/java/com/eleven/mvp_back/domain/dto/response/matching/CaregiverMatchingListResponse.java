package com.eleven.mvp_back.domain.dto.response.matching;

import com.eleven.mvp_back.domain.entity.matching.Matching;
import com.eleven.mvp_back.domain.entity.socialworker.SocialWorker;
import com.eleven.mvp_back.domain.enums.MatchingStatus;
import com.eleven.mvp_back.domain.enums.ProgressStatus;

import java.time.LocalTime;

public record CaregiverMatchingListResponse(
        Long matchingId,
        String centerName,
        LocalTime postStartTime,
        LocalTime postEndTime,
        String managerEmail,
        String managerPhone,
        MatchingStatus matchingStatus,
        ProgressStatus progressStatus
) {
    public static CaregiverMatchingListResponse fromEntity(Matching matching) {
        SocialWorker socialWorker = matching.getJobpost()
                .getSocialworkerElder().getSocialWorker();

        return new CaregiverMatchingListResponse(
                matching.getId(),
                socialWorker.getCenterName(),
                matching.getJobpost().getPostStartTime(),
                matching.getJobpost().getPostEndTime(),
                matching.getJobpost().getManagerEmail(),
                matching.getJobpost().getManagerPhone(),
                matching.getMatchingStatus(),
                matching.getProgressStatus()
        );
    }
}
