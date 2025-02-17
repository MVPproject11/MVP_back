package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.domain.dto.response.elder.ElderMoveAssistsResponse;
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

    public ElderMoveAssistsResponse toResponse() {
        return new ElderMoveAssistsResponse(this.getMoveServiceName());
    }
}
