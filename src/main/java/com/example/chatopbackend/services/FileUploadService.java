package com.example.chatopbackend.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    String uploadFile(MultipartFile multipartFile) throws IOException;
}