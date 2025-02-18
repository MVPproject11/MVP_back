package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.dto.request.jobpost.JobPostRequest;
import com.eleven.mvp_back.domain.dto.response.jobpost.JobPostResponse;
import com.eleven.mvp_back.domain.service.JopPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/elders/{elderId}")
@RequiredArgsConstructor
public class JobPostController {

    private final JopPostService jopPostService;

    @PostMapping("/jobposts")
    public CommonResponse<JobPostResponse> createJobPost(@AuthenticationPrincipal Long socialworkerId,
                                                        @PathVariable Long elderId,
                                                        @Valid @RequestBody JobPostRequest request) {

        JobPostResponse response = jopPostService.createJobPost(socialworkerId, elderId, request);
        return CommonResponse.created("구인공고 생성 완료 및 매칭 전달", response);
    }
}
