package com.cloudstorage.user.repository;

import com.cloudstorage.common.repository.impl.BaseEntityRepository;
import com.cloudstorage.user.model.UserEntity;
import com.cloudstorage.user.repository.api.JpaUserEntityRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserEntityRepositoryImpl
        extends BaseEntityRepository<UserEntity, Long>
        implements JpaUserEntityRepository {
    protected JpaUserEntityRepositoryImpl(EntityManager entityManager) {
        super(UserEntity.class, entityManager);
    }
}
