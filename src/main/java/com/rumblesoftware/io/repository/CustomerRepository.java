package com.rumblesoftware.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rumblesoftware.io.model.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	@Query(value = "Select c from TCustomer c where c.email like :email")
	public CustomerEntity findCustomerByEmail(@Param("email") String email);
	
	@Query(value = "Select c from TCustomer c where c.externalId like :externalId")
	public CustomerEntity findCustomerByExternalId(@Param("externalId") String externalId);

}
