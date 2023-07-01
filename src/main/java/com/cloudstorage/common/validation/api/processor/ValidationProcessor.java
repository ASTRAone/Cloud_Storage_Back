package com.cloudstorage.common.validation.api.processor;


import com.cloudstorage.common.validation.api.ValidationType;
import com.cloudstorage.common.validation.api.ValidationTypeChecker;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;

import java.util.Collection;

public interface ValidationProcessor<T> extends ValidationTypeChecker {
    Collection<ValidationPartResult> validate(T validationObject, ValidationType validationType);
}
