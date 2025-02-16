package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.common.ApiResponse;
import com.eleven.mvp_back.domain.dto.request.LoginRequest;
import com.eleven.mvp_back.domain.dto.request.LogoutRequest;
import com.eleven.mvp_back.domain.dto.request.SignupRequest;
import com.eleven.mvp_back.domain.dto.response.LoginResponse;
import com.eleven.mvp_back.domain.dto.response.LogoutResponse;
import com.eleven.mvp_back.domain.dto.response.SignupResponse;
import com.eleven.mvp_back.domain.service.UserService;
import com.eleven.mvp_back.security.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signUpUser(@Valid @RequestBody SignupRequest request) {
        SignupResponse response = userService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("회원가입에 성공하셨습니다.", response));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        String token = jwtTokenProvider.createToken(response.userId(), response.email(), response.role());

        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", "Bearer " + token)
                .body(ApiResponse.success("로그인 성공", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<LogoutResponse>> logout(@Valid @RequestBody LogoutRequest request) {
        String token = request.accessToken();
        LogoutResponse response = userService.logout(token);
        return ResponseEntity.ok(ApiResponse.success(response.message(), response));
    }
}
