package com.cloudstorage.user.repository.specification;

import com.cloudstorage.user.model.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class KeycloakUserSpecification implements Specification<UserEntity> {
    private final String keycloakUuid;

    public static Specification<UserEntity> hasKeycloakUuid(String keycloakUuid) {
        return new KeycloakUserSpecification(keycloakUuid);
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return root.get("keycloakUuid").in(keycloakUuid);
    }
}
