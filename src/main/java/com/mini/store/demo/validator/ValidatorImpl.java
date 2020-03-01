package com.mini.store.demo.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Configuration
public class ValidatorImpl implements InitializingBean {

    private Validator validator;
    //https://coding.imooc.com/lesson/338.html#mid=26216
    // implement the validate method then return validation result
    public ValidationResult validate(Object bean){
        ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);

        if(constraintViolationSet.size() > 0){
            validationResult.setHasErrors(true);
            constraintViolationSet.forEach(constraintViolation -> {
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                validationResult.getErrorMsgMAP().put(propertyName, errMsg);
            });
        }

        return validationResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
