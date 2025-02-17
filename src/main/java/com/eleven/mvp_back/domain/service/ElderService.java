package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.elder.ElderRequest;
import com.eleven.mvp_back.domain.dto.response.elder.*;
import com.eleven.mvp_back.domain.entity.*;
import com.eleven.mvp_back.domain.repository.elder.*;
import com.eleven.mvp_back.domain.repository.*;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final FileUploadService fileUploadService;
    private final EntityManager entityManager;

    @Transactional
    public ElderResponse registerElder(Long socialWorkerId, ElderRequest elderRequest) throws IOException {
        SocialWorker socialWorker = socialWorkerRepository.findById(socialWorkerId)
                .orElseThrow(() -> new ResourceNotFoundException("사회복지사 정보를 찾을 수 없습니다.", socialWorkerId));


        String profileUrl = (elderRequest.getElderPhoto() != null && !elderRequest.getElderPhoto().isEmpty())
                ? fileUploadService.uploadFile(elderRequest.getElderPhoto()) : null;

        Elder newElder = elderRepository.save(elderRequest.toElder(profileUrl));

        saveElderDetails(elderRequest, newElder, socialWorker);

        return newElder.toResponse(
                newElder.getCareDays(), newElder.getMealAssists(),
                newElder.getExcretionAssists(), newElder.getMoveAssists(),
                newElder.getDailyLivingAssists(), newElder.getSocialworkerElder()
        );
    }

    public List<ElderResponse> getEldersBySocialWorker(Long socialWorkerId) {
        SocialWorker socialWorker = socialWorkerRepository.findById(socialWorkerId)
                .orElseThrow(() -> new ResourceNotFoundException("사회복지사 정보를 찾을 수 없습니다.", socialWorkerId));

        return socialworkerElderRepository.findBySocialWorkerId(socialWorkerId).stream()
                .map(socialworkerElder -> {
                    Elder elder = socialworkerElder.getElder();
                    return elder.toResponse(
                            elder.getCareDays(), elder.getMealAssists(),
                            elder.getExcretionAssists(), elder.getMoveAssists(),
                            elder.getDailyLivingAssists(), elder.getSocialworkerElder()
                    );
                })
                .collect(Collectors.toList());
    }

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
    public ElderResponse updateElder(Long elderId, Long newSocialWorkerId, ElderRequest elderRequest, MultipartFile elderPhoto) throws IOException {
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

        // 5. 기존 연관 관계 전부 삭제
        deleteElderDetails(elder.getId(), existingSocialWorker.getId());

        // 5.5 사진은 따로 수정
        if (elderPhoto != null && !elderPhoto.isEmpty()) {
            if (elder.getElderPhoto() != null) {
                fileUploadService.deleteFile(elder.getElderPhoto());
            }
            String newProfileUrl = fileUploadService.uploadFile(elderPhoto);
            elder.updateProfile(newProfileUrl);
        } else {
            if (elder.getElderPhoto() != null) {
                fileUploadService.deleteFile(elder.getElderPhoto());
                elder.updateProfile(null);
            }
        }

        // 6. 즉시 반영
        entityManager.flush();

        // 7. 기본 정보 업데이트
        elder.updateFromRequest(elderRequest);

        // 8. 연관 정보 다시 입력
        saveElderDetails(elderRequest, elder, newSocialWorker);

        // 9. 반환
        return elder.toResponse(
                elder.getCareDays(), elder.getMealAssists(),
                elder.getExcretionAssists(), elder.getMoveAssists(),
                elder.getDailyLivingAssists(), elder.getSocialworkerElder()
        );
    }

    @Transactional
    public void deleteElder(Long elderId) {
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new ResourceNotFoundException("어르신 정보를 찾을 수 없습니다.", elderId));

        elderRepository.deleteById(elderId);
    }

    private void saveElderDetails(ElderRequest request, Elder elder, SocialWorker socialWorker) {
        List<ElderCareDays> careDays = request.getCareDays().stream()
                .map(day -> day.toEntity(elder)).toList();
        elderCareDaysRepository.saveAll(careDays);

        List<ElderMealAssist> mealAssists = request.getMealAssists().stream()
                .map(meal -> meal.toEntity(elder)).toList();
        elderMealAssistRepository.saveAll(mealAssists);

        List<ElderExcretionAssist> excretionAssists = request.getExcretionAssists().stream()
                .map(excretion -> excretion.toEntity(elder)).toList();
        elderExcretionAssistRepository.saveAll(excretionAssists);

        List<ElderMoveAssist> moveAssists = request.getMoveAssists().stream()
                .map(move -> move.toEntity(elder)).toList();
        elderMoveAssistRepository.saveAll(moveAssists);

        List<ElderDailyLivingAssist> dailyLivingAssists = request.getDailyLivingAssists().stream()
                .map(dailyLiving -> dailyLiving.toEntity(elder)).toList();
        elderDailyLivingAssistRepository.saveAll(dailyLivingAssists);

        SocialworkerElder socialworkerElder = SocialworkerElder.builder()
                .socialWorker(socialWorker)
                .elder(elder)
                .build();
        socialworkerElderRepository.save(socialworkerElder);
    }

    @Transactional
    public void deleteElderDetails(Long elderId, Long existingSocialWorkerId) {
        // 1:N 관계 테이블 데이터 삭제
        elderRepository.deleteElderCareDays(elderId);
        elderRepository.deleteElderMealAssists(elderId);
        elderRepository.deleteElderExcretionAssists(elderId);
        elderRepository.deleteElderMoveAssists(elderId);
        elderRepository.deleteElderDailyLivingAssists(elderId);

        // 기존 사회복지사와의 관계만 삭제
        elderRepository.deleteElderSocialworkerRelation(elderId, existingSocialWorkerId);
    }
}
