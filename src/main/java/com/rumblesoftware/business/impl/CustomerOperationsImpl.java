package com.rumblesoftware.business.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumblesoftware.business.CustomerOperations;
import com.rumblesoftware.business.ExternalTokenValMediator;
import com.rumblesoftware.exception.CustomerIdNotFoundException;
import com.rumblesoftware.exception.CustomerNotFoundException;
import com.rumblesoftware.exception.InvalidPasswordException;
import com.rumblesoftware.io.input.dto.CustomerIOConverter;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.input.dto.CustomerInputPatchDto;
import com.rumblesoftware.io.input.dto.ExternalTokenDataDto;
import com.rumblesoftware.io.input.dto.LoginDetailsDto;
import com.rumblesoftware.io.model.CustomerEntity;
import com.rumblesoftware.io.output.dto.CustomerOutputDTO;
import com.rumblesoftware.io.repository.CustomerRepository;
import com.rumblesoftware.utils.PasswordSecurity;

@Service
public class CustomerOperationsImpl implements CustomerOperations{

	@Autowired
	private CustomerIOConverter converter;

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private PasswordSecurity passwordSecurity;
	
	private Logger log = LoggerFactory.getLogger(CustomerOperationsImpl.class);
	
	@Autowired
	private ExternalTokenValMediator mediator;
	
	@Override
	public CustomerOutputDTO createCustomer(CustomerInputDTO customer) {
		
		CustomerEntity entity = converter.convertInputToEntity(customer);
		
		log.info("Saving data in the database...");
		entity = repository.save(entity);
		
		return converter.convertEntityToOutput(entity);
	}


	@Override
	public CustomerOutputDTO updateCustomer(CustomerInputPatchDto patch) {
		
		CustomerOutputDTO output = null;
		Optional<CustomerEntity> entity = null;
		
		log.info("looking for the customer in the database...");
		entity = repository.findById(patch.getCustomerId());
		
		if(entity.isEmpty())
			throw new CustomerIdNotFoundException(patch.getCustomerId());
	
		entity = Optional.of(converter.transferPatchToEntity(patch,entity.get()));
	
		log.info("saving data update in the database...");
		entity = Optional.of(repository.save(entity.get()));
		
		output = converter.convertEntityToOutput(entity.get());
		
		return output;
	}


	@Override
	public CustomerOutputDTO findUserByPasswdAndCredential(String email, String passwd) {
		CustomerOutputDTO output = null;

		if (email != null) {
			log.info("looking for the customer in the database...");
			CustomerEntity entity = repository.findCustomerByEmail(email);

			if (entity == null)
				throw new CustomerNotFoundException(email);
			
			log.info("checking user credentials...");
			output = authenticate(entity, passwd);
		}
		return output;
	}


	@Override
	public CustomerOutputDTO findUserByExternalTokenId(ExternalTokenDataDto tokenData) {
		LoginDetailsDto loginDetails = mediator.mediate(tokenData);
		
		log.info("looking for the user in the database...");
		CustomerEntity entity = repository.findCustomerByEmail(loginDetails.getEmail());
		
		if(entity == null) {
			log.info("external login user not registered. saving data in the database...");
			entity = converter.castLoginDetailsToEntity(loginDetails);
			entity.setActive(true);
			entity = repository.save(entity);
		} 
		
		return converter.convertEntityToOutput(entity);
	}

	public CustomerOutputDTO authenticate(CustomerEntity entity, String requestUserPassword) {
		CustomerOutputDTO output = null;
		
		log.info("Hashing password...");
		Optional<String> requestPassword = passwordSecurity.hashPassword(requestUserPassword, entity.getSalt());

		if (requestPassword.isPresent()) {
			if (requestPassword.get().equals(entity.getPassword())) {
				output = converter.convertEntityToOutput(entity);
			} else {
				throw new InvalidPasswordException();
			}
		}

		return output;
	}

}
