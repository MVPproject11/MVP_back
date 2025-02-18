package com.eleven.mvp_back.domain.repository.jobpost;

import com.eleven.mvp_back.domain.entity.caregiver.Caregiver;
import com.eleven.mvp_back.domain.entity.elder.Elder;

import java.util.List;

public interface JobPostCustomRepository {
    List<Caregiver> findCaregiversMatchingElder(Elder elder);
}
