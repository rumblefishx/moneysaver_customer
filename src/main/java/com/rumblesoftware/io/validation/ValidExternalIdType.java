package com.rumblesoftware.io.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.rumblesoftware.io.validation.validator.ValidExternalIdTypeValidator;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidExternalIdTypeValidator.class)
public @interface ValidExternalIdType {
    String message() default "{external.id.type.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
