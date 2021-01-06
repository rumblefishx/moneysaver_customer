package com.rumblesoftware.exception;

/**
 * Exception thrown when a password isn't valid according to the application criterias
 * @author Cleiton
 *
 */
public class InvalidPasswordException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
		super();
	}
}
