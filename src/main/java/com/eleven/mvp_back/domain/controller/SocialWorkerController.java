package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.dto.request.SocialWorkerRequest;
import com.eleven.mvp_back.domain.dto.response.SocialWorkerResponse;
import com.eleven.mvp_back.domain.service.SocialWorkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "사회복지사 API", description = "사회복지사 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socialworkers")
public class SocialWorkerController {

    private final SocialWorkerService socialWorkerService;

    @Operation(summary = "사회복지사 등록", description = "새로운 사회복지사 정보를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "사회복지사 정보 등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (필수 값 누락 등)"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/register")
    public ResponseEntity<CommonResponse<SocialWorkerResponse>> registerSocialWorker(
            @Valid @ModelAttribute SocialWorkerRequest request,
            @AuthenticationPrincipal Long userId) throws IOException {

        SocialWorkerResponse response = socialWorkerService.registerSocialWorker(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.created("사회복지사 정보 등록 성공", response));
    }

    @Operation(summary = "사회복지사 본인 정보 조회", description = "사회복지사의 본인 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사회복지사 정보 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "사회복지사 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/me")
    public ResponseEntity<CommonResponse<SocialWorkerResponse>> getSocialWorkerInfo(@AuthenticationPrincipal Long userId) {

        SocialWorkerResponse response = socialWorkerService.getSocialWorkerInfo(userId);
        return ResponseEntity.ok(CommonResponse.success("사회복지사 정보 조회 성공", response));
    }

    @Operation(summary = "사회복지사 정보 수정", description = "특정 사회복지사의 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사회복지사 정보 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (필수 값 누락 등)"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "사회복지사 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PutMapping("/me")
    public ResponseEntity<CommonResponse<SocialWorkerResponse>> updateSocialWorker(
            @AuthenticationPrincipal Long userId,
            @Valid @ModelAttribute SocialWorkerRequest request) throws IOException {

        SocialWorkerResponse response = socialWorkerService.updateSocialWorker(userId, request);

        return ResponseEntity.ok(CommonResponse.success("사회복지사 정보 수정 성공", response));
    }

    @Operation(summary = "내 요양보호사 프로필 삭제", description = "현재 로그인한 사용자의 요양보호사 프로필을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "요양보호사 프로필 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "요양보호사 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @DeleteMapping("/me")
    public ResponseEntity<CommonResponse<Void>> deleteSocialworkerProfile(@AuthenticationPrincipal Long userId) {
        socialWorkerService.deleteSocialWorkerInfo(userId);
        return ResponseEntity.ok(CommonResponse.noContent("사회복지사 프로필 삭제 성공"));
    }
}
