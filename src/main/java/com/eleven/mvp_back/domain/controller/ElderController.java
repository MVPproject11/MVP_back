package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.ApiResponse;
import com.eleven.mvp_back.domain.dto.request.ElderRequest;
import com.eleven.mvp_back.domain.dto.response.ElderResponse;
import com.eleven.mvp_back.domain.service.ElderService;
import com.eleven.mvp_back.exception.BadRequestException;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elder")
@RequiredArgsConstructor
public class ElderController {
    private final ElderService elderService;

    // 1. 어르신 정보 등록
    @PostMapping("/register/{socialWorkerId}")
    public ApiResponse<ElderResponse> registerElder(@PathVariable Long socialWorkerId, @Valid @RequestBody ElderRequest elderRequest) {
        try {
            ElderResponse response = elderService.registerElder(socialWorkerId, elderRequest);
            return ApiResponse.success("어르신 등록 성공", response);
        } catch (ResourceNotFoundException e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }

    // 2. 사회복지사가 담당하는 어르신 정보 조회
    @GetMapping("/social/{socialWorkerId}")
    public ApiResponse<List<ElderResponse>> getEldersBySocialWorker(@PathVariable Long socialWorkerId) {
        try {
            List<ElderResponse> responses = elderService.getEldersBySocialWorker(socialWorkerId);
            return ApiResponse.success(responses);
        } catch (ResourceNotFoundException e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }

    // 3. 특정 어르신 정보 조회
    @GetMapping("/{elderId}")
    public ApiResponse<ElderResponse> getElderById(@PathVariable Long elderId) {
        try {
            ElderResponse response = elderService.getElderById(elderId);
            return ApiResponse.success(response);
        } catch (ResourceNotFoundException e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }

    // 4. 특정 어르신 정보 수정
    @PutMapping("/{elderId}/{socialWorkerId}")
    public ApiResponse<ElderResponse> updateElderAndSocialWorker(@PathVariable Long elderId, @PathVariable Long socialWorkerId, @Valid @RequestBody ElderRequest elderRequest) {
        try {
            ElderResponse response = elderService.updateElder(elderId, socialWorkerId, elderRequest);
            return ApiResponse.success("어르신 정보 수정에 성공하였습니다.", response);
        } catch (ResourceNotFoundException | BadRequestException e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }

    // 5. 특정 어르신 정보 삭제
    @DeleteMapping("/{elderId}")
    public ApiResponse<Void> deleteElder(@PathVariable Long elderId) {
        elderService.deleteElder(elderId);
        return ApiResponse.success("어르신 정보 삭제에 성공하였습니다.", null);
    }
}
