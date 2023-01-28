package com.acme.banksolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.acme.banksolution.bean.BalanceTransfer;
import com.acme.banksolution.bean.ErrorBean;
import com.acme.banksolution.exception.InvalidAccountException;
import com.acme.banksolution.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AcmesolutionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcmeAccountTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountService accountService;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/acme/api";
    }

    @Test
    public void testGetBalanceBody() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/getBalance/88888888",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testInvalidAccountController() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<ErrorBean> response = restTemplate.exchange(getRootUrl() + "/getBalance/00",
                HttpMethod.GET, entity, ErrorBean.class);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getMessage(), "Invalid Account provided");
    }

    @Test
    public void testInvalidAccountService() {
        HttpHeaders headers = new HttpHeaders();
        InvalidAccountException response =
                assertThrows (InvalidAccountException.class, () -> accountService.getBalance("000000"));
        assertEquals(response.getMessage(), "Invalid Account provided");
    }

    @Test
    public void testBalanceTransferSameAccount() {
        BalanceTransfer balanceTransfer = new BalanceTransfer();
        balanceTransfer.setFromAccount("10001");
        balanceTransfer.setToAccount("10001");
        balanceTransfer.setAmount(new BigDecimal(9000));

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<BalanceTransfer> entity = new HttpEntity<BalanceTransfer>(balanceTransfer, headers);
        ResponseEntity<ErrorBean> response = restTemplate.exchange(getRootUrl() + "/transfer",
                HttpMethod.POST, entity, ErrorBean.class);
        assertNotNull(response.getBody());
        assertEquals(response.getStatusCode().value(), 400);
        assertEquals("Both Accounts can not be same", response.getBody().getMessage());
    }

}
