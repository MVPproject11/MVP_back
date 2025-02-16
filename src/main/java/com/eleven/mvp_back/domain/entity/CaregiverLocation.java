package com.eleven.mvp_back.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "caregiver_locations")
public class CaregiverLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caregiver_location_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    @Column(nullable = false, length = 10)
    private String city;

    @Column(nullable = false, length = 10)
    private String district;

    @Column(nullable = false, length = 10)
    private String dong;
}
