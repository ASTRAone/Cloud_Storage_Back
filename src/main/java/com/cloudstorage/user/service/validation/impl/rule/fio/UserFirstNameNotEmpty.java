package com.cloudstorage.user.service.validation.impl.rule.fio;

import com.cloudstorage.common.domain.user.FIO;
import com.cloudstorage.common.validation.api.context.ValidationContext;
import com.cloudstorage.common.validation.api.enumeration.UserErrorType;
import com.cloudstorage.common.validation.impl.rule.BaseValidationRule;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;
import com.cloudstorage.user.service.validation.enumeration.UserValidationTypes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFirstNameNotEmpty extends BaseValidationRule<FIO> {
    public UserFirstNameNotEmpty() {
        super(List.of(UserValidationTypes.values()), UserErrorType.FIRST_NAME);
    }

    @Override
    public ValidationPartResult validate(FIO validationObject, ValidationContext context) {
        return StringUtils.isBlank(validationObject.getFirstName())
                ? failedValidationResult()
                : successValidationResult();
    }
}
