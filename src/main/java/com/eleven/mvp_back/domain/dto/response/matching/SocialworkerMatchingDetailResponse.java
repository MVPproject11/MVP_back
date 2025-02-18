package com.eleven.mvp_back.domain.dto.response.matching;

import com.eleven.mvp_back.domain.dto.response.caregiver.CaregiverResponse;
import com.eleven.mvp_back.domain.dto.response.elder.ElderResponse;
import com.eleven.mvp_back.domain.dto.response.jobpost.JobPostResponse;
import com.eleven.mvp_back.domain.dto.response.socialworker.SocialWorkerResponse;
import com.eleven.mvp_back.domain.entity.caregiver.Caregiver;
import com.eleven.mvp_back.domain.entity.elder.Elder;
import com.eleven.mvp_back.domain.entity.jobpost.JobPost;
import com.eleven.mvp_back.domain.entity.matching.Matching;
import com.eleven.mvp_back.domain.entity.socialworker.SocialWorker;
import com.eleven.mvp_back.domain.enums.MatchingStatus;
import com.eleven.mvp_back.domain.enums.ProgressStatus;

import java.time.LocalTime;

public record SocialworkerMatchingDetailResponse(
        Long matchingId,
        MatchingStatus matchingStatus,
        ProgressStatus progressStatus,

        SocialWorkerResponse socialWorker,   // 사회복지사 정보
        ElderResponse elder,                 // 어르신 정보
        JobPostResponse jobPost,            // 구인공고 정보

        CaregiverResponse caregiver,        // 요양보호사 정보 (수락/조율/응답 시 보여줌)

        LocalTime requestDate,
        LocalTime responseDate
) {
    public static SocialworkerMatchingDetailResponse fromEntity(Matching matching) {
        Long matchingId = matching.getId();
        MatchingStatus matchingStatus = matching.getMatchingStatus();
        ProgressStatus progressStatus = matching.getProgressStatus();

        JobPost jobPost = matching.getJobpost();
        SocialWorker sw = jobPost.getSocialworkerElder().getSocialWorker();
        Elder elder = jobPost.getSocialworkerElder().getElder();

        SocialWorkerResponse swResponse = SocialWorkerResponse.fromEntity(sw);

        ElderResponse elderResponse = elder.toResponse(
                elder.getCareDays(),
                elder.getMealAssists(),
                elder.getExcretionAssists(),
                elder.getMoveAssists(),
                elder.getDailyLivingAssists(),
                elder.getSocialworkerElder()
        );
        JobPostResponse jobPostResponse = JobPostResponse.fromEntity(jobPost, null);

        CaregiverResponse caregiverDto = null;
        if (progressStatus == ProgressStatus.ACCEPTED
                || progressStatus == ProgressStatus.NEGOTIATING
                || progressStatus == ProgressStatus.RESPONSE)
        {
            Caregiver caregiver = matching.getCaregiver();
            caregiverDto = caregiver.toResponse(
                    caregiver.getAvailableDays(),
                    caregiver.getLocations(),
                    caregiver.getCertifications()
            );
        }

        return new SocialworkerMatchingDetailResponse(
                matchingId,
                matchingStatus,
                progressStatus,
                swResponse,
                elderResponse,
                jobPostResponse,
                caregiverDto,
                matching.getRequestDate(),
                matching.getResponseDate()
        );
    }
}
