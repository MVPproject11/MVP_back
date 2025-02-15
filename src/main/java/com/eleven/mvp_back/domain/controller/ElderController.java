package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.ApiResponse;
import com.eleven.mvp_back.domain.dto.request.ElderRequest;
import com.eleven.mvp_back.domain.dto.response.ElderResponse;
import com.eleven.mvp_back.domain.service.ElderService;
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
    public ApiResponse<ElderResponse> registerElder(
            @PathVariable Long socialWorkerId,
            @RequestBody ElderRequest elderRequest) {
        ElderResponse response = elderService.registerElder(socialWorkerId, elderRequest);
        return ApiResponse.success("어르신 정보 등록에 성공하였습니다.", response);
    }

    // 2. 사회복지사가 담당하는 어르신 정보 조회
    @GetMapping("/social/{socialWorkerId}")
    public ApiResponse<List<ElderResponse>> getEldersBySocialWorker(@PathVariable Long socialWorkerId) {
        List<ElderResponse> responses = elderService.getEldersBySocialWorker(socialWorkerId);
        return ApiResponse.success(responses);
    }

    // 3. 특정 어르신 정보 조회
    @GetMapping("/{elderId}")
    public ApiResponse<ElderResponse> getElderById(@PathVariable Long elderId) {
        ElderResponse response = elderService.getElderById(elderId);
        return ApiResponse.success(response);
    }

    // 4. 특정 어르신 정보 수정
    @PutMapping("/{elderId}")
    public ApiResponse<ElderResponse> updateElder(
            @PathVariable Long elderId,
            @RequestBody ElderRequest elderRequest) {
        ElderResponse response = elderService.updateElder(elderId, elderRequest);
        return ApiResponse.success("어르신 정보 수정에 성공하였습니다.", response);
    }

    // 5. 특정 어르신 정보 삭제
    @DeleteMapping("/{elderId}")
    public ApiResponse<Void> deleteElder(@PathVariable Long elderId) {
        elderService.deleteElder(elderId);
        return ApiResponse.success("어르신 정보 삭제에 성공하였습니다.", null);
    }
}
