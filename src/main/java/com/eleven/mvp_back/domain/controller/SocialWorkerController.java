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
import java.util.List;

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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SocialWorkerResponse>> updateSocialWorker(
            @PathVariable Long id,
            @Valid @RequestBody SocialWorkerRequest request) throws IOException {
        SocialWorkerResponse response = socialWorkerService.updateSocialWorker(id, request);
        return ResponseEntity.ok(ApiResponse.success("사회복지사 정보 수정 성공", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SocialWorkerResponse>> getSocialWorkerById(@PathVariable Long id) {
        SocialWorkerResponse response = socialWorkerService.getSocialWorkerById(id);
        return ResponseEntity.ok(ApiResponse.success("사회복지사 정보 조회 성공", response));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<SocialWorkerResponse>>> getAllSocialWorkers() {
        List<SocialWorkerResponse> responseList = socialWorkerService.getAllSocialWorkers();
        return ResponseEntity.ok(ApiResponse.success("전체 사회복지사 목록 조회 성공", responseList));
    }

}
