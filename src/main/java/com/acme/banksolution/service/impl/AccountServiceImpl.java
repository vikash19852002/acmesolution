package com.acme.banksolution.service.impl;

import com.acme.banksolution.bean.BalanceTransfer;
import com.acme.banksolution.entity.Account;
import com.acme.banksolution.exception.InsufficientFundException;
import com.acme.banksolution.exception.InvalidAccountException;
import com.acme.banksolution.repository.AccountRepository;
import com.acme.banksolution.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * This class provides all account related service with help of repository and controller
 * the transaction behavior in concurrent environment.
 *
 * @author VKumar
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class); ;

    /**
     * This method helps to get Account balance, if Account exists
     * @param accountNumber
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public BigDecimal getBalance(String accountNumber) {
        LOGGER.debug("GetBalance method with account number {}", accountNumber);
        return getAccount(accountNumber).getBalance();
    }

    /**
     * This method helps to transfer the amount from one account to other account, it also controls
     * the current transaction with same account with Version info inside DB.
     * @param balanceTransfer
     * @return boolean
     */
    @Override
    @Transactional
    public boolean transferAmount(BalanceTransfer balanceTransfer) {
        LOGGER.debug("Transfer method called with details: {}", balanceTransfer);
        Account fromAccount = getAccount(balanceTransfer.getFromAccount());
        Account toAccount = getAccount(balanceTransfer.getToAccount());
        // Validate the amount is available in account or not, further we can validate given currency with account currency
        if (fromAccount.getBalance().compareTo(balanceTransfer.getAmount()) >= 0) {
            BigDecimal amount = fromAccount.getBalance().subtract(balanceTransfer.getAmount());
            fromAccount.setBalance(amount);
            toAccount.setBalance(toAccount.getBalance().add(balanceTransfer.getAmount()));
        } else {
            throw new InsufficientFundException(100, "Insufficient Fund to transfer in Account: " + fromAccount.getAccountNumber());
        }
        return true;
    }

    /**
     * This is common method to get Account to help other methods.
     * @param accountNumber
     * @return Account
     * @throws InvalidAccountException
     */
    private Account getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber)
                .orElseThrow(() -> new InvalidAccountException(101, "Invalid Account provided"));
    }

}
