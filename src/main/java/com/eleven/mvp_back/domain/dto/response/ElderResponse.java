package com.eleven.mvp_back.domain.dto.response;

import com.eleven.mvp_back.domain.entity.*;
import com.eleven.mvp_back.domain.enums.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElderResponse {
    private Long elderId;
    private String name;
    private LocalDate birth;
    private Gender gender;
    private CareGrade careGrade;
    private String elderPhoto;
    private String elderAddress;
    private Integer weight;
    private String disease;
    private Housemate housemate;
    private SymptomsDementia symptomsDementia;
    private LocalTime careStartTime;
    private LocalTime careEndTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<String> careDays;
    private List<String> mealAssists;
    private List<String> excretionAssists;
    private List<String> moveAssists;
    private List<String> dailyLivingAssists;
    private List<Long> socialWorkerId;

    public static ElderResponse fromEntity(Elder elder) {
        return ElderResponse.builder()
                .elderId(elder.getId())
                .name(elder.getName())
                .birth(elder.getBirth())
                .gender(elder.getGender())
                .careGrade(elder.getCareGrade())
                .elderPhoto(elder.getElderPhoto())
                .elderAddress(elder.getElderAddress())
                .weight(elder.getWeight())
                .disease(elder.getDisease())
                .housemate(elder.getHousemate())
                .symptomsDementia(elder.getSymptomsDementia())
                .careStartTime(elder.getCareStartTime())
                .careEndTime(elder.getCareEndTime())
                .createdAt(elder.getCreatedAt())
                .updatedAt(elder.getUpdatedAt())
                .careDays(elder.getCareDays() != null ?
                        elder.getCareDays().stream()
                                .map(careDay -> careDay.getDayOfWeek().name())
                                .collect(Collectors.toList())
                        : new ArrayList<>())
                .mealAssists(elder.getMealAssists() != null ?
                        elder.getMealAssists().stream()
                                .map(ElderMealAssist::getMealServiceName)
                                .collect(Collectors.toList())
                        : new ArrayList<>())
                .excretionAssists(elder.getExcretionAssists() != null ?
                        elder.getExcretionAssists().stream()
                                .map(ElderExcretionAssist::getExcretionServiceName)
                                .collect(Collectors.toList())
                        : new ArrayList<>())
                .moveAssists(elder.getMoveAssists() != null ?
                        elder.getMoveAssists().stream()
                                .map(ElderMoveAssist::getMoveServiceName)
                                .collect(Collectors.toList())
                        : new ArrayList<>())
                .dailyLivingAssists(elder.getDailyLivingAssists() != null ?
                        elder.getDailyLivingAssists().stream()
                                .map(ElderDailyLivingAssist::getDailyLivingServiceName)
                                .collect(Collectors.toList())
                        : new ArrayList<>())
                .socialWorkerId(Collections.singletonList(elder.getSocialworkerElder() != null &&
                        !elder.getSocialworkerElder().isEmpty() ?
                        elder.getSocialworkerElder().get(0).getSocialWorker().getId()
                        : null))
                .build();
    }
}

