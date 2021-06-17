package com.account.app.requests;

import lombok.Data;

@Data
public class createAccountDTO {

	private String accountNumber;

	private String accountName;

	private String userName;

	private String firstName;

	private String lastName;

	private String sex;

	private String memberType;
	
	private String email;
	
	private String password;

}
