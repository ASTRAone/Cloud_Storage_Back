package com.cloudstorage.common.validation.impl.processor;

import com.cloudstorage.common.utils.CommonCollections;
import com.cloudstorage.common.validation.api.ValidationType;
import com.cloudstorage.common.validation.api.context.ValidationContext;
import com.cloudstorage.common.validation.api.enricher.ValidationContextEnricher;
import com.cloudstorage.common.validation.api.processor.ValidationProcessor;
import com.cloudstorage.common.validation.impl.BaseValidationTypeChecker;
import com.cloudstorage.common.validation.impl.context.HasMapValidationContext;

import java.util.Collection;
import java.util.List;

public abstract class BaseValidationProcessor<T, E extends ValidationContextEnricher<T>>
        extends BaseValidationTypeChecker
        implements ValidationProcessor<T> {
    protected final List<E> enrichers;

    protected BaseValidationProcessor(Collection<ValidationType> supportedValidationTypes,
                                      List<E> enrichers) {
        super(supportedValidationTypes);
        this.enrichers = enrichers;
    }

    protected ValidationContext prepareContext(T validationObject, ValidationType validationType) {
        ValidationContext context = new HasMapValidationContext();
        CommonCollections.stream(enrichers)
                .filter(enricher -> enricher.checkValidationType(validationType))
                .forEach(enricher -> enricher.enrich(context, validationObject));
        return context;
    }
}
