package com.rumblesoftware.exception;

/**
 * Exception thrown when an user tries to create a profile with an email which is already registered
 * @author Cleiton
 *
 */
public class EmailAlreadyRegisteredException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyRegisteredException(String message) {
		super(message);
	}

}
