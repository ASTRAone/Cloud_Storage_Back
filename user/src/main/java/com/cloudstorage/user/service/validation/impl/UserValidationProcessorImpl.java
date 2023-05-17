package com.cloudstorage.user.service.validation.impl;

import com.cloudstorage.common.domain.user.FIO;
import com.cloudstorage.common.domain.user.User;
import com.cloudstorage.common.validation.api.ValidationType;
import com.cloudstorage.common.validation.api.context.ValidationContext;
import com.cloudstorage.common.validation.api.validator.Validator;
import com.cloudstorage.common.validation.impl.processor.BaseValidationProcessor;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;
import com.cloudstorage.user.model.UserEntity;
import com.cloudstorage.user.service.validation.api.UserContextEnricher;
import com.cloudstorage.user.service.validation.api.UserValidationProcessor;
import com.cloudstorage.user.service.validation.api.UserValidator;
import com.cloudstorage.user.service.validation.enumeration.UserValidationTypes;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class UserValidationProcessorImpl
        extends BaseValidationProcessor<UserEntity, UserContextEnricher>
        implements UserValidationProcessor {
    private final UserValidator userValidator;
    private final Validator<FIO> fioValidator;

    protected UserValidationProcessorImpl(UserValidator userValidator,
                                          Validator<FIO> fioValidator,
                                          List<UserContextEnricher> enrichers) {
        super(List.of(UserValidationTypes.values()), enrichers);
        this.userValidator = userValidator;
        this.fioValidator = fioValidator;
    }

    @Override
    public Collection<ValidationPartResult> validate(UserEntity validationObject,
                                                     ValidationType validationType) {
        ValidationContext context = prepareContext(validationObject, validationType);
        User data = validationObject.getData();
        ArrayList<ValidationPartResult> result = new ArrayList<>();

        result.addAll(userValidator.validate(data, validationType, context));
        Optional.ofNullable(data.getFio())
                .map(fio -> fioValidator.validate(fio, validationType, context))
                .ifPresent(result::addAll);

        return result;
    }
}
