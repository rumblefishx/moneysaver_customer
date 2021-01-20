package com.rumblesoftware.io.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rumblesoftware.io.validation.EmailChecker;

public class EmailCheckerValidator implements ConstraintValidator<EmailChecker, String> {
	
	private static String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

	@Override
	public boolean isValid(String inputEmail, ConstraintValidatorContext context) {
		
		if(inputEmail != null 
				&& !(inputEmail.trim().length() == 0)
				&& !inputEmail.matches(EMAIL_REGEX))
			return false; 
		return true;
	}

}
