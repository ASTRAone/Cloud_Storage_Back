package com.cloudstorage.enrichment.model.impl;

import com.cloudstorage.enrichment.model.IdentityEnrichmentType;
import com.cloudstorage.enrichment.model.UuidData;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static org.springframework.util.CollectionUtils.isEmpty;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleUuidData implements UuidData {
    private final IdentityEnrichmentType type;
    private final Set<String> uuids;

    public static UuidData of(IdentityEnrichmentType type, Collection<String> uuids) {
        return new SimpleUuidData(type, isEmpty(uuids) ? Collections.emptySet() : Set.copyOf(uuids));
    }

    @Override
    public IdentityEnrichmentType type() {
        return type;
    }

    @Override
    public Set<String> uuids() {
        return uuids;
    }

    @Override
    public boolean notEmpty() {
        return !isEmpty(uuids);
    }
}
