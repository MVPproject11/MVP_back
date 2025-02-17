package com.eleven.mvp_back.domain.dto.request.elder;

import com.eleven.mvp_back.domain.entity.Elder;
import com.eleven.mvp_back.domain.enums.CareGrade;
import com.eleven.mvp_back.domain.enums.Gender;
import com.eleven.mvp_back.domain.enums.Housemate;
import com.eleven.mvp_back.domain.enums.SymptomsDementia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record ElderRequest(
        @NotBlank(message = "이름은 필수 입력 항목입니다.")
        String name,

        @NotNull(message = "생년월일은 필수 입력 항목입니다.")
        LocalDate birth,

        @NotNull(message = "성별은 필수 입력 항목입니다.")
        Gender gender,

        @NotNull(message = "케어등급(장기요양등급)은 필수 입력 항목입니다.")
        CareGrade careGrade,

        MultipartFile elderPhoto,

        @NotBlank(message = "어르신 주소는 필수 입력 항목입니다.")
        String elderAddress,

        @NotNull(message = "체중은 필수 입력 항목입니다.")
        Integer weight,

        String disease,

        @NotNull(message = "동거인여부는 필수 입력 항목입니다.")
        Housemate housemate,

        @NotNull(message = "증상(치매 등)은 필수 입력 항목입니다.")
        SymptomsDementia symptomsDementia,

        @NotNull(message = "케어 시작 시간은 필수 입력 항목입니다.")
        LocalTime careStartTime,

        @NotNull(message = "케어 종료 시간은 필수 입력 항목입니다.")
        LocalTime careEndTime,

        @NotEmpty(message = "요일을 최소 한 개 이상 선택해야 합니다.")
        List<ElderCareDaysRequest> careDays,

        @NotEmpty(message = "식사보조 항목을 한 개 이상 선택해야 합니다.")
        List<ElderMealAssistsRequest> mealAssists,

        @NotEmpty(message = "배변보조 항목을 최소 한 개 이상 선택해야 합니다.")
        List<ElderExcretionAssistsRequest> excretionAssists,

        @NotEmpty(message = "이동 보조 항목을 최소 한 개 이상 선택해야 합니다.")
        List<ElderMoveAssistsRequest> moveAssists,

        @NotEmpty(message = "일상생활 보조 항목을 최소 한 개 이상 선택해야 합니다.")
        List<ElderDailyLivingAssistsRequest> dailyLivingAssists
) {
    public Elder toEntity(String elderPhotoUrl) {
        return Elder.builder()
                .name(this.name())
                .birth(this.birth())
                .gender(this.gender())
                .careGrade(this.careGrade())
                .elderPhoto(elderPhotoUrl)
                .elderAddress(this.elderAddress())
                .weight(this.weight())
                .disease(this.disease())
                .housemate(this.housemate())
                .symptomsDementia(this.symptomsDementia())
                .careStartTime(this.careStartTime())
                .careEndTime(this.careEndTime())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
