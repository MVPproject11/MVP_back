package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.dto.request.matching.ProgressStatusRequest;
import com.eleven.mvp_back.domain.dto.response.matching.CaregiverMatchingDetailResponse;
import com.eleven.mvp_back.domain.dto.response.matching.CaregiverMatchingListResponse;
import com.eleven.mvp_back.domain.dto.response.matching.MatchingResponse;
import com.eleven.mvp_back.domain.service.MatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "요양보호사 매칭 API", description = "요양보호사의 매칭에 관한 API")
@RestController
@RequestMapping("/api/matchings")
@RequiredArgsConstructor
public class CaregiverMatchingController {

    private final MatchingService matchingService;

    @Operation(summary = "요양보호사 매칭 목록 조회", description = "로그인된 요양보호사가 받은 매칭 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매칭 목록 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping
    public CommonResponse<List<CaregiverMatchingListResponse>> getMatchingsCaregiver(@AuthenticationPrincipal Long caregiverId) {
        List<CaregiverMatchingListResponse> responses = matchingService.getMatchingsCaregiver(caregiverId);
        return CommonResponse.success("매칭 목록 조회 성공", responses);
    }

    @Operation(summary = "요양보호사 매칭 상세 조회", description = "로그인된 요양보호사가 특정 매칭의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매칭 상세 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않음"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "매칭 정보 없음")
    })
    @GetMapping("/{matchingId}")
    public CommonResponse<CaregiverMatchingDetailResponse> getCaregiverMatchingDetail(@AuthenticationPrincipal Long caregiverId,
                                                                                      @PathVariable Long matchingId) {
        CaregiverMatchingDetailResponse response = matchingService.getCaregiverMatchingDetail(caregiverId, matchingId);
        return CommonResponse.success("매칭 상세 조회 성공", response);
    }

    @Operation(summary = "요양보호사 매칭 응답", description = "로그인된 요양보호사가 특정 매칭에 대해 응답/대기/수락/거절/조율요청 요청을 할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매칭 응답 처리 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증되지 않음"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "매칭 정보 없음")
    })
    @PutMapping("/{matchingId}/response")
    public CommonResponse<MatchingResponse> responseMatchingToSocialworker(@AuthenticationPrincipal Long caregiverId,
                                                                           @PathVariable Long matchingId,
                                                                           @Valid @RequestBody ProgressStatusRequest request) {

        MatchingResponse response = matchingService.responseMatchingToSocialworker(caregiverId, matchingId, request);
        return CommonResponse.success("매칭 응답 완료", response);
    }
}
