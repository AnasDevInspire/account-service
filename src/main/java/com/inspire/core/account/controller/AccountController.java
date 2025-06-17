package com.inspire.core.account.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inspire.core.account.entity.Account;
import com.inspire.core.account.entity.AccountType;
import com.inspire.core.account.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping
	////////////////////////////////////////////
	public ResponseEntity<List<Account>> getAcocunts() {
		List<Account> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}

	@GetMapping("/{id}")
	////////////////////////////////////////////
	public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
		Optional<Account> optinalAccount = accountService.getAccountById(id);
		if (optinalAccount.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optinalAccount.get());
	}

	@GetMapping("/{id}/{type}")
	////////////////////////////////////////////
	public ResponseEntity<Account> getAccountByIdAndType(@PathVariable Long id, @PathVariable AccountType type) {
		Optional<Account> optinalAccount = accountService.getAccountByIdAndType(id, type);
		if (optinalAccount.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optinalAccount.get());
	}

	@PostMapping
	////////////////////////////////////////////
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		return ResponseEntity.ok(accountService.createAccount(account));
	}

	@PutMapping("/{id}")
	////////////////////////////////////////////
	public ResponseEntity<Account> updateAcccount(@PathVariable Long id, @RequestBody Account account) {
		Account updateAccount = accountService.updateAccount(id, account);
		return ResponseEntity.ok(updateAccount);
	}

	@PutMapping("/{id}/{newBalance}")
	////////////////////////////////////////////
	public ResponseEntity<Account> updateAccountBalance(@PathVariable Long id, @PathVariable double newBalance) {
		Account updateAccountBalance = accountService.updateAccountBalance(id, newBalance);
		return ResponseEntity.ok(updateAccountBalance);
	}

	@DeleteMapping("/{id}")
	////////////////////////////////////////////
	public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.noContent().build();
	}
}
