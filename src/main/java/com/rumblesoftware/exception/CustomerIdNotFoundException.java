package com.rumblesoftware.exception;

/**
 * Exception thrown when a customer ID isn't found during a search
 * @author Cleiton
 *
 */
public class CustomerIdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerIdNotFoundException(Long id) {
		super(String.valueOf(id));
	}
}
