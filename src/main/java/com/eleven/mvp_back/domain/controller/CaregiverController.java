package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.ApiResponse;
import com.eleven.mvp_back.domain.dto.request.caregiver.CaregiverRequest;
import com.eleven.mvp_back.domain.dto.response.caregiver.CaregiverResponse;
import com.eleven.mvp_back.domain.service.CaregiverService;
import io.swagger.v3.oas.annotations.Parameter;
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

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CaregiverResponse>> registerCaregiver(
            @Valid @ModelAttribute CaregiverRequest request,
            @AuthenticationPrincipal Long userId) throws IOException {

        CaregiverResponse response = caregiverService.registerCaregiver(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("요양보호사 정보 등록 성공", response));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<CaregiverResponse>> getCaregiverInfo(@AuthenticationPrincipal Long userId) {

        CaregiverResponse response = caregiverService.getCaregiverInfo(userId);
        return ResponseEntity.ok(ApiResponse.success("요양보호사 정보 조회 성공", response));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<CaregiverResponse>> updateCaregiverInfo(
            @Valid @ModelAttribute CaregiverRequest request, @AuthenticationPrincipal Long userId) throws IOException {

        CaregiverResponse response = caregiverService.updateCaregiverInfo(request, userId);

        return ResponseEntity.ok(ApiResponse.success("요양보호사 정보 수정 성공", response));
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Void>> deleteCaregiveerProfile(@AuthenticationPrincipal Long userId) {
        caregiverService.deleteCaregiverInfo(userId);
        return ResponseEntity.ok(ApiResponse.success("요양보호사 프로필 삭제 성공", null));
    }
}
