package com.cloudstorage.user.service.crud;

import com.cloudstorage.common.service.impl.BaseCrudService;
import com.cloudstorage.user.api.dto.UserDto;
import com.cloudstorage.user.mapper.UserEntityMapper;
import com.cloudstorage.user.model.UserEntity;
import com.cloudstorage.user.repository.api.JpaUserEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        extends BaseCrudService<UserDto, UserEntity, Long>
        implements UserService {
    protected UserServiceImpl(JpaUserEntityRepository repository, UserEntityMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void update(UserEntity dbEntity, UserEntity updatedEntity) {
        dbEntity.setData(updatedEntity.getData());
    }
}
