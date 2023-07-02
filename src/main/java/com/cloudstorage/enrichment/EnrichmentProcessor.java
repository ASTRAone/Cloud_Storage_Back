package com.cloudstorage.enrichment;

import com.cloudstorage.enrichment.model.TypedEnrichmentDataContainer;

public interface EnrichmentProcessor<DTO> {
    void enrich(DTO dto, TypedEnrichmentDataContainer enrichmentData);
}
