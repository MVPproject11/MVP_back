package com.eleven.mvp_back.domain.entity.caregiver;

import com.eleven.mvp_back.domain.dto.response.caregiver.CaregiverAvailableDayResponse;
import com.eleven.mvp_back.domain.enums.Weekday;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "caregiver_availabledays")
public class CaregiverAvailableDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caregiver_availableday_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    @Enumerated(EnumType.STRING)
    @Column(name = "available_day", nullable = false, length = 3)
    private Weekday availableDay;

    public CaregiverAvailableDayResponse toResponse() {
        return new CaregiverAvailableDayResponse(this.getAvailableDay());
    }
}
