package com.eleven.mvp_back.domain.dto.request;

import com.eleven.mvp_back.domain.entity.Elder;
import com.eleven.mvp_back.domain.enums.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElderRequest {
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
    private List<Weekday> careDays;
    private List<String> mealAssists;
    private List<String> excretionAssists;
    private List<String> moveAssists;
    private List<String> dailyLivingAssists;

    public Elder toElder() {
        return Elder.builder()
                .name(this.name)
                .birth(this.birth)
                .gender(this.gender)
                .careGrade(this.careGrade)
                .elderPhoto(this.elderPhoto)
                .elderAddress(this.elderAddress)
                .weight(this.weight)
                .disease(this.disease)
                .housemate(this.housemate)
                .symptomsDementia(this.symptomsDementia)
                .careStartTime(this.careStartTime)
                .careEndTime(this.careEndTime)
                .createdAt(LocalDateTime.now()) // createdAt 설정
                .build();
    }
}
