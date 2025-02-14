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

    @Column(length = 10)
    private String careGrade;

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

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}
