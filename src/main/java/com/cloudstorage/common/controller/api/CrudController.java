package com.cloudstorage.common.controller.api;

import com.cloudstorage.common.processing.api.ProcessingResult;
import org.springframework.http.ResponseEntity;

public interface CrudController<DTO> {
    ResponseEntity<DTO> get(String uuid);

    ResponseEntity<ProcessingResult<DTO>> create(DTO dto);

    ResponseEntity<ProcessingResult<DTO>> update(DTO dto);

    ResponseEntity<Void> delete(String uuid);
}
