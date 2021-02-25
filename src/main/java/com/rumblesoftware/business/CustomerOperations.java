package com.rumblesoftware.business;

import org.springframework.stereotype.Service;

import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.input.dto.CustomerInputPatchDto;
import com.rumblesoftware.io.input.dto.ExternalTokenDataDto;
import com.rumblesoftware.io.output.dto.CustomerOutputDTO;

/**
 * Application Main Business Interface
 */
@Service
public interface CustomerOperations {
	
	/**
	 * Method responsible for create a new Customer Profile
	 * @param customer : input parameter with customer details
	 * @return CustomerOutputDTO instance with customer details 
	 */
	public CustomerOutputDTO createCustomer(CustomerInputDTO customer);
	
	/**
	 * Method responsible for create a new Customer Profile
	 * @param customer : input parameter with customer details to update
	 * @return CustomerOutputDTO instance with updated customer details
	 */
	public CustomerOutputDTO updateCustomer(CustomerInputPatchDto customer);
	
	/**
	 * Method responsible for find a customer based in two input parameters (email and password)
	 * @param email : input parameter referring to the user email
	 * @param passwd : input parameter referring to the user password
	 * @return CustomerOutputDTO instance with customer details
	 */
	public CustomerOutputDTO findUserByPasswdAndCredential(String email,String passwd);
	
	/**
	 * Method responsible for find a customer based in an external token 
	 * @param tokenData : input parameter with token details
	 * @return CustomerOutputDTO instance with customer details
	 */
	public CustomerOutputDTO findUserByExternalTokenId(ExternalTokenDataDto tokenData);

	/**
	 * Method responsible for find a customer based in his id
	 * @param customerId : input parameter with the customer id
	 * @return CustomerOutputDTO instance with customer details
	 */
	public CustomerOutputDTO findByCustomerId(Long customerId);
}
