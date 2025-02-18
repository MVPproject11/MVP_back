package com.eleven.mvp_back.domain.entity.socialworkerelder;

import com.eleven.mvp_back.domain.dto.response.elder.SocialWorkerElderResponse;
import com.eleven.mvp_back.domain.entity.elder.Elder;
import com.eleven.mvp_back.domain.entity.socialworker.SocialWorker;
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

    public SocialWorkerElderResponse toResponse() {
        return new SocialWorkerElderResponse(this.getSocialWorker().getId());
    }
}
