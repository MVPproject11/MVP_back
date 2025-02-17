package com.eleven.mvp_back.domain.service.impl;

import com.eleven.mvp_back.domain.dto.request.user.LoginRequest;
import com.eleven.mvp_back.domain.dto.request.user.SignupRequest;
import com.eleven.mvp_back.domain.dto.response.user.LoginResponse;
import com.eleven.mvp_back.domain.dto.response.user.SignupResponse;
import com.eleven.mvp_back.domain.entity.User;
import com.eleven.mvp_back.domain.enums.Role;
import com.eleven.mvp_back.domain.repository.UserRepository;
import com.eleven.mvp_back.domain.service.UserService;
import com.eleven.mvp_back.exception.BadRequestException;
import com.eleven.mvp_back.exception.ResourceAlreadyExistException;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import com.eleven.mvp_back.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final Set<String> blacklistedTokens = new HashSet<>();

    @Transactional
    @Override
    public SignupResponse signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistException("이미 등록된 이메일입니다.");
        }

        Role role;
        try {
            role = Role.valueOf(request.role().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("올바르지 않은 역할입니다.");
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(role)
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        return new SignupResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getRole().name());
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("미가입 회원입니다."));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadRequestException("ID 또는 비밀번호가 일치하지 않습니다.");
        }
        String token = jwtTokenProvider.createToken(user.getId(), user.getEmail(), user.getRole().name()); // JWT 발급

        return new LoginResponse(user.getId(), user.getEmail(), user.getRole().name());
    }

    @Transactional
    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}

