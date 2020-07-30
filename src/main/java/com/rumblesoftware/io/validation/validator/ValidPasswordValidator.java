package com.rumblesoftware.io.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rumblesoftware.io.validation.ValidPassword;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {

	
	private final String VALID_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
	
	// Password must contain lower case letters
	// Password must contain higher case letters
	// Password must contain Symbols letters
	// Password must have between 8 and 12 characters
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(!value.matches(VALID_PATTERN))
			return false;
		
		return true;
	}

}
