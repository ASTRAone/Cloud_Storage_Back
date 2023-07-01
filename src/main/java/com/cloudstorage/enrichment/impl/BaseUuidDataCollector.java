package com.cloudstorage.enrichment.impl;

import com.cloudstorage.enrichment.UuidDataCollector;
import com.cloudstorage.enrichment.model.IdentityEnrichmentType;
import com.cloudstorage.enrichment.model.UuidData;
import com.cloudstorage.enrichment.model.impl.SimpleUuidData;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public abstract class BaseUuidDataCollector<DTO> implements UuidDataCollector<DTO> {
    private final IdentityEnrichmentType enrichmentType;

    @Override
    public UuidData collect(DTO dto) {
        return SimpleUuidData.of(enrichmentType, extractUuids(dto));
    }

    protected abstract Set<String> extractUuids(DTO dto);
}
