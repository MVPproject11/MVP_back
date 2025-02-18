package com.eleven.mvp_back.domain.dto.response.matching;

import com.eleven.mvp_back.domain.dto.response.elder.ElderResponse;
import com.eleven.mvp_back.domain.dto.response.jobpost.JobPostResponse;
import com.eleven.mvp_back.domain.dto.response.socialworker.SocialWorkerResponse;
import com.eleven.mvp_back.domain.entity.elder.Elder;
import com.eleven.mvp_back.domain.entity.jobpost.JobPost;
import com.eleven.mvp_back.domain.entity.matching.Matching;
import com.eleven.mvp_back.domain.entity.socialworker.SocialWorker;
import com.eleven.mvp_back.domain.enums.MatchingStatus;
import com.eleven.mvp_back.domain.enums.ProgressStatus;

public record CaregiverMatchingDetailResponse(
        Long matchingId,
        MatchingStatus matchingStatus,
        ProgressStatus progressStatus,

       SocialWorkerResponse socialWorker,

        ElderResponse elder,

        JobPostResponse jobPostResponse
) {
    public static CaregiverMatchingDetailResponse fromEntity(Matching matching) {
        Long matchingId = matching.getId();
        MatchingStatus matchingStatus = matching.getMatchingStatus();
        ProgressStatus progressStatus = matching.getProgressStatus();

        JobPost jobPost = matching.getJobpost();
        SocialWorker socialWorker = jobPost.getSocialworkerElder().getSocialWorker();
        Elder elder = jobPost.getSocialworkerElder().getElder();

        SocialWorkerResponse swResponse = SocialWorkerResponse.fromEntity(socialWorker);
        ElderResponse elderResponse = elder.toResponse(
                elder.getCareDays(),
                elder.getMealAssists(),
                elder.getExcretionAssists(),
                elder.getMoveAssists(),
                elder.getDailyLivingAssists(),
                elder.getSocialworkerElder()
        );

        JobPostResponse jobPostResp = JobPostResponse.fromEntity(jobPost, null);

        return new CaregiverMatchingDetailResponse(
                matchingId,
                matchingStatus,
                progressStatus,
                swResponse,
                elderResponse,
                jobPostResp
        );
    }
}
