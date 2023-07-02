package com.cloudstorage.enrichment;

public interface EnrichmentService<DTO> {
    void enrich(DTO dto);
}
