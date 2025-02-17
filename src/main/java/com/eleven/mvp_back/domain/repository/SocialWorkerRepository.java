package com.eleven.mvp_back.domain.repository;

import com.eleven.mvp_back.domain.entity.SocialWorker;
import com.eleven.mvp_back.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialWorkerRepository extends JpaRepository<SocialWorker, Long> {
    boolean existsByUser(User user);
    Optional<SocialWorker> findByUserId(Long userId);
}
