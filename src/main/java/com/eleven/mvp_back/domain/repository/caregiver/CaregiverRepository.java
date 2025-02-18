package com.eleven.mvp_back.domain.repository.caregiver;

import com.eleven.mvp_back.domain.entity.caregiver.Caregiver;
import com.eleven.mvp_back.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaregiverRepository extends JpaRepository<Caregiver, Long>, CaregiverCustomRepository {
    boolean existsByUser(User user);
    Optional<Caregiver> findByUserId(Long userId);
}
