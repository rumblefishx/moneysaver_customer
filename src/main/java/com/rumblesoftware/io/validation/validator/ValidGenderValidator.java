package com.rumblesoftware.io.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rumblesoftware.io.validation.ValidGender;

public class ValidGenderValidator implements ConstraintValidator<ValidGender, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value != null) {
			if (value.trim().equalsIgnoreCase("m") == false 
					&& value.trim().equalsIgnoreCase("f") == false)
				return false;
		}

		return true;
	}

}
