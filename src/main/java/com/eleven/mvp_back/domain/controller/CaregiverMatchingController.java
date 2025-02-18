package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.dto.request.matching.ProgressStatusRequest;
import com.eleven.mvp_back.domain.dto.response.matching.CaregiverMatchingDetailResponse;
import com.eleven.mvp_back.domain.dto.response.matching.CaregiverMatchingListResponse;
import com.eleven.mvp_back.domain.dto.response.matching.MatchingResponse;
import com.eleven.mvp_back.domain.enums.ProgressStatus;
import com.eleven.mvp_back.domain.service.MatchingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matchings")
@RequiredArgsConstructor
public class CaregiverMatchingController {

    private MatchingService matchingService;

    @GetMapping
    public CommonResponse<List<CaregiverMatchingListResponse>> getMatchingsCaregiver(@AuthenticationPrincipal Long caregiverId) {
        List<CaregiverMatchingListResponse> responses = matchingService.getMatchingsCaregiver(caregiverId);
        return CommonResponse.success("매칭 목록 조회 성공", responses);
    }

    @GetMapping("/{matchingId}")
    public CommonResponse<CaregiverMatchingDetailResponse> getCaregiverMatchingDetail(@AuthenticationPrincipal Long caregiverId,
                                                                                      @PathVariable Long matchingId) {
        CaregiverMatchingDetailResponse response = matchingService.getCaregiverMatchingDetail(caregiverId, matchingId);
        return CommonResponse.success("매칭 상세 조회 성공", response);
    }

    @PutMapping("/{matchingId}/response")
    public CommonResponse<MatchingResponse> responseMatchingToSocialworker(@AuthenticationPrincipal Long caregiverId,
                                                                           @PathVariable Long matchingId,
                                                                           @Valid @RequestBody ProgressStatusRequest request) {

        MatchingResponse response = matchingService.responseMatchingToSocialworker(caregiverId, matchingId, request);
        return CommonResponse.success("매칭 응답 완료", response);
    }
}
