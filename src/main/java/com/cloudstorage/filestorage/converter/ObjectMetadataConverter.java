package com.cloudstorage.filestorage.converter;

import com.cloudstorage.common.utils.StringUtils;
import com.cloudstorage.filestorage.config.S3BucketProperties;
import com.cloudstorage.filestorage.converter.api.Converter;
import io.awspring.cloud.s3.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ObjectMetadataConverter implements Converter<MultipartFile, ObjectMetadata> {
    private final S3BucketProperties s3BucketProperties;

    @Override
    public ObjectMetadata convert(MultipartFile multipartFile) {
        return ObjectMetadata.builder()
                .metadata("name", StringUtils.encodeToURL(multipartFile.getName()))
                .metadata("author", StringUtils.encodeToURL("Пользователь"))
                .contentType(multipartFile.getContentType())
                .metadata("dateTime", s3BucketProperties.dateTimeFormatter().format(LocalDateTime.now()))
                .build();
    }
}
