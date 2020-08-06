package com.rumblesoftware.io.input.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.rumblesoftware.io.validation.ValidDate;
import com.rumblesoftware.io.validation.ValidGender;
import com.rumblesoftware.io.validation.ValidPassword;

@Valid
public class CustomerInputDTO {
	
	@NotBlank(message = "{customer.input.name.notblank}")
	@Length(max = 25,message="{customer.input.name.maxlength}")
	@NotNull(message="{customer.input.name.notnull}")
	private String name;
	
	@NotBlank(message = "{customer.input.surname.notblank}")
	@Length(max = 60,message="{customer.input.surname.maxlength}")
	@NotNull(message="{customer.input.surname.notnull}")
	private String surname;
	
	@NotBlank(message = "{customer.input.gender.notblank}")
	@Length(max = 60,message="{customer.input.gender.maxlength}")
	@NotNull(message="{customer.input.gender.notnull}")
	@ValidGender(message="{customer.input.gender.invalid}")
	private String gender;
	
	@NotBlank(message = "{customer.input.email.notblank}")
	@Email(message = "{customer.input.email.invalid}")
	@NotNull(message="{customer.input.email.notnull}")
	private String email;
	
	@NotBlank(message = "{customer.input.password.notblank}")
	@Length(max = 12,message="{customer.input.password.maxlength}")
	@NotNull(message="{customer.input.password.notnull}")
	@ValidPassword(message = "{customer.input.password.invalid}")
	private String password;
	
	@NotBlank(message = "{customer.input.dateofbirth.notblank}")
	@NotNull(message = "{customer.input.dateofbirth.notnull}")
	@ValidDate(message="{customer.input.dateofbirth.invalid}")
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
