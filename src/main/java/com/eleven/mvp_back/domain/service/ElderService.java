package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.common.ApiResponse;
import com.eleven.mvp_back.domain.dto.request.ElderRequest;
import com.eleven.mvp_back.domain.dto.response.ElderResponse;
import com.eleven.mvp_back.domain.entity.*;
import com.eleven.mvp_back.domain.repository.*;
import com.eleven.mvp_back.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        Elder savedElder = elderRepository.save(elderRequest.toElder());

        elderRequest.getCareDays().forEach(day -> elderCareDaysRepository.save(
                ElderCareDays.builder().elder(savedElder).dayOfWeek(day).build()
        ));
        elderRequest.getMealAssists().forEach(service -> elderMealAssistRepository.save(
                ElderMealAssist.builder().elder(savedElder).mealServiceName(service).build()
        ));
        elderRequest.getExcretionAssists().forEach(service -> elderExcretionAssistRepository.save(
                ElderExcretionAssist.builder().elder(savedElder).excretionServiceName(service).build()
        ));
        elderRequest.getMoveAssists().forEach(service -> elderMoveAssistRepository.save(
                ElderMoveAssist.builder().elder(savedElder).moveServiceName(service).build()
        ));
        elderRequest.getDailyLivingAssists().forEach(service -> elderDailyLivingAssistRepository.save(
                ElderDailyLivingAssist.builder().elder(savedElder).dailyLivingServiceName(service).build()
        ));

        SocialWorker socialWorker = socialWorkerRepository.findById(socialWorkerId)
                .orElseThrow(() -> new IllegalArgumentException("사회복지사 정보를 찾을 수 없습니다."));

        socialworkerElderRepository.save(SocialworkerElder.builder()
                .id(new SocialworkerElderId(socialWorkerId, savedElder.getId()))
                .socialWorker(socialWorker)
                .elder(savedElder)
                .build()
        );

        return ElderResponse.fromEntity(savedElder);
    }

    public List<ElderResponse> getEldersBySocialWorker(Long socialWorkerId) {
        return socialworkerElderRepository.findBySocialWorkerId(socialWorkerId).stream()
                .map(socialworkerElder -> ElderResponse.fromEntity(socialworkerElder.getElder()))
                .collect(Collectors.toList());
    }

    public ElderResponse getElderById(Long elderId) {
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new BadRequestException("어르신 정보를 찾을 수 없습니다."));
        return ElderResponse.fromEntity(elder);
    }

    @Transactional
    public ElderResponse updateElder(Long elderId, ElderRequest elderRequest) {
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new BadRequestException("어르신 정보를 찾을 수 없습니다."));
        elder.updateFromRequest(elderRequest);
        return ElderResponse.fromEntity(elder);
    } //수정필요.. 요일 및 복지서비스 내용이 수정되지 않는 문제.

    @Transactional
    public void deleteElder(Long elderId) {
        elderRepository.deleteById(elderId);
    } //노인 담당 사회복지사가 바뀌는거는, 이거 delete로 지우고 새로운 사회복지사로 post해야하나..?
}
