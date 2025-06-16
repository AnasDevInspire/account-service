package com.inspire.core.account.controller;

import java.util.List;

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
import com.inspire.core.account.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAcocunts() {
    	List<Account> allAccounts = accountService.getAllAccounts();
		return allAccounts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    
	@GetMapping("/{id}/{type}")
	public ResponseEntity<Account> getAccountByIdAndType(@PathVariable Long id, @PathVariable String type) {
		return accountService.getAccountById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAcccount(@PathVariable Long id, @RequestBody Account account) {
        Account updateAccount = accountService.updateAccount(id, account);
		return ResponseEntity.ok(updateAccount);
    }
    
    @PutMapping("/{id}/balance/{newBalance}")
    public ResponseEntity<Account> updateAccountBalance(@PathVariable Long id, @PathVariable double newBalance) {
    	Account updateAccountBalance = accountService.updateAccountBalance(id, newBalance);
    	return ResponseEntity.ok(updateAccountBalance);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
