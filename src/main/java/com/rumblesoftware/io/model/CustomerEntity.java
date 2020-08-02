package com.rumblesoftware.io.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import com.rumblesoftware.io.enums.Gender;

@Entity
public class CustomerEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String RESPONSIBLE_USER = "customer_service";

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	
	@Column(length = 25)
	@NotNull
	private String name;
	
	@Column(length = 60)
	@NotNull
	private String surname;
	
	@NotNull
	private Date dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 1)
	@NotNull
	private Gender gender;

	@Column(length = 100)
	@NotNull
	private String email;
	
	@Column(length = 512)
	@NotNull
	private String password;
	
	@NotNull
	@Column(length = 512)
	private String salt;
	
	@Column
	@NotNull
	private BigDecimal userBalance;
	
	@Column(length = 1)
	@NotNull
	private boolean isActive;
	
	@Column
	@NotNull
	private Date creationDate;
	
	@Column
	@NotNull
	private Date lastUpdate;
	
	@Column
	@NotNull
	private String responsibleUser;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getResponsibleUser() {
		return responsibleUser;
	}

	public void setResponsibleUser(String responsibleUser) {
		this.responsibleUser = responsibleUser;
	}	

	@PrePersist
	private void setDates() {
		Date now = new Date();
		
		if(this.creationDate == null) 
			creationDate = now;
		
		if(this.lastUpdate == null)
			lastUpdate = now;
		
		this.responsibleUser = RESPONSIBLE_USER;
	}

	public BigDecimal getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(BigDecimal userBalance) {
		this.userBalance = userBalance;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "CustomerEntity [customerId=" + customerId + ", name=" + name + ", surname=" + surname + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", email=" + email + ", password=" + password + ", salt=" + salt
				+ ", userBalance=" + userBalance + ", isActive=" + isActive + ", creationDate=" + creationDate
				+ ", lastUpdate=" + lastUpdate + ", responsibleUser=" + responsibleUser + "]";
	}
	
	
	
}
