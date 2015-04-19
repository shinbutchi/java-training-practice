package vn.smartdev.javatrainingpractice.springmvcpractice.validator.impl;

import com.mysql.jdbc.StringUtils;
import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.MatchingPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordValidator implements ConstraintValidator<MatchingPassword, Object> {
    public void initialize(MatchingPassword matchingPassword) {

    }

    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO userDTO = (UserDTO) object;
        return StringUtils.isNullOrEmpty(userDTO.getPassword()) || isMatcherPassword(userDTO);
    }

    private boolean isMatcherPassword(UserDTO userDTO) {
        return userDTO.getPassword().equals(userDTO.getRetypePassword());
    }
}
