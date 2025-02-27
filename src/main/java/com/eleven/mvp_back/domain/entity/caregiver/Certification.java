package com.eleven.mvp_back.domain.entity.caregiver;

import com.eleven.mvp_back.domain.dto.response.caregiver.CertificationResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    // 자격증타입 (명칭)
    @Column(name = "certification_type", nullable = false, length = 20)
    private String certificationType;

    @Column(name = "certification_grade", nullable = false, length = 20)
    private String certificationGrade;

    @Column(name = "certification_number", nullable = false, length = 20)
    private String certificationNumber;

    public CertificationResponse toResponse() {
        return new CertificationResponse(this.getCertificationType(), this.getCertificationGrade(), this.getCertificationNumber());
    }
}
