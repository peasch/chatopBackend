package com.example.chatopbackend.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.example.chatopbackend.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final Cloudinary cloudinary;


    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        return cloudinary.uploader()

                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString(),new EagerTransformation().width(400).height(300).crop("pad"),
                                new EagerTransformation().width(260).height(200).crop("crop").gravity("north")))
                .get("url") // Get the URL of the uploaded file from the response
                .toString();
    }
}
