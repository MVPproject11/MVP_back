package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.matching.ProgressStatusRequest;
import com.eleven.mvp_back.domain.dto.response.matching.*;

import java.util.List;

public interface MatchingService {
    List<CaregiverMatchingListResponse> getMatchingsCaregiver(Long caregiverId);

    CaregiverMatchingDetailResponse getCaregiverMatchingDetail(Long caregiverId, Long matchingId);

    MatchingResponse responseMatchingToSocialworker(Long caregiverId, Long matchingId, ProgressStatusRequest request);

    List<SocialworkerMatchingListResponse> getAllMatchingsSocialworker(Long socialworkerId);

    SocialworkerMatchingDetailResponse getMatchingDetailSocialworker(Long socialworkerId, Long matchingId);
}
