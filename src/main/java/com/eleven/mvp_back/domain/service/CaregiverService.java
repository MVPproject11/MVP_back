package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.caregiver.CaregiverRequest;
import com.eleven.mvp_back.domain.dto.response.caregiver.CaregiverResponse;

import java.io.IOException;

public interface CaregiverService {
    CaregiverResponse registerCaregiver(CaregiverRequest request, Long userId) throws IOException;
    CaregiverResponse getCaregiverInfo(Long userId);
    CaregiverResponse updateCaregiverInfo(CaregiverRequest request, Long userId) throws IOException;
}
