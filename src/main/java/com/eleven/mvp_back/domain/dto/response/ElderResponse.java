package com.eleven.mvp_back.domain.dto.response;

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

//    private List<CareDayResponse> careDays;
//    private List<MealAssistResponse> mealAssists;
//    private List<ExcretionAssistResponse> excretionAssists;
//    private List<MoveAssistResponse> moveAssists;
//    private List<DailyLivingAssistResponse> dailyLivingAssists;
//    private SocialworkerElderResponse socialWorkersElder;
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class CareDayResponse {
//        private Long careDaysId;
//        private Long elderId;
//        private List<Weekday> dayOfWeek;
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class MealAssistResponse {
//        private Long mealAssistsId;
//        private Long elderId;
//        private List<String> mealServiceName;
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class ExcretionAssistResponse {
//        private Long excretionAssistsId;
//        private Long elderId;
//        private List<String> excretionServiceName;
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class MoveAssistResponse {
//        private Long moveAssistsId;
//        private Long elderId;
//        private List<String> moveServiceName;
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class DailyLivingAssistResponse {
//        private Long dailyLivingAssistsId;
//        private Long elderId;
//        private List<String> dailyLivingServiceName;
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class SocialworkerElderResponse {
//        private Long socialWorkerId;
//        private Long elderId;
//    }

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
//                .careDays(elder.getCareDays().stream().map(careDay -> CareDayResponse.builder()
//                        .careDaysId(careDay.getId())
//                        .elderId(careDay.getElder().getId())
//                        .dayOfWeek(List.of(careDay.getDayOfWeek())) // 수정: 배열로 반환
//                        .build()).collect(Collectors.toList()))
//                .mealAssists(elder.getMealAssists().stream().map(mealAssist -> MealAssistResponse.builder()
//                        .mealAssistsId(mealAssist.getId())
//                        .elderId(mealAssist.getElder().getId())
//                        .mealServiceName(List.of(mealAssist.getMealServiceName())) // 수정: 배열로 반환
//                        .build()).collect(Collectors.toList()))
//                .excretionAssists(elder.getExcretionAssists().stream().map(excretionAssist -> ExcretionAssistResponse.builder()
//                        .excretionAssistsId(excretionAssist.getId())
//                        .elderId(excretionAssist.getElder().getId())
//                        .excretionServiceName(List.of(excretionAssist.getExcretionServiceName())) // 수정: 배열로 반환
//                        .build()).collect(Collectors.toList()))
//                .moveAssists(elder.getMoveAssists().stream().map(moveAssist -> MoveAssistResponse.builder()
//                        .moveAssistsId(moveAssist.getId())
//                        .elderId(moveAssist.getElder().getId())
//                        .moveServiceName(List.of(moveAssist.getMoveServiceName())) // 수정: 배열로 반환
//                        .build()).collect(Collectors.toList()))
//                .dailyLivingAssists(elder.getDailyLivingAssists().stream().map(dailyLivingAssist -> DailyLivingAssistResponse.builder()
//                        .dailyLivingAssistsId(dailyLivingAssist.getId())
//                        .elderId(dailyLivingAssist.getElder().getId())
//                        .dailyLivingServiceName(List.of(dailyLivingAssist.getDailyLivingServiceName())) // 수정: 배열로 반환
//                        .build()).collect(Collectors.toList()))
//                .socialWorkersElder(SocialworkerElderResponse.builder()
//                        .socialWorkerId(elder.getSocialworkerElder().getSocialWorker().getId())
//                        .elderId(elder.getSocialworkerElder().getElder().getId())
//                        .build())
                .build();
    }

}
