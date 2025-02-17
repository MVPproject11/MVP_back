package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.Elder;
import com.eleven.mvp_back.domain.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private MultipartFile elderPhoto;

    @NotBlank(message = "주소는 필수입력 항목입니다.")
    private String elderAddress;

    private Integer weight;
    private String disease;
    private Housemate housemate;
    private SymptomsDementia symptomsDementia;
    private LocalTime careStartTime;
    private LocalTime careEndTime;

    private List<ElderCareDaysRequest> careDays;
    private List<ElderMealAssistsRequset> mealAssists;
    private List<ElderExcretionAssistsRequest> excretionAssists;
    private List<ElderMoveAssistsRequest> moveAssists;
    private List<ElderDailyLivingAssistsRequest> dailyLivingAssists;
    private Long socialWorkerId;

    public Elder toElder(String profileUrl) {
        return Elder.builder()
                .name(this.name)
                .birth(this.birth)
                .gender(this.gender)
                .careGrade(this.careGrade)
                .elderPhoto(profileUrl)
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
