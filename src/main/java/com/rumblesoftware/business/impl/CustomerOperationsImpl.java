package com.rumblesoftware.business.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumblesoftware.business.CustomerOperations;
import com.rumblesoftware.exception.CustomerNotFoundException;
import com.rumblesoftware.io.input.dto.CustomerIOConverter;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.input.dto.CustomerInputPatchDto;
import com.rumblesoftware.io.model.CustomerEntity;
import com.rumblesoftware.io.output.dto.CustomerOutputDTO;
import com.rumblesoftware.io.repository.CustomerRepository;

@Service
public class CustomerOperationsImpl implements CustomerOperations{

	@Autowired
	private CustomerIOConverter converter;

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public CustomerOutputDTO createCustomer(CustomerInputDTO customer) {
		
		CustomerEntity entity = converter.convertInputToEntity(customer);
		
		System.out.println("Objeto Usado--> " + entity.toString());
		entity = repository.save(entity);
		
		return converter.convertEntityToOutput(entity);
	}


	@Override
	public CustomerOutputDTO updateCustomer(CustomerInputPatchDto patch) {
		
		CustomerOutputDTO output = null;
		Optional<CustomerEntity> entity = null;
		
		//look inside of the database
		entity = repository.findById(patch.getCustomerId());
		
		if(entity.isEmpty())
			throw new CustomerNotFoundException(patch.getCustomerId());
		
		entity = Optional.of(converter.transferPatchToEntity(patch,entity.get()));
		
		entity = Optional.of(repository.save(entity.get()));
		
		output = converter.convertEntityToOutput(entity.get());
		
		return output;
	}
	

}
