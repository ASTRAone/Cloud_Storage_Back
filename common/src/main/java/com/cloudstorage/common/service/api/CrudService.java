package com.cloudstorage.common.service.api;

import com.cloudstorage.common.processing.api.ProcessingResult;

public interface CrudService<DTO> {
    DTO find(String uuid);

    ProcessingResult<DTO> create(DTO dto);

    ProcessingResult<DTO> update(DTO dto);

    void delete(String uuid);
}
