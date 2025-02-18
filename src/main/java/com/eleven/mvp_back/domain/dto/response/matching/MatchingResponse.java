package com.eleven.mvp_back.domain.dto.response.matching;

import com.eleven.mvp_back.domain.entity.matching.Matching;
import com.eleven.mvp_back.domain.enums.MatchingStatus;
import com.eleven.mvp_back.domain.enums.ProgressStatus;

import java.time.LocalTime;

public record MatchingResponse(
        Long matchingId,
        MatchingStatus matchingStatus,
        ProgressStatus progressStatus,
        LocalTime requestDate,
        LocalTime responseDate,
        String message
) {
    public static MatchingResponse fromEntity(Matching matching, String msg) {
        return new MatchingResponse(
                matching.getId(),
                matching.getMatchingStatus(),
                matching.getProgressStatus(),
                matching.getRequestDate(),
                matching.getResponseDate(),
                msg
        );
    }

    public MatchingResponse fromEntity(Matching matching) {
        return fromEntity(matching, null);
    }
}
