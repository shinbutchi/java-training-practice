package vn.smartdev.javatrainingpractice.springmvcpractice.validator;

import vn.smartdev.javatrainingpractice.springmvcpractice.validator.impl.MatchingPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = MatchingPasswordValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MatchingPassword {
    String message() default "{invalid.retype.password}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
