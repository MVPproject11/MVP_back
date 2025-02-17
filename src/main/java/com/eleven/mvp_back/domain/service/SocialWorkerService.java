package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.socialworker.SocialWorkerRequest;
import com.eleven.mvp_back.domain.dto.response.socialworker.SocialWorkerResponse;

import java.io.IOException;

public interface SocialWorkerService {
    SocialWorkerResponse registerSocialWorker(SocialWorkerRequest request, Long userId) throws IOException;

    SocialWorkerResponse updateSocialWorker(Long id, SocialWorkerRequest request) throws IOException;

    SocialWorkerResponse getSocialWorkerInfo(Long userId);

    void deleteSocialWorkerInfo(Long userId);
}
