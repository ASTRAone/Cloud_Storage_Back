package com.cloudstorage.user.service.validation.impl;

import com.cloudstorage.common.validation.api.ValidationType;
import com.cloudstorage.common.validation.api.enumeration.Action;
import com.cloudstorage.common.validation.impl.initiator.BaseValidationInitiator;
import com.cloudstorage.user.model.UserEntity;
import com.cloudstorage.user.service.validation.api.UserValidationInitiator;
import com.cloudstorage.user.service.validation.api.UserValidationProcessor;
import com.cloudstorage.user.service.validation.enumeration.UserValidationTypes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserValidationInitiatorImpl
        extends BaseValidationInitiator<UserEntity, UserValidationProcessor>
        implements UserValidationInitiator {
    public UserValidationInitiatorImpl(List<UserValidationProcessor> validationProcessors) {
        super(validationProcessors);
    }

    @Override
    protected ValidationType resolveValidationType(Action action) {
        return switch (action) {
            case CREATE -> UserValidationTypes.CREATE;
            case UPDATE -> UserValidationTypes.UPDATE;
        };
    }
}
