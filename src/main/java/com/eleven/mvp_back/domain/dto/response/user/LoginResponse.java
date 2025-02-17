package com.eleven.mvp_back.domain.dto.response.user;

public record LoginResponse(
        Long userId,
        String email,
        String role
) {
}
