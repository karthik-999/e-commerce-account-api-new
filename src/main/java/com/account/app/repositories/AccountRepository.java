package com.account.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.app.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {



}
