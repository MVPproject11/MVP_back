package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.domain.dto.response.elder.ElderDailyLivingAssistsResponse;
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

    public ElderDailyLivingAssistsResponse toResponse() {
        return new ElderDailyLivingAssistsResponse(this.getDailyLivingServiceName());
    }
}
