package com.rumblesoftware.io.output.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerResponseDto {

	private List<String> errors;
	private CustomerOutputDTO responseBody;
	
	public CustomerResponseDto() {
		errors = new ArrayList<String>();
	}
	
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public void addError(String message) {
		this.errors.add(message);
	}
	
	public CustomerOutputDTO getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(CustomerOutputDTO responseBody) {
		this.responseBody = responseBody;
	}

	
}
