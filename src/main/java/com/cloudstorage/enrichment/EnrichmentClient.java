package com.cloudstorage.enrichment;

import com.cloudstorage.common.model.Identifiable;
import com.cloudstorage.enrichment.model.IdentityEnrichmentType;

import java.util.Collection;
import java.util.List;

public interface EnrichmentClient<DTO extends Identifiable> {
    boolean support(IdentityEnrichmentType type);

    List<DTO> fetch(Collection<String> uuids);
}
