package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socialworker/matchings")
public class SocialworkerMatchingController {

    private final MatchingService matchingService;

    @GetMapping
    public CommonResponse<List<SocialworkerMatchingListResponse>> getAllMatchingsSocialworker(
            @AuthenticationPrincipal Long socialworkerId) {

        List<SocialworkerMatchingListResponse> responses = matchingService.getAllMatchingsSocialworker(socialworkerId);

        return CommonResponse.success("사회복지사의 매칭 목록 조회 성공", responses);
    }

    @GetMapping("/{matchingId}")
    public CommonResponse<SocialworkerMatchingDetailResponse> getMatchingDetailSocialworker(
            @AuthenticationPrincipal Long socialworkerId, @PathVariable Long matchingId) {

        SocialworkerMatchingDetailResponse response = matchingService.getMatchingDetailSocialworker(socialworkerId, matchingId);

        return CommonResponse.success("매칭 상세 조회 성공", response);
    }
}
