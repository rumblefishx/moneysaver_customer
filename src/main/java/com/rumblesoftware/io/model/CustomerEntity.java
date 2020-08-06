package com.rumblesoftware.io.model;

import java.io.Serializable;
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

import com.rumblesoftware.enums.ExternalIdType;
import com.rumblesoftware.io.enums.Gender;

@Entity
public class CustomerEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String RESPONSIBLE_USER = "customer_service";

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name="external_id_type",length = 25)
	@Enumerated(EnumType.STRING)
	private ExternalIdType externalIdType;
	
	@Column(name="external_id",length = 255)
	private String externalId;
	
	@Column(name="name",length = 25)
	@NotNull
	private String name;
	
	@Column(name="surname",length = 60)
	@NotNull
	private String surname;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender",length = 1)
	@NotNull
	private Gender gender;

	@Column(name="email",length = 100)
	@NotNull
	private String email;
	
	@Column(name="password",length = 512)
	private String password;
	
	@Column(name="salt",length = 512)
	private String salt;
	
	@Column(name="user_status",length = 1)
	@NotNull
	private boolean isActive;
	
	@Column(name="creation_date")
	@NotNull
	private Date creationDate;
	
	@Column(name="last_update")
	@NotNull
	private Date lastUpdate;
	
	@Column(name="responsible_user")
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
				+ ", isActive=" + isActive + ", creationDate=" + creationDate + ", lastUpdate=" + lastUpdate
				+ ", responsibleUser=" + responsibleUser + "]";
	}


	
	
}
