package com.eleven.mvp_back.domain.service.impl;

import com.eleven.mvp_back.domain.dto.request.caregiver.CaregiverRequest;
import com.eleven.mvp_back.domain.dto.response.caregiver.CaregiverResponse;
import com.eleven.mvp_back.domain.entity.*;
import com.eleven.mvp_back.domain.enums.Role;
import com.eleven.mvp_back.domain.repository.UserRepository;
import com.eleven.mvp_back.domain.repository.caregiver.*;
import com.eleven.mvp_back.domain.service.CaregiverService;
import com.eleven.mvp_back.domain.service.FileUploadService;
import com.eleven.mvp_back.exception.BadRequestException;
import com.eleven.mvp_back.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CaregiverServiceImpl implements CaregiverService {

    private final CaregiverRepository caregiverRepository;
    private final CaregiverAvailableDayRepository caregiverAvailableDayRepository;
    private final CaregiverLocationRepository caregiverLocationRepository;
    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public CaregiverResponse registerCaregiver(CaregiverRequest request, Long userId) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 사용자를 찾을 없습니다."));

        if (!user.getRole().equals(Role.CAREGIVER)) {
            throw new BadRequestException("요양보호사 프로필은 요양보호사만 등록 가능합니다.");
        }

        if (caregiverRepository.existsByUser(user)) {
            throw new BadRequestException("이미 등록된 요양보호사입니다.");
        }

        String profileUrl = (request.caregiverProfile() != null && !request.caregiverProfile().isEmpty())
                ? fileUploadService.uploadFile(request.caregiverProfile()) : null;

        Caregiver caregiver = caregiverRepository.save(request.toEntity(user, profileUrl));

        saveCaregiverDetails(request, caregiver);

        return caregiver.toResponse(
                caregiver.getAvailableDays(),
                caregiver.getLocations(),
                caregiver.getCertifications()
        );
    }

    @Override
    public CaregiverResponse getCaregiverInfo(Long userId) {

        Caregiver caregiver = caregiverRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("등록된 요양보호사 정보를 찾을 수 없습니다."));

        return caregiver.toResponse(caregiver.getAvailableDays(),
                caregiver.getLocations(),
                caregiver.getCertifications()
        );
    }

    @Transactional
    @Override
    public CaregiverResponse updateCaregiverInfo(CaregiverRequest request, Long userId) throws IOException {

        Caregiver caregiver = caregiverRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("등록된 요양보호사 정보를 찾을 수 없습니다."));

        caregiverRepository.deleteCaregiverDetails(caregiver.getId());

        entityManager.flush();

        if (request.caregiverProfile() != null && !request.caregiverProfile().isEmpty()) {
            if (caregiver.getCaregiverProfile() != null) {
                fileUploadService.deleteFile(caregiver.getCaregiverProfile());
            }
            String newProfileUrl = fileUploadService.uploadFile(request.caregiverProfile());
            caregiver.updateProfile(newProfileUrl);
        } else {
            if (caregiver.getCaregiverProfile() != null) {
                fileUploadService.deleteFile(caregiver.getCaregiverProfile());
                caregiver.updateProfile(null);
            }
        }

        caregiver.updateCaregiverInfo(request);

        saveCaregiverDetails(request, caregiver);

        return caregiver.toResponse(
                caregiver.getAvailableDays(),
                caregiver.getLocations(),
                caregiver.getCertifications()
        );
    }

    @Transactional
    @Override
    public void deleteCaregiverInfo(Long userId) {
        Caregiver caregiver = caregiverRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("등록된 요양보호사 정보를 찾을 수 없습니다."));

        if (caregiver.getCaregiverProfile() != null) {
            fileUploadService.deleteFile(caregiver.getCaregiverProfile());
        }

        caregiverRepository.delete(caregiver);
    }

    private void saveCaregiverDetails(CaregiverRequest request, Caregiver caregiver) {
        List<CaregiverAvailableDay> availableDays = request.availableDays().stream()
                .map(day -> day.toEntity(caregiver)).toList();

        caregiverAvailableDayRepository.saveAll(availableDays);

        List<CaregiverLocation> locations = request.locations().stream()
                .map(loc -> loc.toEntity(caregiver)).toList();

        caregiverLocationRepository.saveAll(locations);

        List<Certification> certifications = request.certifications().stream()
                .map(cert -> cert.toEntity(caregiver)).toList();

        certificationRepository.saveAll(certifications);
    }
}
