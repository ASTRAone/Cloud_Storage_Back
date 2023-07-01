package com.cloudstorage.common.service.impl;

import com.cloudstorage.common.mapper.api.EntityMapper;
import com.cloudstorage.common.repository.api.EntityRepository;
import com.cloudstorage.common.service.api.PageableService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BasePageableService<DTO, ENTITY, ID> implements PageableService<DTO> {
    private final EntityRepository<ENTITY, ID> repository;
    private final EntityMapper<ENTITY, DTO> mapper;

    @Override
    public Page<DTO> findPage(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::prepareDto);
    }

    protected DTO prepareDto(ENTITY entity) {
        return mapper.toDto(entity);
    }
}
