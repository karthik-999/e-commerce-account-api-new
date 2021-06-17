package com.account.app.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(name="account_number", nullable = false)
	String accountNumber;
	
	@Column(name="account_name", nullable = false)
	String accountName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="USER_PROFILE_ID")
	private User user;
	
	/*
	 * One Account may have Many Addresses like Separate Shipping Address, and Billing Address
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	@JsonIgnore private Set<Address> addresses = new HashSet<Address>();
	
	/*
	 * One Account may have Many place many Orders
	 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	@JsonIgnore private Set<Order> orders = new HashSet<Order>();*/

	
}
