package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.ApiResponse;
import com.eleven.mvp_back.domain.dto.request.SocialWorkerRequest;
import com.eleven.mvp_back.domain.dto.response.SocialWorkerResponse;
import com.eleven.mvp_back.domain.service.SocialWorkerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socialworkers")
public class SocialWorkerController {

    private final SocialWorkerService socialWorkerService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<SocialWorkerResponse>> registerSocialWorker(@Valid @ModelAttribute SocialWorkerRequest request,
                                                                                  @AuthenticationPrincipal Long userId) throws IOException {
        SocialWorkerResponse response = socialWorkerService.registerSocialWorker(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("사회복지사 정보 등록 성공", response));
    }
}
