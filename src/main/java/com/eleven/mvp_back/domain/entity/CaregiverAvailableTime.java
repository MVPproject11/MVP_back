package com.eleven.mvp_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "caregiver_availabletimes")
public class CaregiverAvailableTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caregiver_availabletime_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    @Column(name = "available_day", nullable = false, length = 3)
    private String availableDay;

    @Column(name = "avaliable_time", nullable = false, length = 10)
    private String availableTime;
}
