package com.account.app.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.account.app.IUserService;
import com.account.app.entities.Account;
import com.account.app.entities.UserEntity;
import com.account.app.repositories.AccountRepository;
import com.account.app.requests.createAccountDTO;
import com.account.app.service.interfaces.IAccountService;

class AccountServiceImplTest {

	@InjectMocks
	IAccountService accountService = new AccountServiceImpl();

	@Autowired
	IUserService userService;
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Mock
	AccountRepository accountRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

//	@Test
//	void testFindAll() {
//		Account account = new Account();
//		List<Account> accountsList = new ArrayList<Account>();
//		int page =1;
//		int size = 9;
//		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
////		when(accountRepository.findAll(Mockito.any(Pageable.class)).getContent()).thenReturn(accountsList);
////		List<Account> newAccountsList = accountService.findAll(pageable);
////		assertNotNull(newAccountsList);
//	}

	@Test
	void testGetAccount() {
		Account account = new Account();
		account.setId(1L);
		account.setAccountName("Karthik");
		Optional<Account> optionalAccount = Optional.of(account);
		when(accountRepository.findById(Mockito.anyLong())).thenReturn(optionalAccount);
		account = accountService.getAccount(1L);
		assertNotNull(account);
	}
	
	@Test
	void testGetAccountForNull() {
		Account account = new Account();
		Optional<Account> optionalAccount = Optional.of(account);
		when(accountRepository.findById(Mockito.anyLong())).thenReturn(optionalAccount);
		account = accountService.getAccount(1L);
		assertNotNull(account);
	}

	@Test
	void testSaveAccountCreateAccountDTO() {
		createAccountDTO createAccountDTO = new createAccountDTO();
		Account account = new Account();
		account.setId(1L);
		account.setAccountName("Karthik");
		account.setAccountNumber("SBIN001");
		account.setUser(new UserEntity());
		createAccountDTO.setAccountName("Karthik");
		createAccountDTO.setAccountNumber("SBIN001");
		createAccountDTO.setUserName("KarthikTIM");
		createAccountDTO.setPassword("password");
		when(accountRepository.save(Mockito.any())).thenReturn(account);
		when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("xxxxx - xxxxxx");
		account = accountService.saveAccount(createAccountDTO);
		assertNotNull(account);
	}

	
	@Test
	void testSaveAccountAccount() {
		Account account = new Account();
		account.setId(1L);
		account.setAccountName("Karthik");
		account.setAccountNumber("SBIN001");
		account.setUser(new UserEntity());	
		when(accountRepository.save(Mockito.any())).thenReturn(account);
		account = accountService.saveAccount(account);
		assertNotNull(account);
	}
}
