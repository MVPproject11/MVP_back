package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.dto.response.mainimage.MainImagesResponse;
import com.eleven.mvp_back.domain.service.MainImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "메인 이미지 API", description = "메인 페이지에 표시할 이미지 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class MainImageController {

    private final MainImageService mainImageService;

    @GetMapping
    @Operation(summary = "메인 이미지 조회", description = "메인 페이지에 표시될 10개의 이미지를 2줄(각 5개)로 나눠서 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상적으로 조회됨"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    public CommonResponse<MainImagesResponse> getMainImages() {
        MainImagesResponse mainImagesResponse =  mainImageService.getMainImages();

        return CommonResponse.success(mainImagesResponse);
    }
}
