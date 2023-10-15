package com.codurance.atm.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void shouldHaveAccountNumberIndicated() {
        assertEquals(String.valueOf(accountNumber), account.accountNumber());
    }

    @Test
    void shouldHaveBalanceWhenCreateAccount() {
        assertEquals(String.valueOf(balance), account.balance());
    }

    private int accountNumber;
    private int balance;
    private Account account;

    @BeforeEach
    void setUp() {
        accountNumber = 123456;
        balance = 100;
        account = new Account(new AccountNumber(accountNumber), new AccountBalance(balance));
    }
}
