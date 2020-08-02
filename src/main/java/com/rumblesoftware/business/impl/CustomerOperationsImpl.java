package com.rumblesoftware.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumblesoftware.business.CustomerOperations;
import com.rumblesoftware.io.input.dto.CustomerIOConverter;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.model.CustomerEntity;
import com.rumblesoftware.io.output.dto.CustomerOutputDTO;
import com.rumblesoftware.io.repository.CustomerRepository;

@Service
public class CustomerOperationsImpl implements CustomerOperations{

	@Autowired
	private CustomerIOConverter customerConverter;

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public CustomerOutputDTO createCustomer(CustomerInputDTO customer) {
		
		CustomerEntity entity = customerConverter.convertInputToEntity(customer);
		
		System.out.println("Objeto Usado--> " + entity.toString());
		entity = repository.save(entity);
		
		return customerConverter.convertEntityToOutput(entity);
	}

	@Override
	public CustomerOutputDTO findCustomerById(Long customerId) {
		// TODO implement find customer by id
		return null;
	}

	@Override
	public CustomerOutputDTO findCustomerByEmail(String customerEmail) {
		// TODO implement find customer by email
		return null;
	}

	@Override
	public CustomerOutputDTO deactivateCustomer(Long customerId) {
		// TODO implement deactivate customer
		return null;
	}

	@Override
	public CustomerOutputDTO updateCustomer() {
		// TODO implement update customer
		return null;
	}
	

}
