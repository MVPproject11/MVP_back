package com.eleven.mvp_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "socialworkers")
public class SocialWorker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socialworker_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String centerName;

    @Column(nullable = false, length = 13)
    private String phoneNumber;

    @Column(nullable = false)
    private boolean ownBathCar;

    @Column(nullable = false, length = 255)
    private String centerAddress;

    @Column(length = 10)
    private String centerGrade;

    private Integer operationPeriod;

    @Column(length = 255)
    private String introduction;

    @Column(columnDefinition = "TEXT")
    private String socialworkerProfile;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "socialworker_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public static SocialWorker of(String centerName, String phoneNumber, boolean ownBathCar,
                                  String centerAddress, String centerGrade, Integer operationPeriod,
                                  String introduction, String socialworkerProfile) {
        return SocialWorker.builder()
                .centerName(centerName)
                .phoneNumber(phoneNumber)
                .ownBathCar(ownBathCar)
                .centerAddress(centerAddress)
                .centerGrade(centerGrade)
                .operationPeriod(operationPeriod)
                .introduction(introduction)
                .socialworkerProfile(socialworkerProfile)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
