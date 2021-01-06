package com.rumblesoftware.io.input.dto;

import com.rumblesoftware.enums.ExternalTokenType;

/**
 * Class responsible for hold login details
 * @author Cleiton
 *
 */
public class LoginDetailsDto {

	private String externalId;
	private ExternalTokenType externalIdType;
	private String name;
	private String surname;
	private String gender;
	private String email;
	private String dateOfBirth;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public ExternalTokenType getExternalIdType() {
		return externalIdType;
	}
	public void setExternalIdType(ExternalTokenType externalIdType) {
		this.externalIdType = externalIdType;
	}
	
	
}
