package com.account.app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_ADDRESS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "house_number", nullable = false)
	String houseNumber;

	@Column(name = "street_address", nullable = false)
	String streetAddress;

	String city;

	String state;

	@Column(name = "zip_code", nullable = false)
	String zipCode;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	// @JsonIgnore
	Account account;
}
