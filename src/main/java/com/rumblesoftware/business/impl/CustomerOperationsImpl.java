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
import com.rumblesoftware.exception.EmailAlreadyRegisteredException;
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
import com.rumblesoftware.utils.PostOfficer;

/**
 * Business class with the main operations methods
 * Class responsible for create, update and authenticate users
 * @author Cleiton
 *
 */
@Service
public class CustomerOperationsImpl implements CustomerOperations{

	private static final String DUPLICATED_EMAIL_MESSAGE = "customer.email.duplicated.message";
	
	/**
	 * CustomerIOConverter injection 
	 */
	@Autowired
	private CustomerIOConverter converter;

	/**
	 * CustomerRepository injection
	 */
	@Autowired
	private CustomerRepository repository;
	
	/**
	 * PasswordSecurity injection 
	 */
	@Autowired
	private PasswordSecurity passwordSecurity;
	
	/**
	 * Get Logger instance in order to log error ocurrences
	 */
	private Logger log = LoggerFactory.getLogger(CustomerOperationsImpl.class);
	
	/**
	 * Inject an instance of ExternalTokenValMediator
	 */
	@Autowired
	private ExternalTokenValMediator mediator;
	
	/**
	 * Inject an instance of PostOfficer
	 */
	@Autowired
	private PostOfficer po;
	
	/**
	 * Method responsible for create a new user
	 */
	@Override
	public CustomerOutputDTO createCustomer(CustomerInputDTO customer) {
		
		checkEmailRegister(customer.getEmail());
		
		CustomerEntity entity = converter.convertInputToEntity(customer);
		
		log.info("Saving data in the database...");
		entity = repository.save(entity);
		
		return converter.convertEntityToOutput(entity);
	}


	/**
	 * method responsible for update a customer
	 */
	@Override
	public CustomerOutputDTO updateCustomer(CustomerInputPatchDto patch) {
		
		CustomerOutputDTO output = null;
		Optional<CustomerEntity> entity = null;
		
		log.info("looking for the customer in the database...");
		entity = repository.findById(patch.getCustomerId());
		
		if(entity == null)
			throw new CustomerIdNotFoundException(patch.getCustomerId());
	
		entity = Optional.of(converter.transferPatchToEntity(patch,entity.get()));
	
		log.info("saving data update in the database...");
		entity = Optional.of(repository.save(entity.get()));
		
		output = converter.convertEntityToOutput(entity.get());
		
		return output;
	}

	/**
	 * Method responsible for find an user based in an email and a password
	 */
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


	/**
	 * A method responsible for find an user based in an external token
	 */
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

	/**
	 * Method responsible for authenticate users based in internal user's details
	 * @param entity : input parameter with local user details
	 * @param requestUserPassword : input parameter with user's password
	 * @return
	 */
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
	
	/**
	 * Method responsible for check email validity
	 * Is it a valid email ?
	 * Is it a duplicated email ?
	 * @param email : input parameter filled with user email
	 */
	private void checkEmailRegister(String email) {	
		
		if(email != null && !(email.trim().length() == 0)) {
			Optional<CustomerEntity> entity = Optional.ofNullable(repository.findCustomerByEmail(email));
		
			if(entity.isPresent())
				throw new EmailAlreadyRegisteredException(po.getMessage(DUPLICATED_EMAIL_MESSAGE));
		}
		
	}

}
