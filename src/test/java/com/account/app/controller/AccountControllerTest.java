package com.account.app.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.account.app.entities.Account;
import com.account.app.requests.createAccountDTO;
import com.account.app.service.interfaces.IAccountService;

public class AccountControllerTest {

	
	@InjectMocks
	AccountController accountController = new AccountController();

	@Mock
	IAccountService accountService;
	
	@Mock
	Environment environment;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testStatus() {
		when(environment.getProperty(Mockito.anyString())).thenReturn("8193");
		String status = accountController.status();
		assertNotNull(status);
	}
	
	
	@Test
	void testGetAccountsByPage() {
		Account account = new Account();
		List<Account> accountsList = new ArrayList<Account>();
		when(accountService.findAll(Mockito.any(Pageable.class))).thenReturn(accountsList);
		ResponseEntity<List<Account>> newAccountsList;
		int page = 1;
		int size = 2;
		newAccountsList  = accountController.getAccountsByPage(page, size);
		assertNotNull(newAccountsList);
	}

	@Test
	void testGetAccount() {
		Account account = new Account();
		List<Account> accountsList = new ArrayList<Account>();
		when(accountService.getAccount(Mockito.any(Long.class))).thenReturn(account);
		ResponseEntity<Account> newAccount;
		Long accountId = 1L;
		newAccount  = accountController.getAccount(accountId);
		assertNotNull(newAccount);
	}
	
	@Test
	void testCreateAccount() {
		createAccountDTO createAccountDTO = new createAccountDTO();
		Account account = new Account();
		List<Account> accountsList = new ArrayList<Account>();
		when(accountService.saveAccount(Mockito.any(createAccountDTO.class))).thenReturn(account);
		ResponseEntity<Account> newAccount = new ResponseEntity<Account>(HttpStatus.OK);
		Long accountId = 1L;
		newAccount  = accountController.createAccount(createAccountDTO);
		assertNotNull(newAccount);
	}
	
}
