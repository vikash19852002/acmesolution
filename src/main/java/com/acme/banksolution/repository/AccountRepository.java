package com.acme.banksolution.repository;

import com.acme.banksolution.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is repository class, all JPA CRUD methods are available by default.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {}
