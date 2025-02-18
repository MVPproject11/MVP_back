package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.dto.request.jobpost.JobPostRequest;
import com.eleven.mvp_back.domain.dto.response.jobpost.JobPostResponse;
import com.eleven.mvp_back.domain.service.JopPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "구인공고 API", description = "사회복지사가 선택한 노인에 대한 구인공고를 등록하고 매칭을 생성하는 API")
@RestController
@RequestMapping("/api/elders/{elderId}")
@RequiredArgsConstructor
public class JobPostController {

    private final JopPostService jopPostService;

    @Operation(summary = "구인공고 등록", description = "로그인된 사회복지사가 특정 노인에 대해 구인공고를 등록하고 매칭 요청을 전달합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "구인공고 생성 및 매칭 요청 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증되지 않음"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "노인 또는 사회복지사 매핑 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/jobposts")
    public CommonResponse<JobPostResponse> createJobPost(@AuthenticationPrincipal Long socialworkerId,
                                                        @PathVariable Long elderId,
                                                        @Valid @RequestBody JobPostRequest request) {

        JobPostResponse response = jopPostService.createJobPost(socialworkerId, elderId, request);
        return CommonResponse.created("구인공고 생성 완료 및 매칭 전달", response);
    }
}
