package com.rumblesoftware.business;

import org.springframework.stereotype.Service;

import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.output.dto.CustomerOutputDTO;

@Service
public interface CustomerOperations {
	
	public CustomerOutputDTO createCustomer(CustomerInputDTO customer);
	public CustomerOutputDTO findCustomerById(Long customerId);
	public CustomerOutputDTO findCustomerByEmail(String customerEmail);
	public CustomerOutputDTO deactivateCustomer(Long customerId);
	public CustomerOutputDTO updateCustomer();
}
