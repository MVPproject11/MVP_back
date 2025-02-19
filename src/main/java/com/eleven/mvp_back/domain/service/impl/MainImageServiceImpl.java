package com.eleven.mvp_back.domain.service.impl;

import com.eleven.mvp_back.domain.dto.response.mainimage.MainImageResponse;
import com.eleven.mvp_back.domain.dto.response.mainimage.MainImagesResponse;
import com.eleven.mvp_back.domain.entity.mainimage.MainImage;
import com.eleven.mvp_back.domain.repository.mainimage.MainImageRepository;
import com.eleven.mvp_back.domain.service.MainImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainImageServiceImpl implements MainImageService {

    private final MainImageRepository mainImageRepository;

    @Override
    @Cacheable(value = "mainImages")
    public MainImagesResponse getMainImages() {
        List<MainImage> images = mainImageRepository.findTop10ByOrderByIdAsc();

        List<MainImageResponse> responses = images.stream()
                .map(image -> new MainImageResponse(image.getId(), image.getMainPhoto()))
                .collect(Collectors.toList());


        List<MainImageResponse> firstRow = responses.size() >= 5 ? responses.subList(0, 5) : responses;
        List<MainImageResponse> secondRow = responses.size() > 5 ? responses.subList(5, responses.size()) : Collections.emptyList();

        return new MainImagesResponse(firstRow, secondRow);
    }
}
