package com.eleven.mvp_back.domain.dto.request.socialworker;

import com.eleven.mvp_back.domain.entity.SocialWorker;
import com.eleven.mvp_back.domain.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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
        public SocialWorker toEntity(User user, String profileUrl) {
                return SocialWorker.builder()
                        .user(user)
                        .centerName(centerName)
                        .phoneNumber(phoneNumber)
                        .ownBathCar(ownBathCar)
                        .centerAddress(centerAddress)
                        .centerGrade(centerGrade)
                        .operationPeriod(operationPeriod)
                        .introduction(introduction)
                        .socialworkerProfile(profileUrl)
                        .createdAt(LocalDateTime.now())
                        .build();
        }
}
