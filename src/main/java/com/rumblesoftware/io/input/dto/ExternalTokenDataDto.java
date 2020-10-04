package com.rumblesoftware.io.input.dto;

import com.rumblesoftware.enums.ExternalTokenType;

public class ExternalTokenDataDto {

	private String token;
	private ExternalTokenType tokenType;
	
	public ExternalTokenDataDto() {}
	
	public ExternalTokenDataDto(String token,ExternalTokenType tokenType) {
		this.token = token;
		this.tokenType = tokenType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ExternalTokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(ExternalTokenType tokenType) {
		this.tokenType = tokenType;
	}
	
	
}
