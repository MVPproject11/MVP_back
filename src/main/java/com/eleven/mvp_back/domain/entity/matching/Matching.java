package com.eleven.mvp_back.domain.entity.matching;

import com.eleven.mvp_back.domain.entity.caregiver.Caregiver;
import com.eleven.mvp_back.domain.entity.jobpost.JobPost;
import com.eleven.mvp_back.domain.enums.MatchingStatus;
import com.eleven.mvp_back.domain.enums.ProgressStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "matchings")
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jobpost_id", nullable = false)
    private JobPost jobpost;

    @ManyToOne
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MatchingStatus matchingStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 20)
    private ProgressStatus progressStatus;

    @Column(nullable = false)
    private LocalDate requestDate;

    private LocalDate responseDate;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void updateStatus(ProgressStatus progressStatus, MatchingStatus matchingStatus) {
        this.progressStatus = progressStatus;
        this.matchingStatus = matchingStatus;
        this.updatedAt = LocalDateTime.now();
    }
}
