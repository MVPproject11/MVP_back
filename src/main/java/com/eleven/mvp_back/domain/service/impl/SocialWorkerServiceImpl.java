package com.eleven.mvp_back.domain.service.impl;

import com.eleven.mvp_back.domain.dto.request.SocialWorkerRequest;
import com.eleven.mvp_back.domain.dto.response.SocialWorkerResponse;
import com.eleven.mvp_back.domain.entity.SocialWorker;
import com.eleven.mvp_back.domain.entity.User;
import com.eleven.mvp_back.domain.enums.Role;
import com.eleven.mvp_back.domain.repository.SocialWorkerRepository;
import com.eleven.mvp_back.domain.repository.UserRepository;
import com.eleven.mvp_back.domain.service.FileUploadService;
import com.eleven.mvp_back.domain.service.SocialWorkerService;
import com.eleven.mvp_back.exception.BadRequestException;
import com.eleven.mvp_back.exception.ResourceAlreadyExistException;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SocialWorkerServiceImpl implements SocialWorkerService {

    private final SocialWorkerRepository socialWorkerRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    @Transactional
    @Override
    public SocialWorkerResponse registerSocialWorker(SocialWorkerRequest request, Long userId) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 사용자를 찾을 수 없습니다,"));

        if (!user.getRole().equals(Role.SOCIALWORKER)) {
            throw new BadRequestException("사회복지사 프로필은 사회복지사만 등록 가능합니다");
        }

        String profileUrl;
        if (request.socialworkerProfile() != null && !request.socialworkerProfile().isEmpty()) {
            profileUrl = fileUploadService.uploadFile(request.socialworkerProfile());
        } else {
            profileUrl = null;
        }

        //TODO: aws s3로 이미지 url 저장하도록 추가예정

        SocialWorker socialWorker = SocialWorker.builder()
                .user(user)
                .centerName(request.centerName())
                .phoneNumber(request.phoneNumber())
                .ownBathCar(request.ownBathCar())
                .centerAddress(request.centerAddress())
                .centerGrade(request.centerGrade())
                .operationPeriod(request.operationPeriod())
                .introduction(request.introduction())
                .socialworkerProfile(profileUrl)
                .createdAt(LocalDateTime.now())
                .build();

        SocialWorker saveSocialWorker = socialWorkerRepository.save(socialWorker);

        return new SocialWorkerResponse(
                saveSocialWorker.getId(),
                saveSocialWorker.getCenterName(),
                saveSocialWorker.getPhoneNumber(),
                saveSocialWorker.isOwnBathCar(),
                saveSocialWorker.getCenterAddress(),
                saveSocialWorker.getCenterGrade(),
                saveSocialWorker.getOperationPeriod(),
                saveSocialWorker.getIntroduction(),
                saveSocialWorker.getSocialworkerProfile()
        );
    }
}
