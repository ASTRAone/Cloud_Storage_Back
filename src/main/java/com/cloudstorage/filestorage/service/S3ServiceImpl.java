package com.cloudstorage.filestorage.service;

import com.cloudstorage.filestorage.config.S3BucketProperties;
import com.cloudstorage.filestorage.converter.api.Converter;
import com.cloudstorage.filestorage.service.api.S3Service;
import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@EnableConfigurationProperties(S3BucketProperties.class)
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Template s3Template;
    private final Converter<S3Resource, MultipartFile> multipartFileConverter;
    private final Converter<MultipartFile, ObjectMetadata> metadataConverter;
    private final S3BucketProperties s3BucketProperties;

    @SneakyThrows
    @Override
    public String create(@NonNull MultipartFile multipartFile) {
        try (InputStream is = multipartFile.getInputStream()) {
            String uuid = UUID.randomUUID().toString();
            ObjectMetadata metadata = metadataConverter.convert(multipartFile);
            s3Template.upload(bucketName(), uuid, is, metadata);
            return uuid;
        }
    }

    @Override
    public MultipartFile read(String uuid) {
        S3Resource resource = s3Template.download(bucketName(), uuid);
        return multipartFileConverter.convert(resource);
    }

    private String bucketName() {
        return s3BucketProperties.bucketName();
    }

}
