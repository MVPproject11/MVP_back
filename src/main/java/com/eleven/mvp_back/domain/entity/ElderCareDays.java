package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.domain.enums.Weekday;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "elder_care_days")
public class ElderCareDays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "elder_care_days_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "elder_id", nullable = false)
    private Elder elder;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", length = 3)
    private Weekday dayOfWeek;

    // 새로운 생성자 추가
    public ElderCareDays(Elder elder, Weekday dayOfWeek) {
        this.elder = elder;
        this.dayOfWeek = dayOfWeek;
    }
}
