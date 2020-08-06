package com.rumblesoftware.business;

import org.springframework.stereotype.Service;

import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.input.dto.CustomerInputPatchDto;
import com.rumblesoftware.io.output.dto.CustomerOutputDTO;

@Service
public interface CustomerOperations {
	
	public CustomerOutputDTO createCustomer(CustomerInputDTO customer);
	public CustomerOutputDTO updateCustomer(CustomerInputPatchDto customer);
}
