package com.rumblesoftware.business.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rumblesoftware.business.ExternalTokenValMediator;
import com.rumblesoftware.business.ExternalTokenValidator;
import com.rumblesoftware.io.input.dto.ExternalTokenDataDto;
import com.rumblesoftware.io.input.dto.LoginDetailsDto;

/**
 * Class responsible for mediate the solution responsible for authenticate an user
 * @author Cleiton
 *
 */
@Component
public class ExternalTokenMediatorImpl implements ExternalTokenValMediator {

	/**
	 * Inject ExternalTokenValidator responsible for authenticate an user with google data
	 */
	@Autowired
	private ExternalTokenValidator google;
	
	/**
	 * List responsible for hold external validators
	 */
	private List<ExternalTokenValidator> validators;
	
	/**
	 * Method responsible for initialize validator arrays
	 */
	@PostConstruct
	private void initialize() {
		validators = Arrays.asList(google);
	}

	/**
	 * Method responsible for mediate the external login entity
	 */
	@Override
	public LoginDetailsDto mediate(ExternalTokenDataDto tokenData) {
		LoginDetailsDto loginDetails = null;
		
		for(ExternalTokenValidator validator : validators) {
			loginDetails = validator.verifyTokenValidity(tokenData);
			if(loginDetails != null)
				break;
		}
		
		return loginDetails;
	}
	


}
