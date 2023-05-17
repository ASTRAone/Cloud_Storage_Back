package com.cloudstorage.common.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PageableController<DTO> {
    ResponseEntity<Page<DTO>> findPage(Pageable pageable);
}
