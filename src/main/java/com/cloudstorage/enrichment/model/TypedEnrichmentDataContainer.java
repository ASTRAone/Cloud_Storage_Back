package com.cloudstorage.enrichment.model;


import com.cloudstorage.common.model.Identifiable;

import java.util.Collection;
import java.util.Optional;

public interface TypedEnrichmentDataContainer {

    Optional<EnrichmentData> tryFind(IdentityEnrichmentType identityEnrichmentType);

    void put(IdentityEnrichmentType identityEnrichmentType, EnrichmentData enrichmentData);

    void put(IdentityEnrichmentType type, Collection<? extends Identifiable> data);
}
