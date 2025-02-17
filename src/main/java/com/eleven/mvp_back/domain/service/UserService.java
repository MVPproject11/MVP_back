package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.LoginRequest;
import com.eleven.mvp_back.domain.dto.request.LogoutRequest;
import com.eleven.mvp_back.domain.dto.request.SignupRequest;
import com.eleven.mvp_back.domain.dto.response.LoginResponse;
import com.eleven.mvp_back.domain.dto.response.LogoutResponse;
import com.eleven.mvp_back.domain.dto.response.SignupResponse;

public interface UserService {
    SignupResponse signup(SignupRequest request);
    LoginResponse login(LoginRequest request);
    LogoutResponse logout(LogoutRequest request);

    LogoutResponse logout(String token);
}
