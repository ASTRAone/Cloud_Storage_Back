package com.cloudstorage.user.service.crud;

import com.cloudstorage.common.exception.NotFoundException;
import com.cloudstorage.common.processing.api.ProcessingResult;
import com.cloudstorage.common.processing.impl.SimpleImmutableProcessingResult;
import com.cloudstorage.common.service.impl.BaseCrudService;
import com.cloudstorage.common.validation.api.enumeration.Action;
import com.cloudstorage.common.validation.model.ValidationResult;
import com.cloudstorage.user.api.dto.KeycloakUserDto;
import com.cloudstorage.user.api.dto.UserDto;
import com.cloudstorage.user.mapper.UserEntityMapper;
import com.cloudstorage.user.model.UserEntity;
import com.cloudstorage.user.repository.api.JpaUserEntityRepository;
import com.cloudstorage.user.repository.specification.KeycloakUserSpecification;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        extends BaseCrudService<UserDto, UserEntity, Long>
        implements UserService {
    private final UserEntityMapper mapper;
    private final JpaUserEntityRepository repository;

    protected UserServiceImpl(JpaUserEntityRepository repository, UserEntityMapper mapper) {
        super(repository, mapper);
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    protected void update(UserEntity dbEntity, UserEntity updatedEntity) {
        dbEntity.setData(updatedEntity.getData());
    }

    @Override
    public ProcessingResult<Void> createUser(KeycloakUserDto userDto) {
        UserEntity entity = mapper.toEntity(userDto.getUser());
        entity.setKeycloakUuid(userDto.getKeycloakUuid());
        ValidationResult validationResult = validate(entity, Action.CREATE);
        if (validationResult.status().isFailed()) {
            return SimpleImmutableProcessingResult.ofFailed(validationResult.validationPartResults());
        }
        repository.save(entity);
        return SimpleImmutableProcessingResult.ofSuccess();
    }

    @Override
    public UserDto findByKeycloakUuid(String keycloakUuid) {
        return repository.findOne(KeycloakUserSpecification.hasKeycloakUuid(keycloakUuid))
                .map(this::prepareDto)
                .orElseThrow(NotFoundException::new);
    }
}
