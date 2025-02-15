package com.eleven.mvp_back.domain.dto.response;

public record LoginResponse(
        Long userId,
        String email,
        String role
) {
}
