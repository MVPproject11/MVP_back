package com.eleven.mvp_back.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponse<T> {
    private int code;
    private String message;
    private T data;

    //200-OK
    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.<T>builder()
                .code(HttpStatus.OK.value())
                .message("Success")
                .data(data)
                .build();
    }

    //200-OK
    public static <T> CommonResponse<T> success(String message, T data) {
        return CommonResponse.<T>builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build();
    }

    //201-CREATED
    public static <T> CommonResponse<T> created(String message, T data) {
        return CommonResponse.<T>builder()
                .code(HttpStatus.CREATED.value())
                .message(message)
                .data(data)
                .build();
    }

    //204-NO CONTENT
    public static <T> CommonResponse<T> noContent(String message) {
        return CommonResponse.<T>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message(message)
                .data(null)
                .build();
    }

    //에러
    public static <T> CommonResponse<T> error(int code, String message) {
        return CommonResponse.<T>builder()
                .code(code)
                .message(message)
                .data(null)
                .build();
    }

}
