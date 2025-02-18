package com.eleven.mvp_back.domain.dto.response.jobpost;

import com.eleven.mvp_back.domain.entity.jobpost.JobPost;
import com.eleven.mvp_back.domain.enums.WageType;
import com.eleven.mvp_back.domain.enums.WorkType;

import java.time.LocalTime;

public record JobPostResponse(
        Long id,
        WorkType workType,
        String workAddress,
        Boolean isTimeNegotiable,
        WageType wageType,
        int wageAmount,
        String benefits,
        Integer needMember,
        String status,
        LocalTime postStartTime,
        LocalTime postEndTime,
        String managerPhone,
        String managerEmail
) {
    public JobPostResponse fromEntity(JobPost jobPost) {
        if (jobPost == null) return null;
        return new JobPostResponse(
                jobPost.getId(),
                WorkType.valueOf(jobPost.getWorkType().name()),
                jobPost.getWorkAddress(),
                jobPost.isTimeNegotiable(),
                WageType.valueOf(jobPost.getWageType()),
                jobPost.getWageAmount(),
                jobPost.getBenefits(),
                jobPost.getNeedMember(),
                jobPost.getStatus(),
                jobPost.getPostStartTime(),
                jobPost.getPostEndTime(),
                jobPost.getManagerPhone(),
                jobPost.getManagerEmail()
        );
    }
}
