package com.eleven.mvp_back.domain.service.impl;

import com.eleven.mvp_back.domain.dto.request.jobpost.JobPostRequest;
import com.eleven.mvp_back.domain.dto.response.jobpost.JobPostResponse;
import com.eleven.mvp_back.domain.service.JopPostService;
import org.springframework.stereotype.Service;

@Service
public class JopPostServiceImpl implements JopPostService {
    @Override
    public JobPostResponse createJobPost(Long socialworkerId, Long elderId, JobPostRequest request) {
        return null;
    }
}
