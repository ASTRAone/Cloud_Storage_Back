package com.cloudstorage.enrichment.model;


import com.cloudstorage.common.model.Identifiable;

import java.util.Optional;

public interface EnrichmentData {
    <DTO extends Identifiable> Optional<DTO> tryFindByUuid(String uuid);
}
