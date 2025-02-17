package com.eleven.mvp_back.domain.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String uploadFile(MultipartFile image) throws IOException;
    void deleteFile(String fileUrl);
}
