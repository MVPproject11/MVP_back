package com.eleven.mvp_back.controller;

import com.eleven.mvp_back.common.ApiResponse;
import com.eleven.mvp_back.domain.entity.SocialWorker;
import com.eleven.mvp_back.domain.entity.User;
import com.eleven.mvp_back.dto.SocialWorkerDTO;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import com.eleven.mvp_back.service.impl.SocialWorkerService;
import com.eleven.mvp_back.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socialworkers")
public class SocialWorkerController {

    private final SocialWorkerService socialWorkerService;
    private final UserService userService;

    /*@PostMapping
    public ResponseEntity<ApiResponse<SocialWorker>> createSocialWorker(@RequestBody SocialWorkerDTO socialWorkerDTO, @RequestParam String email) {
        User user = userService.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + socialWorkerDTO.getId()));

        try {
            SocialWorker socialWorker = SocialWorker.of(
                    socialWorkerDTO.getCenterName(),
                    socialWorkerDTO.getPhoneNumber(),
                    socialWorkerDTO.isOwnBathCar(),
                    socialWorkerDTO.getCenterAddress(),
                    socialWorkerDTO.getCenterGrade(),
                    socialWorkerDTO.getOperationPeriod(),
                    socialWorkerDTO.getIntroduction(),
                    socialWorkerDTO.getSocialworkerProfile()
            );
            //socialWorker.setUser(user);
            SocialWorker savedSocialWorker = socialWorkerService.saveSocialWorker(socialWorker);

            if (savedSocialWorker == null) {
                throw new RuntimeException("올바른 정보를 입력해주십시오.");
            }

            return ResponseEntity.ok(ApiResponse.success("관리자 회원가입에 성공하였습니다.", savedSocialWorker));
        } catch (Exception e) {
            // 저장 실패 시 에러 응답 반환
            return ResponseEntity.status(500).body(ApiResponse.error(500, "관리자 회원가입에 실패하였습니다: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SocialWorker>> getSocialWorkerById(@PathVariable Long id) {
        SocialWorker socialWorker = socialWorkerService.findSocialWorkerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Social worker not found with id: " + id));
        return ResponseEntity.ok(ApiResponse.success(socialWorker));
    }*/
}
