package vn.smartdev.javatrainingpractice.springmvcpractice.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Autowired
    private IUserService iUserService;

    public void initialize(ValidEmail validEmail) {

    }

    public boolean isValid(String emailAddress, ConstraintValidatorContext constraintValidatorContext) {
        return !iUserService.isExistedEmailAddress(emailAddress);
    }
}
