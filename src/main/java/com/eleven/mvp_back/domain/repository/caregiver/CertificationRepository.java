package com.eleven.mvp_back.domain.repository.caregiver;

import com.eleven.mvp_back.domain.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
}
