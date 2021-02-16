package com.rumblesoftware.io.input.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.rumblesoftware.io.validation.ValidDate;
import com.rumblesoftware.io.validation.ValidGender;
import com.rumblesoftware.io.validation.ValidPassword;

/**
 * Class responsible for hold input parameter during user data update requests
 * @author Cleiton
 *
 */
@Valid
public class CustomerInputPatchDto {
	
	/**
	 * Customer ID in the database
	 */
	@NotNull(message="customer.input.id.notnull")
	private Long customerId;

	/**
	 * User's email
	 */
	@Length(max = 25,message="customer.input.name.maxlength")
	private String name;
	
	/**
	 * User's surname
	 */
	@Length(max = 60,message="customer.input.surname.maxlength")
	private String surname;

	
	/**
	 * User's email
	 */
	@Email(message = "customer.input.email.invalid")
	private String email;
	
	
	/**
	 * User's password
	 */
	@Length(max = 12,message="customer.input.password.maxlength")
	@ValidPassword(message = "customer.input.password.invalid")
	private String password;

	
	/**
	 * Get the user's name
	 * @return user's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set a name to the user
	 * @param name : input parameter with the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the user's surname
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
	 * Get the user's email
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set the user's email
	 * @param email : input parameter with the user's email
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
	 * Set a password for the user
	 * @param password : input parameter filled with a password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * Get the user's id
	 * @return user's id
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * Set an id to the user
	 * @param customerId : input parameter with an user id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
}
