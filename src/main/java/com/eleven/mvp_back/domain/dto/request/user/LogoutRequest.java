package com.eleven.mvp_back.domain.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequest (
    @NotBlank(message = "엑세스 토큰은 필수입니다.")
    String accessToken
) {
}
