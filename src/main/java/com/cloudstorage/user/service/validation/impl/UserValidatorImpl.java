package com.cloudstorage.user.service.validation.impl;

import com.cloudstorage.common.domain.user.User;
import com.cloudstorage.common.validation.api.rule.ValidationRule;
import com.cloudstorage.common.validation.impl.validator.BaseValidator;
import com.cloudstorage.user.service.validation.api.UserValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserValidatorImpl
        extends BaseValidator<User>
        implements UserValidator {
    protected UserValidatorImpl(List<ValidationRule<User>> validationRules) {
        super(validationRules);
    }
}
