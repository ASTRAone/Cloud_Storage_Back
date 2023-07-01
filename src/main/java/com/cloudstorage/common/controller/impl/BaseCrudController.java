package com.cloudstorage.common.controller.impl;

import com.cloudstorage.common.controller.api.CrudController;
import com.cloudstorage.common.processing.api.ProcessingResult;
import com.cloudstorage.common.service.api.CrudService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseCrudController<DTO> implements CrudController<DTO> {
    private final CrudService<DTO> service;

    @Override
    @GetMapping("{uuid}")
    public ResponseEntity<DTO> get(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(service.find(uuid));
    }

    @Override
    @PostMapping
    public ResponseEntity<ProcessingResult<DTO>> create(@RequestBody DTO dto) {
        return buildResponse(service.create(dto));
    }

    @Override
    @PutMapping
    public ResponseEntity<ProcessingResult<DTO>> update(@RequestBody DTO dto) {
        return buildResponse(service.update(dto));
    }

    @Override
    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> delete(@PathVariable("uuid") String uuid) {
        service.delete(uuid);
        return ResponseEntity.noContent().build();
    }

    protected <T> ResponseEntity<ProcessingResult<T>> buildResponse(ProcessingResult<T> processingResult) {
        if (processingResult.status().isFailed()) {
            return ResponseEntity.unprocessableEntity().body(processingResult);
        }

        return ResponseEntity.ok(processingResult);
    }
}
