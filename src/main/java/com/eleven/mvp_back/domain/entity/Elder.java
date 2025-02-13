package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.enums.Gender;
import com.eleven.mvp_back.enums.Housemate;
import com.eleven.mvp_back.enums.SymptomsDementia;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "elders")
public class Elder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Gender gender;

    @Column(length = 10)
    private String careGrade;

    @Column(columnDefinition = "TEXT")
    private String elderPhoto;

    @Column(nullable = false, length = 255)
    private String elderAddress;

    @Column(nullable = false)
    private Integer weight;

    @Column(length = 255)
    private String disease;

    @Column(nullable = false, length = 255) // 단일선택
    private Housemate housemate;

    @Column(name = "symptoms_dementia", nullable = false, length = 255) // 복수선택
    private SymptomsDementia symptomsDementia;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    public static Elder of(String name, LocalDate birth, Gender gender, String careGrade,
                           String elderPhoto, String elderAddress, Integer weight, String disease,
                           Housemate housemate, SymptomsDementia symptomsDementia) {
        return Elder.builder()
                .name(name)
                .birth(birth)
                .gender(gender)
                .careGrade(careGrade)
                .elderPhoto(elderPhoto)
                .elderAddress(elderAddress)
                .weight(weight)
                .disease(disease)
                .housemate(housemate)
                .symptomsDementia(symptomsDementia)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
