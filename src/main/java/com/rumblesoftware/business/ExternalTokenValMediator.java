package com.rumblesoftware.business;

import org.springframework.stereotype.Component;

import com.rumblesoftware.io.input.dto.ExternalTokenDataDto;
import com.rumblesoftware.io.input.dto.LoginDetailsDto;

@Component
public interface ExternalTokenValMediator {
	
	public LoginDetailsDto mediate(ExternalTokenDataDto tokenData);
}
