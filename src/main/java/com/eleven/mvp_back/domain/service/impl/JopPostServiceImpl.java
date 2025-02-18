package com.eleven.mvp_back.domain.service.impl;

import com.eleven.mvp_back.domain.dto.request.jobpost.JobPostRequest;
import com.eleven.mvp_back.domain.dto.response.jobpost.JobPostResponse;
import com.eleven.mvp_back.domain.entity.caregiver.Caregiver;
import com.eleven.mvp_back.domain.entity.jobpost.JobPost;
import com.eleven.mvp_back.domain.entity.matching.Matching;
import com.eleven.mvp_back.domain.entity.socialworkerelder.SocialworkerElder;
import com.eleven.mvp_back.domain.entity.socialworkerelder.SocialworkerElderId;
import com.eleven.mvp_back.domain.enums.MatchingStatus;
import com.eleven.mvp_back.domain.enums.ProgressStatus;
import com.eleven.mvp_back.domain.repository.MatchingRepository;
import com.eleven.mvp_back.domain.repository.elder.ElderRepository;
import com.eleven.mvp_back.domain.repository.jobpost.JobPostRepository;
import com.eleven.mvp_back.domain.repository.socialworker.SocialWorkerRepository;
import com.eleven.mvp_back.domain.repository.socialworkerelder.SocialworkerElderRepository;
import com.eleven.mvp_back.domain.service.JopPostService;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JopPostServiceImpl implements JopPostService {

    private final SocialWorkerRepository socialWorkerRepository;
    private final ElderRepository elderRepository;
    private final SocialworkerElderRepository socialworkerElderRepository;
    private final JobPostRepository jobPostRepository;
    private final MatchingRepository matchingRepository;

    @Transactional
    @Override
    public JobPostResponse createJobPost(Long socialworkerId, Long elderId, JobPostRequest request) {
        socialWorkerRepository.findById(socialworkerId)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 사회복지사입니다."));

        elderRepository.findById(elderId)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 어르신입니다."));

        SocialworkerElderId socialworkerElderId = new SocialworkerElderId(socialworkerId, elderId);
        SocialworkerElder socialworkerElder = socialworkerElderRepository.findById(socialworkerElderId)
                .orElseThrow(() -> new ResourceNotFoundException("사회복지사와 어르신이 매핑되지 않았습니다."));

        JobPost jobPost = jobPostRepository.save(request.toEntity(socialworkerElder));

        List<Caregiver> matchedCaregivers = jobPostRepository.findCaregiversMatchingElder(socialworkerElder.getElder());

        if (matchedCaregivers.isEmpty()) {
            return JobPostResponse.fromEntity(
                    jobPost,
                    "매칭 조건에 부합하는 요양보호사가 없습니다."
            );
        }

        for (Caregiver cg : matchedCaregivers) {
            Matching matching = Matching.builder()
                    .jobpost(jobPost)
                    .caregiver(cg)
                    .matchingStatus(MatchingStatus.WAITING)
                    .progressStatus(ProgressStatus.WAITING)
                    .requestDate(LocalDateTime.now())
                    .responseDate(null)
                    .createdAt(LocalDateTime.now())
                    .build();

            log.info("Caregiver ID: " + cg.getId() + ", Available Days: " + cg.getAvailableDays());
            matchingRepository.save(matching);
        }

        String message = "매칭에 조건에 부합하는 요양보호사는 " + matchedCaregivers.size() + "명 입니다.";
        return JobPostResponse.fromEntity(jobPost, message);
    }
}
