package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.dto.request.caregiver.CaregiverRequest;
import com.eleven.mvp_back.domain.dto.response.caregiver.CaregiverResponse;
import com.eleven.mvp_back.domain.service.CaregiverService;
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

@Tag(name = "요양보호사 API", description = "요양보호사 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/caregivers")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Operation(summary = "요양보호사 등록", description = "현재 로그인한 사용자가 요양보호사 정보를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "요양보호사 정보 등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (요양보호사 중복 등록, 필수 값 누락 등)"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/register")
    public ResponseEntity<CommonResponse<CaregiverResponse>> registerCaregiver(
            @Valid @ModelAttribute CaregiverRequest request,
            @AuthenticationPrincipal Long userId) throws IOException {

        CaregiverResponse response = caregiverService.registerCaregiver(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.created("요양보호사 정보 등록 성공", response));
    }

    @Operation(summary = "내 요양보호사 정보 조회", description = "현재 로그인한 사용자의 요양보호사 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요양보호사 정보 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "404", description = "요양보호사 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/me")
    public ResponseEntity<CommonResponse<CaregiverResponse>> getCaregiverInfo(@AuthenticationPrincipal Long userId) {

        CaregiverResponse response = caregiverService.getCaregiverInfo(userId);
        return ResponseEntity.ok(CommonResponse.success("요양보호사 정보 조회 성공", response));
    }

    @Operation(summary = "내 요양보호사 정보 수정", description = "현재 로그인한 사용자의 요양보호사 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요양보호사 정보 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (필수 값 누락 등)"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PutMapping("/me")
    public ResponseEntity<CommonResponse<CaregiverResponse>> updateCaregiverInfo(
            @Valid @ModelAttribute CaregiverRequest request, @AuthenticationPrincipal Long userId) throws IOException {

        CaregiverResponse response = caregiverService.updateCaregiverInfo(request, userId);

        return ResponseEntity.ok(CommonResponse.success("요양보호사 정보 수정 성공", response));
    }

    @Operation(summary = "내 요양보호사 프로필 삭제", description = "현재 로그인한 사용자의 요양보호사 프로필을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "요양보호사 프로필 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "404", description = "요양보호사 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @DeleteMapping("/me")
    public ResponseEntity<CommonResponse<Void>> deleteCaregiverProfile(@AuthenticationPrincipal Long userId) {
        caregiverService.deleteCaregiverInfo(userId);
        return ResponseEntity.ok(CommonResponse.noContent("요양보호사 프로필 삭제 성공"));
    }
}
