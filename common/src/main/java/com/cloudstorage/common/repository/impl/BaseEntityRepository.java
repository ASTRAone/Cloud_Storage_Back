package com.cloudstorage.common.repository.impl;

import jakarta.persistence.EntityManager;
import com.cloudstorage.common.repository.api.EntityRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public abstract class BaseEntityRepository<ENTITY, ID>
        extends SimpleJpaRepository<ENTITY, ID>
        implements EntityRepository<ENTITY, ID> {
    protected BaseEntityRepository(Class<ENTITY> entityClass,
                                   EntityManager entityManager) {
        super(entityClass, entityManager);
    }
}
