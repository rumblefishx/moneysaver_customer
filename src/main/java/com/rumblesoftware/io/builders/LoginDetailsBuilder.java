package com.rumblesoftware.io.builders;

import com.rumblesoftware.enums.ExternalTokenType;
import com.rumblesoftware.io.input.dto.LoginDetailsDto;

public class LoginDetailsBuilder {

	private String externalId;
	private ExternalTokenType externalIdType;
	private String name;
	private String surname;
	private String gender;
	private String email;
	private String dateOfBirth;
	
	public LoginDetailsBuilder withExternalId(String externalId) {
		this.externalId = externalId;
		return this;
	}
	
	public LoginDetailsBuilder withExternalIdType(ExternalTokenType type) {
		this.externalIdType = type;
		return this;
	}
	
	public LoginDetailsBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public LoginDetailsBuilder withSurname(String surname) {
		this.surname = surname;
		return this;
	}
	
	public LoginDetailsBuilder withGender(String gender) {
		this.gender = gender;
		return this;
	}
	
	public LoginDetailsBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	
	public LoginDetailsBuilder withDateOfBirth(String dateString) {
		this.dateOfBirth = dateString;
		return this;
	}
	
	public LoginDetailsDto build() {
		LoginDetailsDto dto = new LoginDetailsDto();
		
		dto.setDateOfBirth(dateOfBirth);
		dto.setEmail(email);
		dto.setExternalId(externalId);
		dto.setExternalIdType(externalIdType);
		dto.setGender(gender);
		dto.setName(name);
		dto.setSurname(surname);
		
		return dto;
	}
}
