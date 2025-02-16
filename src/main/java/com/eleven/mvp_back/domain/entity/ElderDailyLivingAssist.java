package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.domain.enums.Weekday;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "elder_daily_living_assists")
public class ElderDailyLivingAssist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "elder_daily_living_assist_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "elder_id", nullable = false)
    private Elder elder;

    @Column(length = 50)
    private String dailyLivingServiceName;

    // 새로운 생성자 추가
    public ElderDailyLivingAssist(Elder elder, String dailyLivingServiceName) {
        this.elder = elder;
        this.dailyLivingServiceName = dailyLivingServiceName;
    }
}
