package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.ApiResponse;
import com.eleven.mvp_back.domain.dto.request.caregiver.CaregiverRequest;
import com.eleven.mvp_back.domain.dto.response.caregiver.CaregiverResponse;
import com.eleven.mvp_back.domain.service.CaregiverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
}
