package com.eleven.mvp_back.domain.dto.request.matching;

import com.eleven.mvp_back.domain.enums.ProgressStatus;
import jakarta.validation.constraints.NotNull;

public record ProgressStatusRequest(
        @NotNull
        ProgressStatus progressStatus
) {
}
