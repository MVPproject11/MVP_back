package com.eleven.mvp_back.domain.dto.response.matching;

import com.eleven.mvp_back.domain.entity.matching.Matching;
import com.eleven.mvp_back.domain.enums.MatchingStatus;
import com.eleven.mvp_back.domain.enums.ProgressStatus;

import java.time.LocalDate;

public record SocialworkerMatchingListResponse(
        Long matchingId,
        Long caregiverId,
        String caregiverName,
        MatchingStatus matchingStatus,
        ProgressStatus progressStatus,
        LocalDate requestDate,
        LocalDate responseDate
) {
    public static SocialworkerMatchingListResponse fromEntity(Matching matching) {
        return new SocialworkerMatchingListResponse(
                matching.getId(),
                matching.getCaregiver().getId(),
                matching.getCaregiver().getName(),
                matching.getMatchingStatus(),
                matching.getProgressStatus(),
                matching.getRequestDate(),
                matching.getResponseDate()
        );
    }
}
