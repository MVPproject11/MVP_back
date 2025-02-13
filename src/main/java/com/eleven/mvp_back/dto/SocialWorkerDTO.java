package com.eleven.mvp_back.dto;

import lombok.Data;

@Data
public class SocialWorkerDTO {
    private Long id;
    private String centerName;
    private String phoneNumber;
    private boolean ownBathCar;
    private String centerAddress;
    private String centerGrade;
    private Integer operationPeriod;
    private String introduction;
    private String socialworkerProfile;
}

