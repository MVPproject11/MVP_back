package com.eleven.mvp_back.domain.repository.jobpost;

import com.eleven.mvp_back.domain.entity.QCaregiver;
import com.eleven.mvp_back.domain.entity.QCaregiverAvailableDay;
import com.eleven.mvp_back.domain.entity.QCaregiverLocation;
import com.eleven.mvp_back.domain.entity.caregiver.Caregiver;
import com.eleven.mvp_back.domain.entity.elder.Elder;
import com.eleven.mvp_back.domain.entity.elder.ElderCareDays;
import com.eleven.mvp_back.domain.enums.Weekday;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.TimePath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JobPostCustomRepositoryImpl implements JobPostCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Caregiver> findCaregiversMatchingElder(Elder elder) {
        QCaregiver caregiver = QCaregiver.caregiver;
        QCaregiverAvailableDay availableDay = QCaregiverAvailableDay.caregiverAvailableDay;
        QCaregiverLocation location = QCaregiverLocation.caregiverLocation;

        LocalTime elderStart = elder.getCareStartTime();
        LocalTime elderEnd = elder.getCareEndTime();

        String elderAddress = elder.getElderAddress();

        Set<Weekday> elderWeekdays = elder.getCareDays().stream()
                .map(ElderCareDays::getDayOfWeek)
                .collect(Collectors.toSet());

        return queryFactory.selectDistinct(caregiver)
                .from(caregiver)
                .join(caregiver.availableDays, availableDay)
                .join(caregiver.locations, location)
                .where(availableDay.availableDay.in(elderWeekdays),
                        timeOverlap(caregiver.availableStartTime, caregiver.availableEndTime, elderStart, elderEnd),
                        locationOverlap(location, elderAddress)
                )
                .fetch();
    }

    private BooleanExpression timeOverlap(TimePath<LocalTime> cgStart, TimePath<LocalTime> cgEnd,
                                          LocalTime elderStart, LocalTime elderEnd) {
        if (elderStart == null || elderEnd == null) {
            return null;
        }

        return cgStart.before(elderEnd).and(cgEnd.after(elderStart));
    }

    private BooleanExpression locationOverlap(QCaregiverLocation location, String elderAddress) {
        if (elderAddress.isBlank()) {
            return null;
        }

        return stringContains(location.city, elderAddress);
    }

    private BooleanExpression stringContains(StringPath path, String elderAddress) {
        return path.isNotNull()
                .and(path.isNotEmpty())
                .and(path.like("%" + elderAddress + "%")
                        .or(path.like(elderAddress + "%")));
    }
}
