package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.SocialWorkerRequest;
import com.eleven.mvp_back.domain.dto.response.SocialWorkerResponse;

public interface SocialWorkerService {
    SocialWorkerResponse registerSocialWorker(SocialWorkerRequest request, Long userId);
}
