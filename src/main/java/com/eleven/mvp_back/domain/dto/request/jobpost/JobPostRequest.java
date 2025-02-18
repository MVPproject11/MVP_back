package com.eleven.mvp_back.domain.dto.request.jobpost;

import com.eleven.mvp_back.domain.entity.jobpost.JobPost;
import com.eleven.mvp_back.domain.entity.socialworkerelder.SocialworkerElder;
import com.eleven.mvp_back.domain.enums.WageType;
import com.eleven.mvp_back.domain.enums.WorkType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record JobPostRequest(
        @NotNull
        WorkType workType,

        @NotBlank
        String workAddress,

        @NotNull
        Boolean isTimeNegotiable,

        @NotNull
        WageType wageType,

        @Positive
        int wageAmount,

        String benefits,

        Integer needMember,

        @NotBlank
        String status,

        @NotNull
        LocalTime postStartTime,

        @NotNull
        LocalTime postEndTime,

        String managerPhone,

        @NotBlank
        @Email
        String managerEmail
) {
    public JobPost toEntity(SocialworkerElder socialworkerElder) {
        return JobPost.builder()
                .socialworkerElder(socialworkerElder)
                .workType(WorkType.valueOf(this.workType().name()))   // enum → String
                .workAddress(this.workAddress())
                .isTimeNegotiable(this.isTimeNegotiable())
                .wageType(this.wageType().name())   // enum → String
                .wageAmount(this.wageAmount())
                .benefits(this.benefits())
                .needMember(this.needMember())
                .status(this.status())
                .postStartTime(this.postStartTime())
                .postEndTime(this.postEndTime())
                .managerPhone(this.managerPhone())
                .managerEmail(this.managerEmail())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
