package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.SocialWorkerRequest;
import com.eleven.mvp_back.domain.dto.response.SocialWorkerResponse;

import java.io.IOException;
import java.util.List;

public interface SocialWorkerService {
    SocialWorkerResponse registerSocialWorker(SocialWorkerRequest request, Long userId) throws IOException;

    SocialWorkerResponse updateSocialWorker(Long id, SocialWorkerRequest request) throws IOException;

    SocialWorkerResponse getSocialWorkerById(Long id);

    List<SocialWorkerResponse> getAllSocialWorkers();
}
