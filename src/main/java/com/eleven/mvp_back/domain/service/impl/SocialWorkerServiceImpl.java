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
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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

        if (socialWorkerRepository.existsByUser(user)) {
            throw new BadRequestException("이미 등록된 사회복지사입니다.");
        }

        String profileUrl = (request.socialworkerProfile() != null && !request.socialworkerProfile().isEmpty())
                ? fileUploadService.uploadFile(request.socialworkerProfile()) : null;

        SocialWorker socialWorker = socialWorkerRepository.save(request.toEntity(user, profileUrl));

        return SocialWorkerResponse.fromEntity(socialWorker);
    }

    @Override
    public SocialWorkerResponse getSocialWorkerInfo(Long userId) {

        SocialWorker socialWorker = socialWorkerRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 사회복지사를 찾을 수 없습니다"));

        return SocialWorkerResponse.fromEntity(socialWorker);
    }

    @Transactional
    @Override
    public SocialWorkerResponse updateSocialWorker(Long id, SocialWorkerRequest request) throws IOException {
        //사회복지사 정보 수정 로직 추가
        SocialWorker socialWorker = socialWorkerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("해당 사회복지사를 찾을 수 없습니다."));

        if (request.socialworkerProfile() != null && !request.socialworkerProfile().isEmpty()) {
            if (socialWorker.getSocialworkerProfile() != null) {
                fileUploadService.deleteFile(socialWorker.getSocialworkerProfile());
            }
            String newProfileUrl = fileUploadService.uploadFile(request.socialworkerProfile());
            socialWorker.updateProfile(newProfileUrl);
        } else {
            if (socialWorker.getSocialworkerProfile() != null) {
                fileUploadService.deleteFile(socialWorker.getSocialworkerProfile());
                socialWorker.updateProfile(null);
            }
        }

        socialWorker.updateInfo(request);

        return SocialWorkerResponse.fromEntity(socialWorker);
    }

    @Transactional
    @Override
    public void deleteSocialWorkerInfo(Long userId) {
        SocialWorker socialWorker = socialWorkerRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("등록된 사회복지사 정보를 찾을 수 없습니다."));

        if (socialWorker.getSocialworkerProfile() != null) {
            fileUploadService.deleteFile(socialWorker.getSocialworkerProfile());
        }

        socialWorkerRepository.delete(socialWorker);
    }
}
