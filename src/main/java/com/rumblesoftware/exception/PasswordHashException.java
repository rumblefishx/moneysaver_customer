package com.rumblesoftware.exception;

/**
 * Exception thrown when an error occurs during a password hash generation
 * @author Cleiton
 *
 */
public class PasswordHashException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -545293247320138327L;

	public PasswordHashException(String message) {
		super(message);
	}
}
