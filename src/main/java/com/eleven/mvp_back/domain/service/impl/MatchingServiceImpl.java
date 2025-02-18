package com.eleven.mvp_back.domain.service.impl;

import com.eleven.mvp_back.domain.dto.request.matching.ProgressStatusRequest;
import com.eleven.mvp_back.domain.dto.response.matching.*;
import com.eleven.mvp_back.domain.entity.matching.Matching;
import com.eleven.mvp_back.domain.enums.MatchingStatus;
import com.eleven.mvp_back.domain.enums.ProgressStatus;
import com.eleven.mvp_back.domain.repository.matching.MatchingRepository;
import com.eleven.mvp_back.domain.service.MatchingService;
import com.eleven.mvp_back.exception.BadRequestException;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingServiceImpl implements MatchingService {

    private final MatchingRepository matchingRepository;

    @Override
    public List<CaregiverMatchingListResponse> getMatchingsCaregiver(Long caregiverId) {

        List<Matching> matchings = matchingRepository.findAllByCaregiver_Id(caregiverId);

        return matchings.stream()
                .map(CaregiverMatchingListResponse::fromEntity)
                .toList();
    }

    @Override
    public CaregiverMatchingDetailResponse getCaregiverMatchingDetail(Long caregiverId, Long matchingId) {

        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 매칭입니다."));

        if (!matching.getCaregiver().getId().equals(caregiverId)) {
            throw new BadRequestException("해당 매칭은 잘못 전달되었습니다.");
        }

        return CaregiverMatchingDetailResponse.fromEntity(matching);
    }

    @Transactional
    @Override
    public MatchingResponse responseMatchingToSocialworker(Long caregiverId, Long matchingId, ProgressStatusRequest request) {

        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 매칭입니다."));

        if (!matching.getCaregiver().getId().equals(caregiverId)) {
            throw new BadRequestException("해당 매칭은 잘못 전달되었습니다.");
        }

        ProgressStatus progressStatus = request.progressStatus();
        if (progressStatus == ProgressStatus.WAITING){
            matching.updateStatus(progressStatus, MatchingStatus.WAITING);
        } else if (progressStatus == ProgressStatus.NEGOTIATING) {
            matching.updateStatus(progressStatus, MatchingStatus.IN_PROGRESS);
        } else if (progressStatus == ProgressStatus.REJECTED ||
                progressStatus == ProgressStatus.ACCEPTED ||
                progressStatus == ProgressStatus.RESPONSE) {
            matching.updateStatus(progressStatus, MatchingStatus.COMPLETED);
        }

        matchingRepository.save(matching);

        return MatchingResponse.fromEntity(matching, "매칭 상태가 변경되었습니다.");
    }

    @Override
    public List<SocialworkerMatchingListResponse> getAllMatchingsSocialworker(Long socialworkerId) {
        List<Matching> matchings = matchingRepository.findAllByJobpostSocialworkerElderSocialWorkerId(socialworkerId);

        return matchings.stream()
                .map(SocialworkerMatchingListResponse::fromEntity)
                .toList();
    }

    @Override
    public SocialworkerMatchingDetailResponse getMatchingDetailSocialworker(Long socialworkerId, Long matchingId) {
        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new ResourceNotFoundException("매칭이 존재하지 않습니다."));

        return SocialworkerMatchingDetailResponse.fromEntity(matching);
    }
}
