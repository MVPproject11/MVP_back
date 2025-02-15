package com.eleven.mvp_back.domain.service;

import com.eleven.mvp_back.domain.entity.JobPost;
import com.eleven.mvp_back.domain.dto.JobPostDTO;
import com.eleven.mvp_back.domain.repository.JobPostRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public JobPostService(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    // 모든 구인공고 조회
    public List<JobPostDTO> getAllJobPosts() {
        return jobPostRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 구인공고 조회
    public Optional<JobPostDTO> getJobPostById(Long id) {
        return jobPostRepository.findById(id).map(this::convertToDTO);
    }

    // 구인공고 추가
    public JobPostDTO createJobPost(JobPost jobPost) {
        JobPost savedPost = jobPostRepository.save(jobPost);
        return convertToDTO(savedPost);
    }

    // 구인공고 수정
    public Optional<JobPostDTO> updateJobPost(Long id, JobPost jobPostDetails) {
        return jobPostRepository.findById(id).map(jobPost -> {
            jobPost.setWorkType(jobPostDetails.getWorkType());
            jobPost.setWorkAddress(jobPostDetails.getWorkAddress());
            jobPost.setTimeNegotiable(jobPostDetails.isTimeNegotiable());
            jobPost.setWageType(jobPostDetails.getWageType());
            jobPost.setWageAmount(jobPostDetails.getWageAmount());
            jobPost.setBenefits(jobPostDetails.getBenefits());
            jobPost.setNeedMember(jobPostDetails.getNeedMember());
            jobPost.setStatus(jobPostDetails.getStatus());
            jobPost.setPostStartTime(jobPostDetails.getPostStartTime());
            jobPost.setPostEndTime(jobPostDetails.getPostEndTime());
            jobPost.setManagerPhone(jobPostDetails.getManagerPhone());
            jobPost.setManagerEmail(jobPostDetails.getManagerEmail());
            jobPost.setUpdatedAt(LocalDateTime.now());

            return convertToDTO(jobPostRepository.save(jobPost));
        });
    }

    // 구인공고 삭제
    public void deleteJobPost(Long id) {
        jobPostRepository.deleteById(id);
    }

    // Entity → DTO 변환
    private JobPostDTO convertToDTO(JobPost jobPost) {
        return JobPostDTO.builder()
                .id(jobPost.getId())
                .socialworkerId(jobPost.getSocialworkerElder().getSocialWorker().getId())
                .elderId(jobPost.getSocialworkerElder().getElder().getId())
                .workType(jobPost.getWorkType())
                .workAddress(jobPost.getWorkAddress())
                .isTimeNegotiable(jobPost.isTimeNegotiable())
                .wageType(jobPost.getWageType())
                .wageAmount(jobPost.getWageAmount())
                .benefits(jobPost.getBenefits())
                .needMember(jobPost.getNeedMember())
                .status(jobPost.getStatus())
                .postStartTime(jobPost.getPostStartTime())
                .postEndTime(jobPost.getPostEndTime())
                .managerPhone(jobPost.getManagerPhone())
                .managerEmail(jobPost.getManagerEmail())
                .createdAt(jobPost.getCreatedAt())
                .updatedAt(jobPost.getUpdatedAt())
                .build();
    }
}
