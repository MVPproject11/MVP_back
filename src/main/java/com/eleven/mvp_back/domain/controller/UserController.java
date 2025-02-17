package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.CommonResponse;
import com.eleven.mvp_back.domain.dto.request.LoginRequest;
import com.eleven.mvp_back.domain.dto.request.LogoutRequest;
import com.eleven.mvp_back.domain.dto.request.SignupRequest;
import com.eleven.mvp_back.domain.dto.response.LoginResponse;
import com.eleven.mvp_back.domain.dto.response.LogoutResponse;
import com.eleven.mvp_back.domain.dto.response.SignupResponse;
import com.eleven.mvp_back.domain.service.UserService;
import com.eleven.mvp_back.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자 인증 API", description = "회원가입, 로그인 및 로그아웃 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (필수 값 누락 등)"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<SignupResponse>> signUpUser(@Valid @RequestBody SignupRequest request) {
        SignupResponse response = userService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.created("회원가입에 성공하셨습니다.", response));
    }

    @Operation(summary = "로그인", description = "사용자가 로그인하고 JWT 토큰을 발급받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패 (잘못된 자격 증명)"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        String token = jwtTokenProvider.createToken(response.userId(), response.email(), response.role());

        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", "Bearer " + token)
                .body(CommonResponse.success("로그인 성공", response));
    }

    @Operation(summary = "로그아웃", description = "서버측에서는 SecurityContext를 초기화")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/logout")
    public ResponseEntity<CommonResponse<Void>> logout() {
        userService.logout();
        return ResponseEntity.ok(CommonResponse.noContent("로그아웃 성공"));
    }
}
