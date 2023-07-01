package com.cloudstorage.filestorage.controller;

import com.cloudstorage.filestorage.service.api.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file-storage")
@RequiredArgsConstructor
public class FileStorageController {
    private final S3Service service;

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile) {
        String uuid = service.create(multipartFile);
        return ResponseEntity.ok(uuid);
    }

    @GetMapping(value = "{uuid}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Resource> download(@PathVariable String uuid) {
        MultipartFile read = service.read(uuid);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(read.getOriginalFilename()).build().toString())
                .body(read.getResource());
    }
}
