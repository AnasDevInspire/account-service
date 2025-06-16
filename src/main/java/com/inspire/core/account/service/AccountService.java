package com.inspire.core.account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspire.core.account.entity.Account;
import com.inspire.core.account.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	////////////////////////////////////////////
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	////////////////////////////////////////////
	public Optional<Account> getAccountById(Long id) {
		return accountRepository.findById(id);
	}

	////////////////////////////////////////////
	public Optional<Account> getAccountByIdAndType(Long id, String type) {
		return accountRepository.findByIdAndType(id, type);
	}

	////////////////////////////////////////////
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}
	
	////////////////////////////////////////////
	public Account updateAccount(Long id, Account updated) {
		Optional<Account> optinalAccount = accountRepository.findById(id);
		
		if (optinalAccount.isEmpty()) {
			throw new RuntimeException("Account not found");
		}
		
		Account account = optinalAccount.get(); 
		account.setBalance(updated.getBalance());
		account.setBankId(updated.getBankId());
		account.setIsDeleted(updated.getIsDeleted());
		
		return accountRepository.save(account);
	}
	
	////////////////////////////////////////////
	public Account updateAccountBalance(Long id, double newBalance) {
		Optional<Account> optionalAccount = accountRepository.findById(id);
		if (optionalAccount.isEmpty()) {
			throw new RuntimeException("Account not found");
		}

		Account account = optionalAccount.get();
		account.setBalance(newBalance);
		return accountRepository.save(account);
	}

	////////////////////////////////////////////
	public Account deleteAccount(Long id) {
		Optional<Account> optionalAccount = accountRepository.findById(id);

		if (optionalAccount.isEmpty()) {
			throw new RuntimeException("Account not found");
		}

		Account account = optionalAccount.get();
		account.setIsDeleted(1);

		return accountRepository.save(account);

	}
}
