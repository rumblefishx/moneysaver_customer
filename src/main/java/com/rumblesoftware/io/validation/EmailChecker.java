package com.rumblesoftware.io.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.rumblesoftware.io.validation.validator.EmailCheckerValidator;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailCheckerValidator.class)
public @interface EmailChecker {
    String message() default "{com.dolszewski.blog.UniqueLogin.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
