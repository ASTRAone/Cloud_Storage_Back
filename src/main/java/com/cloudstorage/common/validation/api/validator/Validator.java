package com.cloudstorage.common.validation.api.validator;


import com.cloudstorage.common.validation.api.ValidationType;
import com.cloudstorage.common.validation.api.context.ValidationContext;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;

import java.util.Collection;

public interface Validator<T> {
    Collection<ValidationPartResult> validate(T validationObject,
                                              ValidationType validationType,
                                              ValidationContext context);
}
