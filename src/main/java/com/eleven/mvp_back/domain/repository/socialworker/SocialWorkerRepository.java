package com.eleven.mvp_back.domain.repository.socialworker;

import com.eleven.mvp_back.domain.entity.socialworker.SocialWorker;
import com.eleven.mvp_back.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialWorkerRepository extends JpaRepository<SocialWorker, Long> {
    boolean existsByUser(User user);
    Optional<SocialWorker> findByUserId(Long userId);
}
