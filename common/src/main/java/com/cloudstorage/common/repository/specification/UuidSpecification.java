package com.cloudstorage.common.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UuidSpecification<T> implements Specification<T> {
    private final Set<String> uuids;

    public static <T> Specification<T> hasUuid(String uuid) {
        return new UuidSpecification<>(Collections.singleton(uuid));
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Assert.notEmpty(uuids, "Uuids must not be empty");
        return root.get("uuid").in(uuids);
    }
}
