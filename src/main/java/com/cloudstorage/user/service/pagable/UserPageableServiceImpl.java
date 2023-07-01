package com.cloudstorage.user.service.pagable;

import com.cloudstorage.common.service.impl.BasePageableService;
import com.cloudstorage.user.dto.UserDto;
import com.cloudstorage.user.mapper.UserEntityMapper;
import com.cloudstorage.user.model.UserEntity;
import com.cloudstorage.user.repository.api.JpaUserEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPageableServiceImpl
        extends BasePageableService<UserDto, UserEntity, Long>
        implements UserPageableService {

    protected UserPageableServiceImpl(JpaUserEntityRepository repository,
                                      UserEntityMapper mapper) {
        super(repository, mapper);
    }
}
