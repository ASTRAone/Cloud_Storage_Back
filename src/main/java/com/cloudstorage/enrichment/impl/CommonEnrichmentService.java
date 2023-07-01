package com.cloudstorage.enrichment.impl;

import com.cloudstorage.enrichment.EnrichmentProcessor;
import com.cloudstorage.enrichment.EnrichmentService;
import com.cloudstorage.enrichment.ExternalFetchFacade;
import com.cloudstorage.enrichment.UuidDataCollector;
import com.cloudstorage.enrichment.model.TypedEnrichmentDataContainer;
import com.cloudstorage.enrichment.model.UuidData;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class CommonEnrichmentService<DTO> implements EnrichmentService<DTO> {
    private final List<? extends EnrichmentProcessor<DTO>> enrichmentProcessors;
    private final List<? extends UuidDataCollector<DTO>> uuidDataCollectors;
    private final ExternalFetchFacade externalFetchFacade;

    @Override
    public void enrich(DTO dto) {
        if (isNull(dto)) {
            return;
        }
        List<UuidData> uuidDatas = collectUuidData(dto);
        TypedEnrichmentDataContainer enrichmentData = externalFetchFacade.fetch(uuidDatas);
        enrichmentProcessors.forEach(processor -> processor.enrich(dto, enrichmentData));
    }

    private List<UuidData> collectUuidData(DTO dto) {
        return uuidDataCollectors.stream()
                .map(uuidDataCollector -> uuidDataCollector.collect(dto))
                .toList();
    }
}
