package com.eleven.mvp_back.domain.entity.elder;

import com.eleven.mvp_back.domain.dto.response.elder.ElderCareDaysResponse;
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

    public ElderCareDaysResponse toResponse() {
        return new ElderCareDaysResponse(this.getDayOfWeek());
    }
}
