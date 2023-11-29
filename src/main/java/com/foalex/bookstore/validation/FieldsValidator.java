package com.foalex.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValidator
        implements ConstraintValidator<FieldsValid, Object> {
    private String field;
    private String fieldMatch;

    public void initialize(FieldsValid constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object password = new BeanWrapperImpl(value).getPropertyValue(field);
        Object repeatPassword = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        return password != null && Objects.equals(password, repeatPassword);
    }
}
