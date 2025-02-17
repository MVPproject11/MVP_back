package com.eleven.mvp_back.domain.dto.response.user;

public record SignupResponse(
        Long id,
        String email,
        String role
) {
}
