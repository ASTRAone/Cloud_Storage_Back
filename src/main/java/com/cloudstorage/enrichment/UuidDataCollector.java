package com.cloudstorage.enrichment;

import com.cloudstorage.enrichment.model.UuidData;

public interface UuidDataCollector<DTO> {
    UuidData collect(DTO dto);
}
