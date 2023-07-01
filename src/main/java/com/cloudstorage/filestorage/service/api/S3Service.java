package com.cloudstorage.filestorage.service.api;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String create(MultipartFile multipartFile);

    MultipartFile read(String uuid);
}
