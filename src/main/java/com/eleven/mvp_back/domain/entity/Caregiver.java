package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "caregivers")
public class Caregiver {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "caregiver_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "caregiver_id")
    private User user;

    @Column(nullable = false, length = 10)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Gender gender;

    @Column(nullable = false, length = 11)
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

    private String mainCareer;

    private String introduction;

    private LocalTime availableStartTime;

    private LocalTime availableEndTime;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL)
    private List<CaregiverAvailableDay> availableDays = new ArrayList<>();

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL)
    private List<CaregiverLocation> locations = new ArrayList<>();

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL)
    private List<Certification> certifications = new ArrayList<>();

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL)
    private List<Matching> matchings = new ArrayList<>();
}
