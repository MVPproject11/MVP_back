package com.eleven.mvp_back.domain.controller;

import com.eleven.mvp_back.domain.entity.JobPost;
import com.eleven.mvp_back.domain.dto.JobPostDTO;
import com.eleven.mvp_back.domain.service.JobPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobposts")
public class JobPostController {

    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    // 모든 구인공고 조회
    @GetMapping
    public ResponseEntity<List<JobPostDTO>> getAllJobPosts() {
        return ResponseEntity.ok(jobPostService.getAllJobPosts());
    }

    // 특정 구인공고 조회
    @GetMapping("/{jobpost_id}")
    public ResponseEntity<JobPostDTO> getJobPostById(@PathVariable Long id) {
        Optional<JobPostDTO> jobPost = jobPostService.getJobPostById(id);
        return jobPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 구인공고 추가
    @PostMapping
    public ResponseEntity<JobPostDTO> createJobPost(@RequestBody JobPost jobPost) {
        return ResponseEntity.ok(jobPostService.createJobPost(jobPost));
    }

    // 구인공고 수정
    @PutMapping("/{jobpost_id}")
    public ResponseEntity<JobPostDTO> updateJobPost(@PathVariable Long id, @RequestBody JobPost jobPost) {
        Optional<JobPostDTO> updatedPost = jobPostService.updateJobPost(id, jobPost);
        return updatedPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 구인공고 삭제
    @DeleteMapping("/{jobpost_id}")
    public ResponseEntity<Void> deleteJobPost(@PathVariable Long id) {
        jobPostService.deleteJobPost(id);
        return ResponseEntity.noContent().build();
    }
}
