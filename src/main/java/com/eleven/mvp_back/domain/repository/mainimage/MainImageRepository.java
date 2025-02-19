package com.eleven.mvp_back.domain.repository.mainimage;

import com.eleven.mvp_back.domain.entity.mainimage.MainImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainImageRepository extends JpaRepository<MainImage, Long> {
    List<MainImage> findTop10ByOrderByIdAsc();
}
