package com.rumblesoftware.io.input.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.rumblesoftware.io.validation.ValidDate;
import com.rumblesoftware.io.validation.ValidGender;
import com.rumblesoftware.io.validation.ValidPassword;

@Valid
public class CustomerInputPatchDto {
	
	@NotNull(message="customer.input.id.notnull")
	private Long customerId;

	@Length(max = 25,message="customer.input.name.maxlength")
	private String name;
	
	@Length(max = 60,message="customer.input.surname.maxlength")
	private String surname;
	
	@Length(max = 1,message="customer.input.gender.maxlength")
	@ValidGender(message="customer.input.gender.invalid")
	private String gender;
	
	@Email(message = "customer.input.email.invalid")
	private String email;
	
	@Length(max = 12,message="customer.input.password.maxlength")
	@ValidPassword(message = "customer.input.password.invalid")
	private String password;
	
	@ValidDate(message="customer.input.dateofbirth.invalid")
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
}
