package com.rumblesoftware.io.output.dto;

import java.math.BigDecimal;

import com.rumblesoftware.io.enums.Gender;

public class CustomerOutputDTO {
	
	private long customerId;
	
	private boolean isCustomerActive;
	
	private String name;
	
	private String surname;
	
	private Gender gender;
	
	private String email;
	
	private String dateOfBirth;
	
	private BigDecimal userBalance;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public boolean isCustomerActive() {
		return isCustomerActive;
	}

	public void setCustomerActive(boolean isCustomerActive) {
		this.isCustomerActive = isCustomerActive;
	}

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public BigDecimal getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(BigDecimal userBalance) {
		this.userBalance = userBalance;
	}
	
	
}
