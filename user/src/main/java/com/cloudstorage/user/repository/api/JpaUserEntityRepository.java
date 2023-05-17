package com.cloudstorage.user.repository.api;

import com.cloudstorage.common.repository.api.EntityRepository;
import com.cloudstorage.user.model.UserEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaUserEntityRepository extends EntityRepository<UserEntity, Long> {
}
