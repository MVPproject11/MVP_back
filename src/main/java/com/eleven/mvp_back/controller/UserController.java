package com.eleven.mvp_back.controller;

import com.eleven.mvp_back.common.ApiResponse;
import com.eleven.mvp_back.domain.entity.User;
import com.eleven.mvp_back.dto.UserDTO;
import com.eleven.mvp_back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody UserDTO userDTO) {
        try {
            User user = User.of(
                    userDTO.getEmail(),
                    userDTO.getPassword(),
                    userDTO.getRole()
            );

            User savedUser = userService.saveUser(user);

            if (savedUser == null) {
                throw new RuntimeException("올바른 정보를 입력해주십시오.");
            }

            return ResponseEntity.ok(ApiResponse.success("회원가입에 성공하였습니다.", savedUser));
        } catch (Exception e) {
            // 저장 실패 시 에러 응답 반환
            return ResponseEntity.status(500).body(ApiResponse.error(500, "회원가입에 실패하였습니다: " + e.getMessage()));
        }
    }

    @GetMapping("/check-email")
    public ResponseEntity<ApiResponse<Boolean>> checkEmailDuplicate(@RequestParam String email) {
        boolean isDuplicate = userService.findUserByEmail(email).isPresent();
        if (isDuplicate) {
            return ResponseEntity.ok(ApiResponse.error(500, "이미 사용중인 이메일 입니다."));
        }
        return ResponseEntity.ok(ApiResponse.success("사용가능한 이메일 입니다.", false));
    }
}
