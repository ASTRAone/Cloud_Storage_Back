package com.cloudstorage.user.service.validation.impl.rule.user;

import com.cloudstorage.common.domain.user.User;
import com.cloudstorage.common.validation.api.context.ValidationContext;
import com.cloudstorage.common.validation.api.enumeration.UserErrorType;
import com.cloudstorage.common.validation.impl.rule.BaseValidationRule;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;
import com.cloudstorage.user.service.validation.enumeration.UserValidationTypes;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class UserFIONotNull extends BaseValidationRule<User> {
    public UserFIONotNull() {
        super(List.of(UserValidationTypes.values()), UserErrorType.FIO);
    }

    @Override
    public ValidationPartResult validate(User user, ValidationContext context) {
        return isNull(user.getFio())
                ? failedValidationResult()
                : successValidationResult();
    }
}
