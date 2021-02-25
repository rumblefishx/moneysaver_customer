package com.rumblesoftware.exception;

/**
 * Exception thrown when a Customer isn't found during a search
 * @author Cleiton
 *
 */
public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomerNotFoundException(String email) {
		super(email);
	}

}
