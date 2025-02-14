package com.eleven.mvp_back.domain.dto.response;

import com.eleven.mvp_back.domain.enums.Role;

public record SignupResponse(
        Long id,
        String email,
        String role
) {
}
