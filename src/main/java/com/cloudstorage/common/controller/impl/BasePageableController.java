package com.cloudstorage.common.controller.impl;

import com.cloudstorage.common.controller.api.PageableController;
import com.cloudstorage.common.service.api.PageableService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BasePageableController<DTO>
        implements PageableController<DTO> {
    private final PageableService<DTO> service;

    @Override
    @GetMapping
    public ResponseEntity<Page<DTO>> findPage(Pageable pageable) {
        return ResponseEntity.ok(service.findPage(pageable));
    }
}
