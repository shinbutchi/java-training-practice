package vn.smartdev.javatrainingpractice.springmvcpractice.validator.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private IUserService iUserService;

    public void initialize(UniqueUsername uniqueUsername) {

    }

    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !iUserService.isExistedUsername(username);
    }
}
