package com.cloudstorage.enrichment;

import com.cloudstorage.enrichment.model.TypedEnrichmentDataContainer;
import com.cloudstorage.enrichment.model.UuidData;

import java.util.Collection;

public interface ExternalFetchFacade {
    TypedEnrichmentDataContainer fetch(Collection<UuidData> uuidDatas);
}
