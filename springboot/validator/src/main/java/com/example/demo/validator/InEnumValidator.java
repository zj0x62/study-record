package com.example.demo.validator;

import cn.hutool.core.collection.CollUtil;
import com.example.demo.annotation.InEnum;
import com.example.demo.service.ArrayValuable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2025/2/24 14:01
 * @Description:
 */
public class InEnumValidator implements ConstraintValidator<InEnum, Object> {

    private List<Object> allowedValues;

    @Override
    public void initialize(InEnum constraintAnnotation) {
        Class<? extends ArrayValuable<?>> enumClass = constraintAnnotation.value();
        ArrayValuable<?>[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants != null && enumConstants.length > 0) {
            allowedValues = Arrays.asList(enumConstants[0].array());
        } else {
            allowedValues = Collections.emptyList();
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        if (value instanceof Collection) {
            Collection<?> values = (Collection<?>) value;
            if (values.isEmpty()) {
                return true;
            }
            if (CollUtil.containsAll(allowedValues, values)) {
                return true;
            }
            buildErrorMessage(constraintValidatorContext);
            return false;
        } else {
            if (allowedValues.contains(value)) {
                return true;
            }
            buildErrorMessage(constraintValidatorContext);
            return false;
        }
    }

    private void buildErrorMessage(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        String message = context.getDefaultConstraintMessageTemplate()
                .replaceAll("\\{value}", allowedValues.toString());
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
