package com.cloudstorage.enrichment.impl;

import com.cloudstorage.enrichment.EnrichmentProcessor;
import com.cloudstorage.enrichment.model.EnrichmentData;
import com.cloudstorage.enrichment.model.IdentityEnrichmentType;
import com.cloudstorage.enrichment.model.TypedEnrichmentDataContainer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEnrichmentProcessor<DTO> implements EnrichmentProcessor<DTO> {
    private final IdentityEnrichmentType enrichmentType;

    @Override
    public void enrich(DTO dto, TypedEnrichmentDataContainer enrichmentData) {
        enrichmentData.tryFind(enrichmentType).ifPresent(data -> process(dto, data));
    }

    protected abstract void process(DTO dto, EnrichmentData data);
}
