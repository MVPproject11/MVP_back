package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.domain.dto.request.elder.*;
import com.eleven.mvp_back.domain.dto.response.elder.ElderResponse;
import com.eleven.mvp_back.domain.enums.CareGrade;
import com.eleven.mvp_back.domain.enums.Gender;
import com.eleven.mvp_back.domain.enums.Housemate;
import com.eleven.mvp_back.domain.enums.SymptomsDementia;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "elders")
public class Elder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private CareGrade careGrade;

    @Column(columnDefinition = "TEXT")
    private String elderPhoto;

    @Column(nullable = false)
    private String elderAddress;

    @Column(nullable = false)
    private Integer weight;

    private String disease;

    // 단일선택
    private Housemate housemate;

    @Column(nullable = false) // 복수선택
    private SymptomsDementia symptomsDementia;

    @Column(name = "care_start_time")
    private LocalTime careStartTime;

    @Column(name = "care_end_time")
    private LocalTime careEndTime;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "elder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElderCareDays> careDays = new ArrayList<>();

    @OneToMany(mappedBy = "elder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElderDailyLivingAssist> dailyLivingAssists = new ArrayList<>();

    @OneToMany(mappedBy = "elder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElderExcretionAssist> excretionAssists = new ArrayList<>();

    @OneToMany(mappedBy = "elder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElderMealAssist> mealAssists = new ArrayList<>();

    @OneToMany(mappedBy = "elder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElderMoveAssist> moveAssists = new ArrayList<>();

    @OneToMany(mappedBy = "elder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialworkerElder> socialworkerElder = new ArrayList<>();

    public ElderResponse toResponse(List<ElderCareDays> careDays, List<ElderMealAssist> meals,
                                    List<ElderExcretionAssist> excretions, List<ElderMoveAssist> moves,
                                    List<ElderDailyLivingAssist> dailyLivings, List<SocialworkerElder> socialWorkerId) {
        return new ElderResponse(
                this.getId(),
                this.getName(),
                this.getBirth(),
                this.getGender(),
                this.getCareGrade(),
                this.getElderPhoto(),
                this.getElderAddress(),
                this.getWeight(),
                this.getDisease(),
                this.getHousemate(),
                this.getSymptomsDementia(),
                this.getCareStartTime(),
                this.getCareEndTime(),
                careDays.stream().map(ElderCareDays::toResponse).toList(),
                meals.stream().map(ElderMealAssist::toResponse).toList(),
                excretions.stream().map(ElderExcretionAssist::toResponse).toList(),
                moves.stream().map(ElderMoveAssist::toResponse).toList(),
                dailyLivings.stream().map(ElderDailyLivingAssist::toResponse).toList(),
                socialWorkerId.stream().map(SocialworkerElder::toResponse).toList()
        );
    }

    public void updateFromRequest(ElderRequest elderRequest) {
        this.name = elderRequest.getName();
        this.birth = elderRequest.getBirth();
        this.gender = elderRequest.getGender();
        this.careGrade = elderRequest.getCareGrade();
        this.elderAddress = elderRequest.getElderAddress();
        this.weight = elderRequest.getWeight();
        this.disease = elderRequest.getDisease();
        this.housemate = elderRequest.getHousemate();
        this.symptomsDementia = elderRequest.getSymptomsDementia();
        this.careStartTime = elderRequest.getCareStartTime();
        this.careEndTime = elderRequest.getCareEndTime();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateProfile(String profileUrl) {
        this.elderPhoto = profileUrl;
    }
}
