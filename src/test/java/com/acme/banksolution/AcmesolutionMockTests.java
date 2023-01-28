package com.acme.banksolution;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.acme.banksolution.bean.BalanceTransfer;
import com.acme.banksolution.controller.AccountController;
import com.acme.banksolution.entity.Account;
import com.acme.banksolution.exception.InsufficientFundException;
import com.acme.banksolution.repository.AccountRepository;
import com.acme.banksolution.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AcmesolutionApplication.class)
class AcmesolutionMockTests {

	@Autowired
	private AccountController accountController;

	@Autowired
	private AccountService accountService;

	@MockBean
	private AccountRepository accountRepository;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(accountController);
	}

	private Account account1;
	private Account account2;

	@BeforeEach
	public void setup() {
		account1 = new Account();
		account1.setAccountNumber("10001");
		account1.setBalance((new BigDecimal(4000)));
		when(accountRepository.findById("10001")).thenReturn(Optional.of(account1));

		account2 = new Account();
		account2.setAccountNumber("10002");
		account2.setBalance((new BigDecimal(5000)));
		when(accountRepository.findById("10002")).thenReturn(Optional.of(account2));
	}

	@Test
	public void testMockBalance() {
		BigDecimal balance = accountService.getBalance("10001");
		assertTrue(balance.intValue() == 4000);
	}

	@Test
	public void testBalanceTransfer() {
		BalanceTransfer balanceTransfer = new BalanceTransfer();
		balanceTransfer.setFromAccount("10001");
		balanceTransfer.setToAccount("10002");
		balanceTransfer.setAmount(new BigDecimal(3000));
		assertTrue(accountService.transferAmount(balanceTransfer));
		assertEquals(account1.getBalance().intValue(), 1000);
		assertEquals(account2.getBalance().intValue(), 8000);

		balanceTransfer.setFromAccount("10002");
		balanceTransfer.setToAccount("10001");
		balanceTransfer.setAmount(new BigDecimal(2000));
		assertTrue(accountService.transferAmount(balanceTransfer));
		assertEquals(account1.getBalance().intValue(), 3000);
		assertEquals(account2.getBalance().intValue(), 6000);
	}

	@Test
	public void testInsufficientBalanceTransfer() {
		BalanceTransfer balanceTransfer = new BalanceTransfer();
		balanceTransfer.setFromAccount("10001");
		balanceTransfer.setToAccount("10002");
		balanceTransfer.setAmount(new BigDecimal(9000));
		InsufficientFundException response =
		assertThrows (InsufficientFundException.class, () -> accountService.transferAmount(balanceTransfer));
		assertEquals(response.getMessage(), "Insufficient Fund to transfer in Account: 10001");
	}

}
