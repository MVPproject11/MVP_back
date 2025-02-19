package com.eleven.mvp_back.domain.dto.response.mainimage;

import java.util.List;

public record MainImagesResponse(
        List<MainImageResponse> firstRow,
        List<MainImageResponse> secondRow
) {
}
