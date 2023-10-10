package com.xinshijie.common.utils.bean;

//import java.util.Set;
//import jakarta.annotation.ConstraintViolation;
//import jakarta.annotation.ConstraintViolationException;
//import jakarta.validation.Validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.Set;

/**
 * bean对象属性验证
 *
 * @author xinshijie
 */
public class BeanValidators {
    public static void validateWithException(Validator validator, Object object, Class<?>... groups)
            throws ConstraintViolationException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
