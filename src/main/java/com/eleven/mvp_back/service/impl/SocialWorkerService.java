package com.eleven.mvp_back.service.impl;

import com.eleven.mvp_back.domain.entity.Caregiver;
import com.eleven.mvp_back.domain.entity.SocialWorker;
import com.eleven.mvp_back.domain.repository.SocialWorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocialWorkerService {

    private final SocialWorkerRepository socialWorkerRepository;

    public Optional<SocialWorker> findSocialWorkerById(Long id) {
        return socialWorkerRepository.findById(id);
    }

    public SocialWorker saveSocialWorker(SocialWorker socialWorker) {
        Caregiver caregiver = Caregiver.builder().id(3L).build();
        return socialWorkerRepository.save(socialWorker);
    }
}
