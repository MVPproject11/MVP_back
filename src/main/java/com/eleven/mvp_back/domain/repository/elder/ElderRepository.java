package com.eleven.mvp_back.domain.repository.elder;

import com.eleven.mvp_back.domain.entity.Elder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ElderRepository extends JpaRepository<Elder, Long> {

    @Query("select e from Elder e join e.socialworkerElder se where se.socialWorker.id = :socialWorkerId")
    List<Elder> findBySocialworkerId(Long socialWorkerId);

    @Modifying
    @Query("DELETE FROM ElderCareDays e WHERE e.elder.id = :elderId")
    void deleteElderCareDays(Long elderId);

    @Modifying
    @Query("DELETE FROM ElderMealAssist e WHERE e.elder.id = :elderId")
    void deleteElderMealAssists(Long elderId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ElderExcretionAssist e WHERE e.elder.id = :elderId")
    void deleteElderExcretionAssists(Long elderId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ElderMoveAssist e WHERE e.elder.id = :elderId")
    void deleteElderMoveAssists(Long elderId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ElderDailyLivingAssist e WHERE e.elder.id = :elderId")
    void deleteElderDailyLivingAssists(Long elderId);

    // 기존 사회복지사와 노인의 관계만 삭제
    @Modifying
    @Transactional
    @Query("DELETE FROM SocialworkerElder se WHERE se.elder.id = :elderId AND se.socialWorker.id = :socialworkerId")
    void deleteElderSocialworkerRelation(Long elderId, Long socialworkerId);
}

