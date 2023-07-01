package com.cloudstorage.user.service.validation.impl;

import com.cloudstorage.common.domain.user.FIO;
import com.cloudstorage.common.validation.api.rule.ValidationRule;
import com.cloudstorage.common.validation.api.validator.Validator;
import com.cloudstorage.common.validation.impl.validator.BaseValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFIOValidatorImpl
        extends BaseValidator<FIO>
        implements Validator<FIO> {
    protected UserFIOValidatorImpl(List<ValidationRule<FIO>> validationRules) {
        super(validationRules);
    }
}
