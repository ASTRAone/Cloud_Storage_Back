package com.cloudstorage.common.domain.s3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;


@AllArgsConstructor
@Builder
@Data
public class CommonMultipartFile implements MultipartFile {
    private final String name;
    private final String originalFilename;
    private final String contentType;
    private final InputStream payload;
    private final long size;

    @SneakyThrows
    public CommonMultipartFile(String originalFilename, String name, InputStream payload) {
        this.size = payload.available();
        this.originalFilename = originalFilename;
        this.payload = payload;
        this.name = name;
        this.contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return FileCopyUtils.copyToByteArray(payload);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return payload;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        transferTo(dest.toPath());
    }

    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {
        FileCopyUtils.copy(payload, Files.newOutputStream(dest));
    }

}
