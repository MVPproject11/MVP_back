package com.eleven.mvp_back.domain.repository.caregiver;

import com.eleven.mvp_back.domain.entity.caregiver.QCaregiverAvailableDay;
import com.eleven.mvp_back.domain.entity.caregiver.QCaregiverLocation;
import com.eleven.mvp_back.domain.entity.caregiver.QCertification;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CaregiverCustomRepositoryImpl implements CaregiverCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteCaregiverDetails(Long caregiverId) {
        QCaregiverAvailableDay qAvailableDay = QCaregiverAvailableDay.caregiverAvailableDay;
        QCaregiverLocation qLocation = QCaregiverLocation.caregiverLocation;
        QCertification qCertification = QCertification.certification;

        queryFactory.delete(qAvailableDay)
                .where(qAvailableDay.caregiver.id.eq(caregiverId))
                .execute();

        queryFactory.delete(qLocation)
                .where(qLocation.caregiver.id.eq(caregiverId))
                .execute();

        queryFactory.delete(qCertification)
                .where(qCertification.caregiver.id.eq(caregiverId))
                .execute();
    }
}
