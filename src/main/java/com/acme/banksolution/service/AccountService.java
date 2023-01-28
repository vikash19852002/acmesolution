package com.acme.banksolution.service;

import com.acme.banksolution.bean.BalanceTransfer;

import java.math.BigDecimal;

/**
 * This is parent interface to interact with controller and defines all required methods.
 */
public interface AccountService {

    BigDecimal getBalance(String accountNumber);

    boolean transferAmount(BalanceTransfer balanceTransfer);
}
