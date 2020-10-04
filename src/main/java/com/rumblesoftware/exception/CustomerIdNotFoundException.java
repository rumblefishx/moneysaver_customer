package com.rumblesoftware.exception;

public class CustomerIdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerIdNotFoundException(Long id) {
		super(String.valueOf(id));
	}
}
