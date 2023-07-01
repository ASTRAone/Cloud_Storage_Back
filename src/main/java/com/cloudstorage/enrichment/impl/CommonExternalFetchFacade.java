package com.cloudstorage.enrichment.impl;

import com.cloudstorage.common.model.Identifiable;
import com.cloudstorage.enrichment.EnrichmentClient;
import com.cloudstorage.enrichment.ExternalFetchFacade;
import com.cloudstorage.enrichment.model.IdentityEnrichmentType;
import com.cloudstorage.enrichment.model.TypedEnrichmentDataContainer;
import com.cloudstorage.enrichment.model.UuidData;
import com.cloudstorage.enrichment.model.impl.MapTypedEnrichmentData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class CommonExternalFetchFacade implements ExternalFetchFacade {
    private final List<EnrichmentClient<? extends Identifiable>> enrichmentClients;

    @Override
    public TypedEnrichmentDataContainer fetch(Collection<UuidData> uuidDatas) {
        TypedEnrichmentDataContainer typedEnrichmentData = MapTypedEnrichmentData.init();

        if (isEmpty(uuidDatas)) {
            return typedEnrichmentData;
        }

        uuidDatas.stream()
                .filter(UuidData::notEmpty)
                .forEach(uuidData -> typedEnrichmentData.put(uuidData.type(), fetch(uuidData)));

        return typedEnrichmentData;
    }

    private List<? extends Identifiable> fetch(UuidData uuidData) {
        return tryFindClient(uuidData.type())
                .map(client -> client.fetch(uuidData.uuids()))
                .orElseGet(Collections::emptyList);
    }

    private Optional<EnrichmentClient<? extends Identifiable>> tryFindClient(IdentityEnrichmentType type) {
        return enrichmentClients.stream()
                .filter(client -> client.support(type))
                .findFirst();
    }
}
