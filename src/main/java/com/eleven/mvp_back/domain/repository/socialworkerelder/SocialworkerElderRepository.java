package com.eleven.mvp_back.domain.repository.socialworkerelder;

import com.eleven.mvp_back.domain.entity.socialworkerelder.SocialworkerElder;
import com.eleven.mvp_back.domain.entity.socialworkerelder.SocialworkerElderId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SocialworkerElderRepository extends JpaRepository<SocialworkerElder, SocialworkerElderId> {
    List<SocialworkerElder> findBySocialWorkerId(Long socialWorkerId);
}
