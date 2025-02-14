package com.eleven.mvp_back.domain.dto.response;

public record SignupResponse(
        Long id,
        String email,
        String role
) {
}
