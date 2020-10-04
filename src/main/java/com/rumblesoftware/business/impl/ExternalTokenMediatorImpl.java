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

@Component
public class ExternalTokenMediatorImpl implements ExternalTokenValMediator {

	@Autowired
	private ExternalTokenValidator google;
	
	private List<ExternalTokenValidator> validators;
	
	@PostConstruct
	private void initialize() {
		validators = Arrays.asList(google);
	}

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
