package com.eleven.mvp_back.domain.entity.socialworkerelder;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SocialworkerElderId implements Serializable {

    @Column(name = "socialworker_id")
    private Long socialworkerId;

    @Column(name = "elder_id")
    private Long elderId;
}
