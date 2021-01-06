package com.rumblesoftware.business;

import org.springframework.stereotype.Component;

import com.rumblesoftware.io.input.dto.ExternalTokenDataDto;
import com.rumblesoftware.io.input.dto.LoginDetailsDto;

/**
 * Interface responsible for set a mediator pattern
 * @author Cleiton
 *
 */
@Component
public interface ExternalTokenValMediator {
	
	/**
	 * Method responsible for mediate the instance of the External Token validator
	 * @param tokenData : input parameter with token details
	 * @return LoginDetailsDto filled with an User login details
	 */
	public LoginDetailsDto mediate(ExternalTokenDataDto tokenData);
}
