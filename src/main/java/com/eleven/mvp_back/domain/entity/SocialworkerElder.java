package com.eleven.mvp_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "socialworkers_elders")
public class SocialworkerElder {

    @EmbeddedId
    private SocialworkerElderId id;

    @MapsId("socialworkerId")
    @ManyToOne
    @JoinColumn(name = "socialworker_id", nullable = false)
    private SocialWorker socialWorker;

    @MapsId("elderId")
    @ManyToOne
    @JoinColumn(name = "elder_id", nullable = false)
    private Elder elder;
}
