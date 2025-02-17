package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.user.LoginRequest;
import com.eleven.mvp_back.domain.dto.request.user.SignupRequest;
import com.eleven.mvp_back.domain.dto.response.user.LoginResponse;
import com.eleven.mvp_back.domain.dto.response.user.SignupResponse;

public interface UserService {
    SignupResponse signup(SignupRequest request);
    LoginResponse login(LoginRequest request);
    void logout();
}
