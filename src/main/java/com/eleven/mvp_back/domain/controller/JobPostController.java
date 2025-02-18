package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.domain.dto.request.jobpost.JobPostRequest;
import com.eleven.mvp_back.domain.dto.response.jobpost.JobPostResponse;
import com.eleven.mvp_back.domain.entity.jobpost.JobPost;
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
    public JobPostResponse createJobPost(@AuthenticationPrincipal Long socialworkerId,
                                         @PathVariable Long elderId,
                                         @Valid @RequestBody JobPostRequest request) {

        return jopPostService.createJobPost(socialworkerId, elderId, request);
    }
}
