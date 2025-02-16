package com.eleven.mvp_back.domain.entity;

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

    // 새로운 생성자 추가
    public ElderExcretionAssist(Elder elder, String excretionServiceName) {
        this.elder = elder;
        this.excretionServiceName = excretionServiceName;
    }
}
