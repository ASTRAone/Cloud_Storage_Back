package com.cloudstorage.common.repository.api;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<ENTITY, ID> extends JpaRepositoryImplementation<ENTITY, ID> {
}
