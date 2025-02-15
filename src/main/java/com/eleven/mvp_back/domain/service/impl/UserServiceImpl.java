package com.eleven.mvp_back.domain.service.impl;

import com.eleven.mvp_back.domain.dto.request.LoginRequest;
import com.eleven.mvp_back.domain.dto.request.SignupRequest;
import com.eleven.mvp_back.domain.dto.response.LoginResponse;
import com.eleven.mvp_back.domain.dto.response.SignupResponse;
import com.eleven.mvp_back.domain.entity.User;
import com.eleven.mvp_back.domain.enums.Role;
import com.eleven.mvp_back.domain.repository.UserRepository;
import com.eleven.mvp_back.domain.service.UserService;
import com.eleven.mvp_back.exception.BadRequestException;
import com.eleven.mvp_back.exception.ResourceAlreadyExistException;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public SignupResponse signup(SignupRequest request) {

        // 이메일(가입 id) 중복체크
        if (userRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistException("이미 등록된 이메일입니다.");
        }

        Role role;
        try {
            role = Role.valueOf(request.role().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(request.role());
        }

        User user = User.builder()
                .email(request.email())
                .password(request.password())
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

        return new LoginResponse(user.getId(), user.getEmail(), user.getRole().name());
    }
}
