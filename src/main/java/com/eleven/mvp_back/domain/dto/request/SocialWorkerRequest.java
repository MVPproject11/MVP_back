package com.eleven.mvp_back.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record SocialWorkerRequest(
        @NotBlank(message = "센터 이름은 필수입니다.")
        String centerName,

        @NotBlank(message = "전화번호는 필수입니다.")
        @Size(max = 11, message = "전화번호는 11자리가 최대입니다.")
        String phoneNumber,

        @NotNull(message = "차량 소유 여부는 필수입니다.")
        Boolean ownBathCar,

        @NotBlank(message = "센터 주소는 필수입니다.")
        String centerAddress,

        String centerGrade,

        Integer operationPeriod,

        String introduction,

        MultipartFile socialworkerProfile
) {
}
