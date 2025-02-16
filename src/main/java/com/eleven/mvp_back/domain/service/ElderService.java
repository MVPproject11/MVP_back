package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.ElderRequest;
import com.eleven.mvp_back.domain.dto.response.ElderResponse;
import com.eleven.mvp_back.domain.entity.*;
import com.eleven.mvp_back.domain.repository.*;
import com.eleven.mvp_back.exception.BadRequestException;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElderService {
    private final SocialWorkerRepository socialWorkerRepository;
    private final ElderRepository elderRepository;
    private final ElderCareDaysRepository elderCareDaysRepository;
    private final ElderMealAssistRepository elderMealAssistRepository;
    private final ElderExcretionAssistRepository elderExcretionAssistRepository;
    private final ElderMoveAssistRepository elderMoveAssistRepository;
    private final ElderDailyLivingAssistRepository elderDailyLivingAssistRepository;
    private final SocialworkerElderRepository socialworkerElderRepository;

    @Transactional
    public ElderResponse registerElder(Long socialWorkerId, ElderRequest elderRequest) {
        SocialWorker socialWorker = socialWorkerRepository.findById(socialWorkerId)
                .orElseThrow(() -> new ResourceNotFoundException("사회복지사 정보를 찾을 수 없습니다.", socialWorkerId));

        Elder elder = elderRequest.toElder();
        Elder newElder = elderRepository.save(elder);
        Optional<Elder> elderSaved = elderRepository.findById(newElder.getId());
        Elder savedElder = elderSaved.get();

        elderRequest.getCareDays().forEach(day -> savedElder.getCareDays()
                .add(ElderCareDays.builder()
                        .elder(savedElder)
                        .dayOfWeek(day)
                        .build()));
        System.out.println("savedElder.careDays: " + savedElder.getCareDays());

        elderRequest.getMealAssists().forEach(meal -> savedElder.getMealAssists()
                .add(ElderMealAssist.builder()
                        .elder(savedElder)
                        .mealServiceName(meal)
                        .build()));

        elderRequest.getExcretionAssists().forEach(excretion -> savedElder.getExcretionAssists()
                .add(ElderExcretionAssist.builder()
                        .elder(savedElder)
                        .excretionServiceName(excretion)
                        .build()));

        elderRequest.getMoveAssists().forEach(move -> savedElder.getMoveAssists()
                .add(ElderMoveAssist.builder()
                        .elder(savedElder)
                        .moveServiceName(move)
                        .build()));

        elderRequest.getDailyLivingAssists().forEach(living -> savedElder.getDailyLivingAssists()
                .add(ElderDailyLivingAssist.builder()
                        .elder(savedElder)
                        .dailyLivingServiceName(living)
                        .build()));

        savedElder.getSocialworkerElder().add(SocialworkerElder.builder()
                .elder(savedElder)
                .socialWorker(socialWorker)
                .build());

        elderRepository.save(savedElder);

        return ElderResponse.fromEntity(savedElder);
    }

    public List<ElderResponse> getEldersBySocialWorker(Long socialWorkerId) {
        SocialWorker socialWorker = socialWorkerRepository.findById(socialWorkerId)
                .orElseThrow(() -> new ResourceNotFoundException("사회복지사 정보를 찾을 수 없습니다.", socialWorkerId));

        return socialworkerElderRepository.findBySocialWorkerId(socialWorkerId).stream()
                .map(socialworkerElder -> ElderResponse.fromEntity(socialworkerElder.getElder()))
                .collect(Collectors.toList());
    }

    public ElderResponse getElderById(Long elderId) {
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new ResourceNotFoundException("어르신 정보를 찾을 수 없습니다.", elderId));
        return ElderResponse.fromEntity(elder);
    }

    @Transactional
    public ElderResponse updateElder(Long elderId, Long newSocialWorkerId, ElderRequest elderRequest) {
        // 1. 노인 존재 여부 확인
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new ResourceNotFoundException("어르신 정보를 찾을 수 없습니다.", elderId));

        // 2. 요청 Body에서 기존 사회복지사 ID 추출
        Long existingSocialWorkerId = elderRequest.getSocialWorkerId();

        // 3. 기존 사회복지사 존재 여부 확인
        SocialWorker existingSocialWorker = socialWorkerRepository.findById(existingSocialWorkerId)
                .orElseThrow(() -> new ResourceNotFoundException("기존 사회복지사 정보를 찾을 수 없습니다.", existingSocialWorkerId));

        // 4. 신규 사회복지사 존재 여부 확인
        SocialWorker newSocialWorker = socialWorkerRepository.findById(newSocialWorkerId)
                .orElseThrow(() -> new ResourceNotFoundException("신규 사회복지사 정보를 찾을 수 없습니다.", newSocialWorkerId));

        // 5. 기존과 신규 사회복지사가 같은 경우 기본 정보만 업데이트
        if (existingSocialWorkerId.equals(newSocialWorkerId)) {
            elder.updateFromRequest(elderRequest);
            return ElderResponse.fromEntity(elder);
        }

        // 6. 기존 사회복지사와의 관계 삭제
        SocialworkerElder existingRelation = elder.getSocialworkerElder().stream()
                .filter(relation -> relation.getSocialWorker().getId().equals(existingSocialWorkerId))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("기존 사회복지사와의 연결이 없습니다."));

        socialworkerElderRepository.delete(existingRelation);

        // 7. 신규 사회복지사와의 관계 추가
        SocialworkerElder newRelation = SocialworkerElder.builder()
                .id(new SocialworkerElderId(newSocialWorkerId, elder.getId()))
                .socialWorker(newSocialWorker)
                .elder(elder)
                .build();
        socialworkerElderRepository.save(newRelation);

        elder.updateFromRequest(elderRequest);

        return ElderResponse.fromEntity(elder);
    }

    @Transactional
    public void deleteElder(Long elderId) {
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new ResourceNotFoundException("어르신 정보를 찾을 수 없습니다.", elderId));

        elderRepository.deleteById(elderId);
    }
}
