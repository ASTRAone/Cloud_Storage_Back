package com.cloudstorage.enrichment.model.impl;

import com.cloudstorage.common.model.Identifiable;
import com.cloudstorage.enrichment.model.EnrichmentData;
import com.cloudstorage.enrichment.model.IdentityEnrichmentType;
import com.cloudstorage.enrichment.model.TypedEnrichmentDataContainer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MapTypedEnrichmentData implements TypedEnrichmentDataContainer {
    private final Map<IdentityEnrichmentType, EnrichmentData> db = new HashMap<>();

    public static TypedEnrichmentDataContainer init() {
        return new MapTypedEnrichmentData();
    }

    @Override
    public Optional<EnrichmentData> tryFind(IdentityEnrichmentType enrichmentType) {
        return Optional.ofNullable(db.get(enrichmentType));
    }

    @Override
    public void put(IdentityEnrichmentType enrichmentType, EnrichmentData enrichmentData) {
        db.put(enrichmentType, enrichmentData);
    }

    @Override
    public void put(IdentityEnrichmentType type, Collection<? extends Identifiable> data) {
        put(type, MapEnrichmentData.of(data));
    }
}
