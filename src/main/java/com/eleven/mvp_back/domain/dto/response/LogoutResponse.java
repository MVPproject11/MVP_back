package com.eleven.mvp_back.domain.dto.response;

public record LogoutResponse(
        boolean success, //로그아웃 성공 여부(true or false)
        String message
) {
}
