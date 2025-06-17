package com.inspire.core.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inspire.core.account.entity.Account;
import com.inspire.core.account.entity.AccountType;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByIdAndType(Long id, AccountType type);

}
