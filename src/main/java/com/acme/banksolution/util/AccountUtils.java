package com.acme.banksolution.util;

import com.acme.banksolution.bean.BalanceTransfer;
import com.acme.banksolution.exception.InsufficientFundException;
import com.acme.banksolution.exception.InvalidAccountException;

import java.math.BigDecimal;

/**
 * This is utility class to hold all common functionalities for any class.
 *
 * @author VKumar
 */
public class AccountUtils {

    public static void validateTransferBean(BalanceTransfer balanceTransfer) {
        // Provide the basic checks for Accounts and given amount
        if (balanceTransfer.getFromAccount() == null || balanceTransfer.getFromAccount() == null) {
            throw new InvalidAccountException(102, "Invalid Account found for transfer");
        }
        if (balanceTransfer.getFromAccount().equals(balanceTransfer.getToAccount())) {
            throw new InvalidAccountException(102, "Both Accounts can not be same");
        }
        if (balanceTransfer.getAmount() == null || balanceTransfer.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InsufficientFundException(102, "Invalid Amount found for transfer");
        }
    }
}
