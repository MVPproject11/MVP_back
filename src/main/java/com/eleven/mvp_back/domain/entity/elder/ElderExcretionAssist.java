package com.eleven.mvp_back.domain.entity.elder;

import com.eleven.mvp_back.domain.dto.response.elder.ElderExcretionAssistsResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "elder_excretion_assists")
public class ElderExcretionAssist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "elder_excretion_assist_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "elder_id", nullable = false)
    private Elder elder;

    @Column(nullable = false, length = 50)
    private String excretionServiceName;

    public ElderExcretionAssistsResponse toResponse() {
        return new ElderExcretionAssistsResponse(this.getExcretionServiceName());
    }
}
