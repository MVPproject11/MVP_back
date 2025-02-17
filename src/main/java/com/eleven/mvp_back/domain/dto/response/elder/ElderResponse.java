package com.eleven.mvp_back.domain.dto.response.elder;

import com.eleven.mvp_back.domain.enums.CareGrade;
import com.eleven.mvp_back.domain.enums.Gender;
import com.eleven.mvp_back.domain.enums.Housemate;
import com.eleven.mvp_back.domain.enums.SymptomsDementia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ElderResponse(
        Long id,
        String name,
        LocalDate birth,
        Gender gender,
        CareGrade careGrade,
        String elderPhoto,
        String elderAddress,
        Integer weight,
        String disease,
        Housemate housemate,
        SymptomsDementia symptomsDementia,
        LocalTime careStartTime,
        LocalTime careEndTime,
        List<ElderCareDaysResponse> careDays,
        List<ElderMealAssistsResponse> mealAssists,
        List<ElderExcretionAssistsResponse> excretionAssists,
        List<ElderMoveAssistsResponse> moveAssists,
        List<ElderDailyLivingAssistsResponse> dailyLivingAssists,
        List<SocialWorkerElderResponse> socialWorkerIds
) {
}
