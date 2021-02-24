package com.arm.api.hero.component;

import com.arm.api.hero.model.support.ValidationError;
import com.arm.api.hero.model.support.ValidationErrorSet;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidationComponent {

    public  <T> ValidationErrorSet validate(T entity)
    {
        Validator validator=null;
        Set<ConstraintViolation<T>> violations=null;
        ValidationErrorSet validationErrorSet=null;
        List<ValidationError> validationErrors=null;
        try
        {
            validationErrorSet=new ValidationErrorSet();
            validator= Validation.buildDefaultValidatorFactory().getValidator();
            violations=validator.validate(entity);
            if(!violations.isEmpty())
            {
                validationErrors=violations.stream().map(this::apply).collect(Collectors.toList());
                validationErrorSet.setErrors(validationErrors);
            }

            return validationErrorSet;
        }
        finally {
            validator=null;
            violations=null;
            validationErrorSet=null;
            validationErrors=null;
        }
    }

    private ValidationError apply(ConstraintViolation entry) {
        return new ValidationError(entry.getPropertyPath().toString(),entry.getMessage(),entry.getInvalidValue());
    }
}
