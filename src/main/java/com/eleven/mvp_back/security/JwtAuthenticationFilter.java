package com.eleven.mvp_back.security;

import com.eleven.mvp_back.domain.enums.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 회원가입 및 로그인 요청은 필터를 거치지 않고 다음 필터로 넘김
        if (requestURI.startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 헤더에서 토큰 추출
        String bearerToken = request.getHeader("Authorization");
        String token = null;
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            token = bearerToken.substring(7);
        }

        // 토큰 검증
        if (token != null && jwtTokenProvider.validateToken(token)) {

            Long userId = Long.parseLong(jwtTokenProvider.parseClaims(token).getSubject());
            Role role = Role.valueOf(jwtTokenProvider.parseClaims(token).get("role", String.class));

            //인증 객체 생성
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userId,
                    null,
                    Collections.singleton(new SimpleGrantedAuthority(role.name()))
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //필터 체인 계속 진행
        filterChain.doFilter(request, response);
    }
}
