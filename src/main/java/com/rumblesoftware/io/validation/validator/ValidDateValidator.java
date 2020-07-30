package com.rumblesoftware.io.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rumblesoftware.io.validation.ValidDate;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String>  {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		//TODO: Validation is not covering day 31
		if(value.trim().matches("^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"))
			return true;
		return false;
	}

}
