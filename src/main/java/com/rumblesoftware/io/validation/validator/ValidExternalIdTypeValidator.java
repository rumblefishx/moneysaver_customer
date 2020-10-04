package com.rumblesoftware.io.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rumblesoftware.enums.ExternalTokenType;
import com.rumblesoftware.io.validation.ValidExternalIdType;

public class ValidExternalIdTypeValidator implements ConstraintValidator<ValidExternalIdType, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (ExternalTokenType.castIntToEnum(value) == null) {
			return false;
		}
		return true;
	}

}
