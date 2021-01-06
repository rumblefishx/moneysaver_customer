package com.rumblesoftware.io.input.dto;

import com.rumblesoftware.enums.ExternalTokenType;


/**
 * A class responsible for hold External Token data
 * @author Cleiton
 *
 */
public class ExternalTokenDataDto {

	/**
	 * Token value
	 */
	private String token;
	
	/**
	 * Type of the External token (ex: Google/Facebook)
	 */
	private ExternalTokenType tokenType;
	
	/**
	 * Class constructor
	 */
	public ExternalTokenDataDto() {}
	
	/**
	 * Class constructor initialized with data
	 * @param token : input parameter filled with the token value
	 * @param tokenType : input parameter filled with the token type
	 */
	public ExternalTokenDataDto(String token,ExternalTokenType tokenType) {
		this.token = token;
		this.tokenType = tokenType;
	}

	
	/**
	 * Get the token value
	 * @return token value
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Set a token value
	 * @param token : input parameter filled with token value
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Get the token type
	 * @return token type
	 */
	public ExternalTokenType getTokenType() {
		return tokenType;
	}

	/**
	 * Set a token type
	 * @param tokenType : input parameter filled with a token type
	 */
	public void setTokenType(ExternalTokenType tokenType) {
		this.tokenType = tokenType;
	}
	
	
}
