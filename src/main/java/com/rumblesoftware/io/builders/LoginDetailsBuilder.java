package com.rumblesoftware.io.builders;

import com.rumblesoftware.enums.ExternalTokenType;
import com.rumblesoftware.io.input.dto.LoginDetailsDto;

/**
 * Class responsible to build a LoginDetailsDto instance
 * @author Cleiton
 *
 */
public class LoginDetailsBuilder {

	private String externalId;
	private ExternalTokenType externalIdType;
	private String name;
	private String surname;
	private String gender;
	private String email;
	private String dateOfBirth;
	
	/**
	 * Set a value to externalId parameter inside LoginDetailsDto
	 * @param externalId : input parameter containing the value to be set
	 * @return LoginDetailsBuilder instance with an updated LoginDetailsDto object
	 */
	public LoginDetailsBuilder withExternalId(String externalId) {
		this.externalId = externalId;
		return this;
	}
	
	/**
	 * Set a value to externalIdType parameter inside LoginDetailsDto
	 * @param type : input parameter containing the value to be set
	 * @return LoginDetailsBuilder instance with an updated LoginDetailsDto object
	 */
	public LoginDetailsBuilder withExternalIdType(ExternalTokenType type) {
		this.externalIdType = type;
		return this;
	}
	
	/**
	 * Set a value to name parameter inside LoginDetailsDto
	 * @param name : input parameter containing the value to be set
	 * @return LoginDetailsBuilder instance with an updated LoginDetailsDto object
	 */
	public LoginDetailsBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Set a value to surname parameter inside LoginDetailsDto
	 * @param surname : input parameter containing the value to be set
	 * @return LoginDetailsBuilder instance with an updated LoginDetailsDto object
	 */
	public LoginDetailsBuilder withSurname(String surname) {
		this.surname = surname;
		return this;
	}
	
	
	/**
	 * Set a value to gender parameter inside LoginDetailsDto
	 * @param gender : input parameter containing the value to be set
	 * @return LoginDetailsBuilder instance with an updated LoginDetailsDto object
	 */
	public LoginDetailsBuilder withGender(String gender) {
		this.gender = gender;
		return this;
	}
	
	/**
	 * Set a value to email parameter inside LoginDetailsDto
	 * @param email : input parameter containing the value to be set
	 * @return LoginDetailsBuilder instance with an updated LoginDetailsDto object
	 */
	public LoginDetailsBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	
	/**
	 * Set a value to dateOfBirth parameter inside LoginDetailsDto
	 * @param dateString : input parameter containing the value to be set
	 * @return LoginDetailsBuilder instance with an updated LoginDetailsDto object
	 */
	public LoginDetailsBuilder withDateOfBirth(String dateString) {
		this.dateOfBirth = dateString;
		return this;
	}
	
	/**
	 * Method responsible for build a LoginDetailsDto instance with the values provided
	 * @return an instance of a LoginDetailsDto
	 */
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
