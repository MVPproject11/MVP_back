package com.eleven.mvp_back.domain.dto;

import com.eleven.mvp_back.domain.enums.WorkType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobPostDTO {
    private Long id;
    private Long socialworkerId;
    private Long elderId;
    private WorkType workType;
    private String workAddress;
    private boolean isTimeNegotiable;
    private String wageType;
    private int wageAmount;
    private String benefits;
    private Integer needMember;
    private String status;
    private LocalDateTime postStartTime;
    private LocalDateTime postEndTime;
    private String managerPhone;
    private String managerEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}