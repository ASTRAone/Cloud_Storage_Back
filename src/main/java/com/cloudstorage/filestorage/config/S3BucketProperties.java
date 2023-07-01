package com.cloudstorage.filestorage.config;

import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.format.DateTimeFormatter;

@ConfigurationProperties(prefix = "s3")
public record S3BucketProperties(@NonNull String bucketName) {
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ISO_DATE_TIME;
    }

}
