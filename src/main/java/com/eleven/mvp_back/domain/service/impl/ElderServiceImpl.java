package com.eleven.mvp_back.domain.service.impl;

import com.eleven.mvp_back.domain.dto.request.elder.ElderRequest;
import com.eleven.mvp_back.domain.dto.response.elder.*;
import com.eleven.mvp_back.domain.entity.elder.*;
import com.eleven.mvp_back.domain.entity.socialworker.SocialWorker;
import com.eleven.mvp_back.domain.entity.socialworkerelder.SocialworkerElder;
import com.eleven.mvp_back.domain.entity.socialworkerelder.SocialworkerElderId;
import com.eleven.mvp_back.domain.repository.elder.*;
import com.eleven.mvp_back.domain.repository.socialworker.SocialWorkerRepository;
import com.eleven.mvp_back.domain.repository.socialworkerelder.SocialworkerElderRepository;
import com.eleven.mvp_back.domain.service.ElderService;
import com.eleven.mvp_back.domain.service.FileUploadService;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ElderServiceImpl implements ElderService {

    private final SocialWorkerRepository socialWorkerRepository;
    private final ElderRepository elderRepository;
    private final ElderCareDaysRepository elderCareDaysRepository;
    private final ElderMealAssistRepository elderMealAssistRepository;
    private final ElderExcretionAssistRepository elderExcretionAssistRepository;
    private final ElderMoveAssistRepository elderMoveAssistRepository;
    private final ElderDailyLivingAssistRepository elderDailyLivingAssistRepository;
    private final SocialworkerElderRepository socialworkerElderRepository;
    private final FileUploadService fileUploadService;
    private final EntityManager entityManager;

    @Transactional
    @Override
    public ElderResponse registerElder(Long socialWorkerId, ElderRequest elderRequest) throws IOException {
        SocialWorker socialWorker = socialWorkerRepository.findById(socialWorkerId)
                .orElseThrow(() -> new ResourceNotFoundException("사회복지사 정보를 찾을 수 없습니다."));

        String profileUrl = (elderRequest.elderPhoto() != null && !elderRequest.elderPhoto().isEmpty())
                ? fileUploadService.uploadFile(elderRequest.elderPhoto()) : null;

        Elder elder = elderRepository.save(elderRequest.toEntity(profileUrl));

        saveElderDetails(elderRequest, elder, socialWorker);

        return elder.toResponse(
                elder.getCareDays(), elder.getMealAssists(),
                elder.getExcretionAssists(), elder.getMoveAssists(),
                elder.getDailyLivingAssists(), elder.getSocialworkerElder()
        );
    }

    @Override
    public List<ElderResponse> getEldersBySocialWorker(Long socialWorkerId) {
        List<Elder> elders = elderRepository.findBySocialworkerId(socialWorkerId);
        return elders.stream()
                .map(elder -> elder.toResponse(
                        elder.getCareDays(),
                        elder.getMealAssists(),
                        elder.getExcretionAssists(),
                        elder.getMoveAssists(),
                        elder.getDailyLivingAssists(),
                        elder.getSocialworkerElder()
                ))
                .toList();
    }

    @Override
    public ElderResponse getElderById(Long elderId) {
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new ResourceNotFoundException("어르신 정보를 찾을 수 없습니다.", elderId));

        return elder.toResponse(
                elder.getCareDays(), elder.getMealAssists(),
                elder.getExcretionAssists(), elder.getMoveAssists(),
                elder.getDailyLivingAssists(), elder.getSocialworkerElder()
        );
    }

    @Transactional
    @Override
    public ElderResponse updateElder(Long elderId, Long socialWorkerId, ElderRequest elderRequest) throws IOException {

        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new ResourceNotFoundException("어르신 정보를 찾을 수 없습니다."));

        if (elderRequest.elderPhoto() != null && !elderRequest.elderPhoto().isEmpty()) {
            if (elder.getElderPhoto() != null) {
                fileUploadService.deleteFile(elder.getElderPhoto());
            }
            String newProfileUrl = fileUploadService.uploadFile(elderRequest.elderPhoto());
            elder.updateProfile(newProfileUrl);
        } else {
            if (elder.getElderPhoto() != null) {
                fileUploadService.deleteFile(elder.getElderPhoto());
                elder.updateProfile(null);
            }
        }

        elder.updateFromRequest(elderRequest);
        elderRepository.save(elder);

        elderRepository.deleteElderCareDays(elderId);
        elderRepository.deleteElderMealAssists(elderId);
        elderRepository.deleteElderExcretionAssists(elderId);
        elderRepository.deleteElderMoveAssists(elderId);
        elderRepository.deleteElderDailyLivingAssists(elderId);
        elderRepository.deleteElderSocialworkerRelation(elderId, socialWorkerId);

        entityManager.flush();

        SocialWorker socialWorker = socialWorkerRepository.findById(socialWorkerId)
                .orElseThrow(() -> new ResourceNotFoundException("사회복지사 정보를 찾을 수 없습니다."));
        saveElderDetails(elderRequest, elder, socialWorker);

        return elder.toResponse(
                elder.getCareDays(), elder.getMealAssists(),
                elder.getExcretionAssists(), elder.getMoveAssists(),
                elder.getDailyLivingAssists(), elder.getSocialworkerElder()
        );
    }

    @Transactional
    public void deleteElder(Long elderId) {
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new ResourceNotFoundException("어르신 정보를 찾을 수 없습니다."));

        elderRepository.delete(elder);
    }

    private void saveElderDetails(ElderRequest request, Elder elder, SocialWorker socialWorker) {
        List<ElderCareDays> careDays = request.careDays().stream()
                .map(day -> day.toEntity(elder)).toList();
        elderCareDaysRepository.saveAll(careDays);

        List<ElderMealAssist> mealAssists = request.mealAssists().stream()
                .map(meal -> meal.toEntity(elder)).toList();
        elderMealAssistRepository.saveAll(mealAssists);

        List<ElderExcretionAssist> excretionAssists = request.excretionAssists().stream()
                .map(excretion -> excretion.toEntity(elder)).toList();
        elderExcretionAssistRepository.saveAll(excretionAssists);

        List<ElderMoveAssist> moveAssists = request.moveAssists().stream()
                .map(move -> move.toEntity(elder)).toList();
        elderMoveAssistRepository.saveAll(moveAssists);

        List<ElderDailyLivingAssist> dailyLivingAssists = request.dailyLivingAssists().stream()
                .map(dailyLiving -> dailyLiving.toEntity(elder)).toList();
        elderDailyLivingAssistRepository.saveAll(dailyLivingAssists);

        SocialworkerElderId socialworkerElderId = new SocialworkerElderId(socialWorker.getId(), elder.getId());
        SocialworkerElder socialworkerElder = SocialworkerElder.builder()
                .id(socialworkerElderId)
                .socialWorker(socialWorker)
                .elder(elder)
                .build();
        socialworkerElderRepository.save(socialworkerElder);
    }
}
