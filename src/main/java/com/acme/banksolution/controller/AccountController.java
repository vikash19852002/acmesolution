package com.acme.banksolution.controller;

import com.acme.banksolution.bean.BalanceTransfer;
import com.acme.banksolution.service.AccountService;
import com.acme.banksolution.util.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class to accept all request and with help of Service layer, it provides response
 *
 * @author VKumar
 */
@RestController
@RequestMapping("/acme/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getBalance/{accountNumber}")
    public String getBalance(@PathVariable @NonNull String accountNumber) {
        return "Account: " + accountNumber + " Balance: " + accountService.getBalance(accountNumber);
    }

    @PostMapping("/transfer")
    public @ResponseBody String balanceTransfer(@RequestBody @NonNull BalanceTransfer balanceTransfer) {
        AccountUtils.validateTransferBean(balanceTransfer);
        return "Balance transfer: " + (accountService.transferAmount(balanceTransfer) == true ? "Successful" : "Failed");
    }
}
