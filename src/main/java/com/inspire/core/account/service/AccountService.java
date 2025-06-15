package com.inspire.core.account.service;

import com.inspire.core.account.repository.AccountRepository;
import com.inspire.core.account.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	public Optional<Account> getAccountById(Long id) {
		return accountRepository.findById(id);
	}

	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	public Account updateAccount(Long id, Account updated) {
		return accountRepository.findById(id).map(existing -> {
			existing.setType(updated.getType());
			existing.setBalance(updated.getBalance());
			existing.setBankId(updated.getBankId());
			existing.setIsDeleted(updated.getIsDeleted());
			return accountRepository.save(existing);
		}).orElseThrow(() -> new RuntimeException("Account not found"));
	}
	
	public Account updateAccountBalance(Long id, double newBalance) {
		return accountRepository.findById(id).map(existing -> {
			existing.setBalance(newBalance);
			return accountRepository.save(existing);
		}).orElseThrow(() -> new RuntimeException("Account not found"));
	}

	public Account deleteAccount(Long id) {
		return accountRepository.findById(id).map(existing -> {
			existing.setIsDeleted(0);
			return accountRepository.save(existing);
		}).orElseThrow(() -> new RuntimeException("Account not found"));
	}

}
