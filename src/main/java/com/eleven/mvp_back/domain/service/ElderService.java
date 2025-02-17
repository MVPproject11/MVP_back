package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.elder.ElderRequest;
import com.eleven.mvp_back.domain.dto.response.elder.ElderResponse;

import java.io.IOException;
import java.util.List;

public interface ElderService {
    ElderResponse registerElder(Long socialWorkerId, ElderRequest elderRequest) throws IOException;
    List<ElderResponse> getEldersBySocialWorker(Long socialWorkerId);
    ElderResponse getElderById(Long elderId);
    ElderResponse updateElder(Long elderId, Long socialWorkerId, ElderRequest elderRequest) throws IOException;
    void deleteElder(Long elderId);

}
