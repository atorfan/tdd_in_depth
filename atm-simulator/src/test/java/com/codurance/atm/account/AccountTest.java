package com.codurance.atm.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void shouldHaveAccountNumberIndicated() {
        final String accountNumber = "123456";
        final Account account = new Account(AccountNumber.fromString(accountNumber), 100);
        assertEquals(accountNumber, account.accountNumber());
    }

    @Test
    void shouldHaveBalanceWhenCreateAccount() {
        final int accountNumber = 123456;
        final Integer balance = 100;
        final Account account = new Account(new AccountNumber(accountNumber), balance);
        assertEquals(balance.toString(), account.balance());
    }
}
