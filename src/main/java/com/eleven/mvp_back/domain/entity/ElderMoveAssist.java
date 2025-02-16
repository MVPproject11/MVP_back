package com.eleven.mvp_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "elder_move_assists")
public class ElderMoveAssist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "elder_move_assist_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "elder_id", nullable = false)
    private Elder elder;

    @Column(nullable = false, length = 50)
    private String moveServiceName;

    // 새로운 생성자 추가
    public ElderMoveAssist(Elder elder, String moveServiceName) {
        this.elder = elder;
        this.moveServiceName = moveServiceName;
    }
}
