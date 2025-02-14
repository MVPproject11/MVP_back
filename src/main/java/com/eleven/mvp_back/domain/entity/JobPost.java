package com.eleven.mvp_back.domain.entity;

import com.eleven.mvp_back.enums.WorkType;
import jakarta.persistence.*;
import lombok.*;

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
}
