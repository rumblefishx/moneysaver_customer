package com.rumblesoftware.io.model;

import com.rumblesoftware.io.output.dto.CustomerResponseDto;

public class CustomerResponseFactory {

	public static CustomerResponseDto getCustomerResponse(String message) {
		CustomerResponseDto response = new CustomerResponseDto();
		response.addError(message);
		return response;
	}
}
