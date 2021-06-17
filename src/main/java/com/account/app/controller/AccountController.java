package com.account.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.app.entities.Account;
import com.account.app.requests.createAccountDTO;
import com.account.app.service.interfaces.IAccountService;

@RestController
@RequestMapping("/account/")
public class AccountController {

	@Autowired
	IAccountService accountService;
	
	@Autowired
	public Environment environment;
	
	@GetMapping("/status/check")
	public String status() {
		return environment.getProperty("local.server.port");
	}

	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<Account>> getAccountsByPage(@PathVariable(value = "page", required = true) int page,
			@PathVariable(value = "size", required = true) int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		List<Account> accounts = accountService.findAll(pageable);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@GetMapping("/get/{AccountId}")
	public ResponseEntity<Account> getAccount(@PathVariable("AccountId") Long accountId) {
		return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody createAccountDTO accountDTO) {
		return new ResponseEntity<>(accountService.saveAccount(accountDTO), HttpStatus.CREATED);
	}
}
