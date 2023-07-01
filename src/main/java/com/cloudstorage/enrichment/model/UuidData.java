package com.cloudstorage.enrichment.model;

import java.util.Set;

public interface UuidData {
    IdentityEnrichmentType type();

    Set<String> uuids();

    boolean notEmpty();

}
