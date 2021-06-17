package com.account.app.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.account.app.IUserService;
import com.account.app.dto.UserResponseModel;
import com.account.app.entities.Account;
import com.account.app.entities.Address;
import com.account.app.entities.User;
import com.account.app.repositories.AccountRepository;
import com.account.app.requests.createAccountDTO;
import com.account.app.service.interfaces.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	IUserService userService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<Account> findAll(Pageable pageable) {

		return accountRepository.findAll(pageable).getContent();

	}

	@Override
	public Account getAccount(Long accountId) {
		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		if (optionalAccount.isPresent()) {
			return optionalAccount.get();
		}
		return new Account();
	}

	@Override
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account saveAccount(createAccountDTO accountDTO) {
		Account account = new Account();
		ResponseEntity<UserResponseModel> responseModel;
		if(!(accountDTO.getAccountName() != null && accountDTO.getAccountNumber() != null )) {
			throw new RuntimeException("check request parameters");
		}
//		if(accountDTO.getUser() != null) {
//			responseModel = userService.getUser(accountDTO.getUser());
//		}
		account.setAccountName(accountDTO.getAccountName());
		account.setAccountNumber(accountDTO.getAccountNumber());
		User user = new User();
		BeanUtils.copyProperties(accountDTO, user);
		user.setRgistrationDate(Date.valueOf(LocalDate.now()));
		user.setEncryptedPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
		user.setUserId(UUID.randomUUID().toString());
		account.setUser(user);
		return  accountRepository.save(account);
	}
}
