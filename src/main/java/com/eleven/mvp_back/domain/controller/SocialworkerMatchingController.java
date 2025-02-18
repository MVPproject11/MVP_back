package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.dto.response.matching.SocialworkerMatchingDetailResponse;
import com.eleven.mvp_back.domain.dto.response.matching.SocialworkerMatchingListResponse;
import com.eleven.mvp_back.domain.service.MatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "사회복지사 매칭 API", description = "사회복지사가 자신의 매칭 목록 및 상세 정보를 조회하는 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socialworker/matchings")
public class SocialworkerMatchingController {

    private final MatchingService matchingService;

    @Operation(summary = "사회복지사 매칭 목록 조회", description = "로그인된 사회복지사가 본인과 관련된 모든 매칭 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매칭 목록 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않음"),
            @ApiResponse(responseCode = "404", description = "매칭 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping
    public CommonResponse<List<SocialworkerMatchingListResponse>> getAllMatchingsSocialworker(
            @AuthenticationPrincipal Long socialworkerId) {

        List<SocialworkerMatchingListResponse> responses = matchingService.getAllMatchingsSocialworker(socialworkerId);

        return CommonResponse.success("사회복지사의 매칭 목록 조회 성공", responses);
    }

    @Operation(summary = "사회복지사 매칭 상세 조회", description = "로그인된 사회복지사가 특정 매칭의 상세 정보를 조회합니다." +
    "매칭 상태가 수락/조율요청/응답 상태일 경우 요양보호사의 프로필 정보를 볼 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매칭 상세 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않음"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "매칭 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/{matchingId}")
    public CommonResponse<SocialworkerMatchingDetailResponse> getMatchingDetailSocialworker(
            @AuthenticationPrincipal Long socialworkerId, @PathVariable Long matchingId) {

        SocialworkerMatchingDetailResponse response = matchingService.getMatchingDetailSocialworker(socialworkerId, matchingId);

        return CommonResponse.success("매칭 상세 조회 성공", response);
    }
}
