package com.rumblesoftware.io.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rumblesoftware.io.model.CustomerEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
/**
 * Class responsible for repository testing
 * @author Cleiton
 *
 */
public class CustomerRepositoryTests {
	
	private static final String EXISTANT_EMAIL = "patricia.pilar@uol.com.br";
	private static final String MISSING_EMAIL = "maurice.dolly@ie.com";
	private static final String EXISTANT_EXTERNAL_ID = "abcT10@#";
	private static final String MISSING_EXTERNAL_ID = "SD2sdc13$";
	private static final Long EXISTANT_CUSTOMER_ID = 7L;
	private static final Long NON_EXISTANT_CUSTOMER_ID = 12000L;
	
	@Autowired
	private CustomerRepository repository;
	
	/**
	 * Method responsible for check if repository if able to find a stored email
	 */
	@Test
	public void findCustomerByEmailOkTest() {
		Optional<CustomerEntity> customer = Optional.ofNullable(repository.findCustomerByEmail(EXISTANT_EMAIL));
		assertTrue(customer.isPresent());
	}
	
	@Test
	public void findCustomerByEmailFailTest() {
		Optional<CustomerEntity> customer = Optional.ofNullable(repository.findCustomerByEmail(MISSING_EMAIL));
		assertFalse(customer.isPresent());
	}
	
	@Test
	public void findExternalIdOkTest() {
		//List<CustomerEntity> list = repository.findAll();
		
		Optional<CustomerEntity> customer = Optional.ofNullable(repository.findCustomerByExternalId(EXISTANT_EXTERNAL_ID));
		assertTrue(customer.isPresent());	
	}

	@Test
	public void findExternalIdFailTest() {
		Optional<CustomerEntity> customer = Optional.ofNullable(repository.findCustomerByExternalId(MISSING_EXTERNAL_ID));
		assertFalse(customer.isPresent());	
	}
	
	@Test
	public void findCustomerByIdOKTest() {
		Optional<CustomerEntity> customer = repository.findById(EXISTANT_CUSTOMER_ID);
		assertTrue(customer.isPresent());
	}
	
	@Test
	public void findCustomerByIdFailTest() {
		Optional<CustomerEntity> customer = repository.findById(NON_EXISTANT_CUSTOMER_ID);
		assertFalse(customer.isPresent());
	}
}
