package com.rumblesoftware.io.input.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.rumblesoftware.io.validation.EmailChecker;
import com.rumblesoftware.io.validation.ValidPassword;

/**
 * Class responsible for hold the input parameters during customer requests
 * @author Cleiton
 *
 */
@Valid
public class CustomerInputDTO {
	
	/**
	 * user's name
	 */
	@NotBlank(message = "customer.input.name.notblank")
	@Length(max = 25,message="customer.input.name.maxlength")
	@NotNull(message="customer.input.name.notnull")
	private String name;
	
	/**
	 * user's surname
	 */
	@NotBlank(message = "customer.input.surname.notblank")
	@Length(max = 60,message="customer.input.surname.maxlength")
	@NotNull(message="customer.input.surname.notnull")
	private String surname;
	
	/**
	 * user's email
	 */
	@NotBlank(message = "customer.input.email.notblank")
	@EmailChecker(message="customer.input.email.invalid")
	@NotNull(message="customer.input.email.notnull")
	private String email;
	
	/**
	 * user's password
	 */
	@NotBlank(message = "customer.input.password.notblank")
	@Length(max = 12,message="customer.input.password.maxlength")
	@NotNull(message="customer.input.password.notnull")
	@ValidPassword(message = "customer.input.password.invalid")
	private String password;


	/**
	 * get user name
	 * @return user name 
	 */
	public String getName() {
		return name;
	}

	/**
	 * set a name to the user
	 * @param name : input parameter filled with the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get user's surname
	 * @return user's surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set a surname to the user
	 * @param surname : input parameter filled with the surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * get the user's email
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set an email to the user
	 * @param email : input parameter filled with the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * get the user's password
	 * @return user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set a password for the user
	 * @param password : input parameter filled with the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
