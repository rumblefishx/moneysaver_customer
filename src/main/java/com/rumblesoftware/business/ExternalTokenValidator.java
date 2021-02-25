package com.rumblesoftware.business;

import org.springframework.stereotype.Component;

import com.rumblesoftware.io.input.dto.ExternalTokenDataDto;
import com.rumblesoftware.io.input.dto.LoginDetailsDto;

/**
 * Interface responsible for set a pattern of an External Token Validator
 * @author Cleiton 
 */
@Component
public interface ExternalTokenValidator {

	/**
	 * Method responsible for verify token validity
	 * @param tokenData : input parameter with token details
	 * @return LoginDetailsDto with user login details
	 */
	public abstract LoginDetailsDto verifyTokenValidity(ExternalTokenDataDto tokenData);
}
