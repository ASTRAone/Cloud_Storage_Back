package com.cloudstorage.common.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageableService<DTO> {
    Page<DTO> findPage(Pageable pageable);
}
