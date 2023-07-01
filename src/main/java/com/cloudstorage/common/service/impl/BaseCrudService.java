package com.cloudstorage.common.service.impl;

import com.cloudstorage.common.exception.NotFoundException;
import com.cloudstorage.common.mapper.api.EntityMapper;
import com.cloudstorage.common.model.EntityModel;
import com.cloudstorage.common.processing.api.ProcessingResult;
import com.cloudstorage.common.processing.impl.SimpleImmutableProcessingResult;
import com.cloudstorage.common.repository.api.EntityRepository;
import com.cloudstorage.common.repository.specification.UuidSpecification;
import com.cloudstorage.common.service.api.CrudService;
import com.cloudstorage.common.validation.api.enumeration.Action;
import com.cloudstorage.common.validation.api.initiator.ValidationInitiator;
import com.cloudstorage.common.validation.model.SimpleValidationResult;
import com.cloudstorage.common.validation.model.ValidationResult;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseCrudService<DTO, ENTITY extends EntityModel<ID>, ID> implements CrudService<DTO> {
    private final EntityRepository<ENTITY, ID> repository;
    private final EntityMapper<ENTITY, DTO> mapper;
    @Autowired(required = false)
    private ValidationInitiator<ENTITY> validationInitiator;

    @Override
    public DTO find(String uuid) {
        return repository.findOne(UuidSpecification.hasUuid(uuid))
                .map(this::prepareDto)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<DTO> findByUuids(Collection<String> uuids) {
        return repository.findAll(UuidSpecification.hasUuid(uuids)).stream()
                .map(this::prepareDto)
                .toList();
    }

    @Override
    public ProcessingResult<DTO> create(DTO dto) {
        ENTITY entity = mapper.toEntity(dto);

        ValidationResult validationResult = validate(entity, Action.CREATE);
        if (validationResult.status().isFailed()) {
            return SimpleImmutableProcessingResult.ofFailed(validationResult.validationPartResults());
        }

        ENTITY created = repository.save(entity);
        return SimpleImmutableProcessingResult.ofSuccess(prepareDto(created));
    }

    @Override
    public ProcessingResult<DTO> update(DTO dto) {
        ENTITY updatedEntity = mapper.toEntity(dto);

        ValidationResult validationResult = validate(updatedEntity, Action.UPDATE);
        if (validationResult.status().isFailed()) {
            return SimpleImmutableProcessingResult.ofFailed(validationResult.validationPartResults());
        }

        ENTITY dbEntity = repository.findOne(UuidSpecification.hasUuid(updatedEntity.getUuid()))
                .orElseThrow(NotFoundException::new);
        update(dbEntity, updatedEntity);
        ENTITY savedEntity = repository.save(dbEntity);
        DTO resultDto = prepareDto(savedEntity);
        return SimpleImmutableProcessingResult.ofSuccess(resultDto);
    }

    @Override
    public void delete(String uuid) {
        repository.findOne(UuidSpecification.hasUuid(uuid))
                .ifPresent(repository::delete);
    }

    protected abstract void update(ENTITY dbEntity, ENTITY updatedEntity);

    protected DTO prepareDto(ENTITY entity) {
        return mapper.toDto(entity);
    }

    protected ValidationResult validate(ENTITY entity, Action action) {
        return isNull(validationInitiator)
                ? SimpleValidationResult.success()
                : validationInitiator.initValidations(entity, action);
    }
}
