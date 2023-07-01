package com.cloudstorage.filestorage.converter;

import com.cloudstorage.common.domain.s3.CommonMultipartFile;
import com.cloudstorage.filestorage.converter.api.Converter;
import io.awspring.cloud.s3.S3Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;

@Service
public class MultipartConverter implements Converter<S3Resource, MultipartFile> {

    @Override
    @SneakyThrows
    public MultipartFile convert(S3Resource s3Object) {
        return CommonMultipartFile.builder()
                .originalFilename(URLDecoder.decode(s3Object.metadata().get("name")))
                .payload(s3Object.getInputStream())
                .name(s3Object.getFilename())
                .contentType(s3Object.contentType())
                .size(s3Object.contentLength())
                .build();
    }
}
