package com.rumblesoftware.io.output.dto;

import com.rumblesoftware.io.enums.Gender;

public class CustomerOutputDTO {
	
	private long customerId;
	
	private boolean isCustomerActive;
	
	private String name;
	
	private String surname;
	
	private String email;

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
	
}
