package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.dto.request.elder.ElderRequest;
import com.eleven.mvp_back.domain.dto.response.elder.*;
import com.eleven.mvp_back.domain.service.ElderService;
import com.eleven.mvp_back.exception.BadRequestException;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "어르신 API", description = "어르신 관련 API")
@RestController
@RequestMapping("/api/elder")
@RequiredArgsConstructor
public class ElderController {
    private final ElderService elderService;

    // 1. 어르신 정보 등록
    @Operation(summary = "어르신 등록", description = "사회복지사가 어르신 정보를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "어르신 등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (필수 값 누락 등)"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping(value = "/register/{socialWorkerId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<ElderResponse> registerElder(@PathVariable Long socialWorkerId,
                                                       @Valid @ModelAttribute ElderRequest elderRequest) {
        try {
            ElderResponse response = elderService.registerElder(socialWorkerId, elderRequest);
            return CommonResponse.success("어르신 등록 성공", response);
        } catch (ResourceNotFoundException e) {
            return CommonResponse.error(500, e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 2. 사회복지사가 담당하는 어르신 정보 조회
    @Operation(summary = "사회복지사가 담당하는 어르신 목록 조회", description = "사회복지사가 담당하는 모든 어르신 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "어르신 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 사회복지사가 담당하는 어르신 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/social/{socialWorkerId}")
    public CommonResponse<List<ElderResponse>> getEldersBySocialWorker(@PathVariable Long socialWorkerId) {
        try {
            List<ElderResponse> responses = elderService.getEldersBySocialWorker(socialWorkerId);
            return CommonResponse.success(responses);
        } catch (ResourceNotFoundException e) {
            return CommonResponse.error(500, e.getMessage());
        }
    }

    // 3. 특정 어르신 정보 조회
    @Operation(summary = "특정 어르신 정보 조회", description = "특정 어르신의 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "어르신 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "어르신 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/{elderId}")
    public CommonResponse<ElderResponse> getElderById(@PathVariable Long elderId) {
        try {
            ElderResponse response = elderService.getElderById(elderId);
            return CommonResponse.success(response);
        } catch (ResourceNotFoundException e) {
            return CommonResponse.error(500, e.getMessage());
        }
    }

    // 4. 특정 어르신 정보 수정
    @Operation(summary = "특정 어르신 정보 수정", description = "특정 어르신의 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "어르신 정보 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (필수 값 누락 등)"),
            @ApiResponse(responseCode = "404", description = "어르신 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PutMapping(value = "/{elderId}/{socialWorkerId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<ElderResponse> updateElderAndSocialWorker(@PathVariable Long elderId,
                                                                    @PathVariable Long socialWorkerId,
                                                                    @RequestPart("elderPhoto") MultipartFile elderPhoto,
                                                                    @Valid @RequestPart("elderRequest") ElderRequest elderRequest) {
        try {
            ElderResponse response = elderService.updateElder(elderId, socialWorkerId, elderRequest, elderPhoto);
            return CommonResponse.success("어르신 정보 수정에 성공하였습니다.", response);
        } catch (ResourceNotFoundException | BadRequestException e) {
            return CommonResponse.error(500, e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 5. 특정 어르신 정보 삭제
    @Operation(summary = "특정 어르신 정보 삭제", description = "특정 어르신의 정보를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "어르신 정보 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "어르신 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @DeleteMapping("/{elderId}")
    public CommonResponse<Void> deleteElder(@PathVariable Long elderId) {
        elderService.deleteElder(elderId);
        return CommonResponse.success("어르신 정보 삭제에 성공하였습니다.", null);
    }
}
