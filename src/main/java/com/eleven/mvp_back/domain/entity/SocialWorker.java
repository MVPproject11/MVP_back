package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.domain.dto.request.SocialWorkerRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "socialworkers")
public class SocialWorker {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "socialworker_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "socialworker_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String centerName;

    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false)
    private boolean ownBathCar;

    @Column(nullable = false)
    private String centerAddress;

    @Column(length = 10)
    private String centerGrade;

    private Integer operationPeriod;

    private String introduction;

    @Column(columnDefinition = "TEXT")
    private String socialworkerProfile;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void updateProfile(String profileUrl) {
        this.socialworkerProfile = profileUrl;
    }

    public void updateInfo(SocialWorkerRequest request) {
        this.centerName = request.centerName();
        this.phoneNumber = request.phoneNumber();
        this.ownBathCar = request.ownBathCar();
        this.centerAddress = request.centerAddress();
        this.centerGrade = request.centerGrade();
        this.operationPeriod = request.operationPeriod();
        this.introduction = request.introduction();
        this.updatedAt = LocalDateTime.now();
    }
}
