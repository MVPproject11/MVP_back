package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.dto.request.jobpost.JobPostRequest;
import com.eleven.mvp_back.domain.dto.response.jobpost.JobPostResponse;

public interface JopPostService {
    JobPostResponse createJobPost(Long socialworkerId, Long elderId, JobPostRequest request);
}
