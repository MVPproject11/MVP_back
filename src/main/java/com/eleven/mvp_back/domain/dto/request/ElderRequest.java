package com.eleven.mvp_back.domain.dto.request;

import com.eleven.mvp_back.domain.entity.Elder;
import com.eleven.mvp_back.domain.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ElderRequest {
    @NotBlank(message = "이름은 필수입력 항목입니다.")
    private String name;

    @NotNull(message = "생년월일은 필수입력 항목입니다.")
    private LocalDate birth;

    @NotNull(message = "성별은 필수입력 항목입니다.")
    private Gender gender;

    @NotNull(message = "장기요양등급은 필수입력 항목입니다.")
    private CareGrade careGrade;

    private String elderPhoto;

    @NotBlank(message = "주소는 필수입력 항목입니다.")
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
    private Long socialWorkerId;

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
                .createdAt(LocalDateTime.now())
                .build();
    }
}
