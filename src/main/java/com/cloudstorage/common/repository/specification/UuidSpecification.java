package com.cloudstorage.common.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class UuidSpecification<T> implements Specification<T> {
    private final Set<String> uuids;

    private UuidSpecification(Collection<String> uuids) {
        Assert.notEmpty(uuids, "Uuids must not be null");
        this.uuids = Set.copyOf(uuids);
    }

    public static <T> Specification<T> hasUuid(String uuid) {
        return new UuidSpecification<>(Collections.singleton(uuid));
    }

    public static <T> Specification<T> hasUuid(Collection<String> uuids) {
        return new UuidSpecification<>(uuids);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Assert.notEmpty(uuids, "Uuids must not be empty");
        return root.get("uuid").in(uuids);
    }
}
