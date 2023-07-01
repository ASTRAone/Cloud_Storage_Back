package com.cloudstorage.enrichment.model.impl;

import com.cloudstorage.common.model.Identifiable;
import com.cloudstorage.enrichment.model.EnrichmentData;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
import static org.springframework.util.CollectionUtils.isEmpty;

public class MapEnrichmentData implements EnrichmentData {
    private final Map<String, Identifiable> db;

    private MapEnrichmentData(Collection<? extends Identifiable> datas) {
        if (!isEmpty(datas)) {
            db = datas.stream().collect(toMap(Identifiable::getUuid, Function.identity()));
        } else {
            db = Collections.emptyMap();
        }
    }

    public static EnrichmentData of(Collection<? extends Identifiable> data) {
        return new MapEnrichmentData(data);
    }

    @Override
    public <DTO extends Identifiable> Optional<DTO> tryFindByUuid(String uuid) {
        return Optional.ofNullable((DTO) db.get(uuid));
    }
}
