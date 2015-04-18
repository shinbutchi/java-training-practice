package vn.smartdev.javatrainingpractice.springmvcpractice.validator.impl;

import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.MatchingPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordValidator implements ConstraintValidator<MatchingPassword, Object> {
    @Override
    public void initialize(MatchingPassword matchingPassword) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO userDTO = (UserDTO) object;
        return userDTO.getPassword().equals(userDTO.getRetypePassword());
    }
}
