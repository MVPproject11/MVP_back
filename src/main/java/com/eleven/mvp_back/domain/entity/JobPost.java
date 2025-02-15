package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.domain.enums.WorkType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "jobposts")
public class JobPost {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobpost_id")
    private Long id;

    @ManyToOne
    @JoinColumns(
            {@JoinColumn(name = "socialworker_id", referencedColumnName = "socialworker_id"),
                    @JoinColumn(name = "elder_id", referencedColumnName = "elder_id")
            })
    private SocialworkerElder socialworkerElder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private WorkType workType;

    @Column(nullable = false)
    private String workAddress;

    @Column(nullable = false)
    private boolean isTimeNegotiable;

    @Column(nullable = false, length = 10)
    private String wageType;

    @Column(name = "wage_amount", nullable = false)
    private int wageAmount;

    // 복수선택이지만 단순 정보 저장용으로 콤마로 구분하여 문자열로 저장
    // dto에서는 Set<Benefit> 으로 받으면 될듯
    @Column(name = "benefits", length = 255)
    private String benefits;

    @Column(name = "need_member")
    private Integer needMember;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "post_start_time", nullable = false)
    private LocalDateTime postStartTime;

    @Column(name = "post_end_time", nullable = false)
    private LocalDateTime postEndTime;

    private String managerPhone;

    @Column(nullable = false)
    private String managerEmail;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
