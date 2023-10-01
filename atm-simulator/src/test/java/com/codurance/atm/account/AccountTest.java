package com.codurance.atm.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void shouldHaveAccountNumberIndicated() {
        final String accountNumber = "XXXXXX";
        final Account account = new Account(accountNumber, 100);
        assertEquals(accountNumber, account.accountNumber());
    }

    @Test
    void shouldHaveBalanceWhenCreateAccount() {
        final Integer balance = 100;
        final Account account = new Account("XXXXXX", balance);
        assertEquals(balance.toString(), account.balance());
    }
}
