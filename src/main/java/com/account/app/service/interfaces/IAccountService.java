package com.account.app.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.account.app.entities.Account;
import com.account.app.requests.createAccountDTO;

public interface IAccountService {

	public List<Account> findAll(Pageable pageable);

	public Account getAccount(Long accountId);

	public Account saveAccount(Account account);

	public Account saveAccount(createAccountDTO account);

}
