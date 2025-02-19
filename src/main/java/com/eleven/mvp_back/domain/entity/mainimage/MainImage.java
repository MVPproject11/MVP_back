package com.eleven.mvp_back.domain.entity.mainimage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "main_images")
public class MainImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_image_id")
    private Long id;

    private String mainPhoto;
}
