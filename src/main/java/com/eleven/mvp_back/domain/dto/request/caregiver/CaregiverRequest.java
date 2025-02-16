package com.eleven.mvp_back.domain.dto.request.caregiver;

import com.eleven.mvp_back.domain.entity.Caregiver;
import com.eleven.mvp_back.domain.entity.User;
import com.eleven.mvp_back.domain.enums.Gender;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record CaregiverRequest(
        @NotBlank(message = "이름은 필수 입력값입니다.")
        @Size(max = 10, message = "이름은 최대 10자까지 입력 가능합니다.")
        String name,

        @NotNull(message = "성별은 필수 입력값입니다.")
        Gender gender,

        @NotBlank(message = "전화번호는 필수 입력값입니다.")
        @Size(max = 11, message = "전화번호는 최대 11자리 숫자로 입력해야 합니다.")
        String phoneNumber,

        MultipartFile caregiverProfile,

        @NotNull(message = "자가용 보유 여부는 필수 입력값입니다.")
        Boolean ownCar,

        @NotNull(message = "치매 교육 여부는 필수 입력값입니다.")
        Boolean dementiaTraining,

        @NotNull(message = "희망 시급은 필수 입력값입니다.")
        @Min(value = 0, message = "희망 시급은 0 이상이어야 합니다.")
        Integer desiredWage,

        Integer careerPeriod,

        @Size(max = 50, message = "주요 경력은 최대 50자까지 입력 가능합니다.")
        String mainCareer,

        @Size(max = 255, message = "소개 글은 최대 255자까지 입력 가능합니다.")
        String introduction,

        LocalTime availableStartTime,

        LocalTime availableEndTime,

        @NotEmpty(message = "가능 요일을 최소 한 개 이상 선택해야 합니다.")
        List<CaregiverAvailableDayRequest> availableDays,

        @NotEmpty(message = "활동 지역을 최소 한 개 이상 입력해야 합니다.")
        List<CaregiverLocationRequest> locations,

        List<CertificationRequest> certifications
) {
        public Caregiver toEntity(User user, String profileUrl) {
                return Caregiver.builder()
                        .user(user)
                        .name(name())
                        .gender(gender())
                        .phoneNumber(phoneNumber())
                        .caregiverProfile(profileUrl)
                        .ownCar(ownCar())
                        .dementiaTraining(dementiaTraining())
                        .desiredWage(desiredWage())
                        .careerPeriod(careerPeriod())
                        .mainCareer(mainCareer())
                        .introduction(introduction())
                        .availableStartTime(availableStartTime())
                        .availableEndTime(availableEndTime())
                        .createdAt(LocalDateTime.now())
                        .build();
        }
}
