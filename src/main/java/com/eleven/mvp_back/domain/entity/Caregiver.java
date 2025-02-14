package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "caregivers")
public class Caregiver {

    @Id
    @Column(name = "caregiver_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Gender gender;

    @Column(nullable = false, length = 13)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String caregiverProfile;

    @Column(nullable = false)
    private boolean ownCar;

    @Column(nullable = false)
    private boolean dementiaTraining;

    @Column(nullable = false)
    private Integer desiredWage;

    private Integer careerPeriod;

    @Column(length = 255)
    private String mainCareer;

    @Column(length = 255)
    private String introduction;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "caregiver_id")
    private User user;

    public static Caregiver of(String name, Gender gender, String phoneNumber, boolean ownCar, boolean dementiaTraining,
                               Integer desiredWage, Integer careerPeriod, String mainCareer, String introduction) {
        return Caregiver.builder()
                .name(name)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .ownCar(ownCar)
                .dementiaTraining(dementiaTraining)
                .desiredWage(desiredWage)
                .careerPeriod(careerPeriod)
                .mainCareer(mainCareer)
                .introduction(introduction)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
