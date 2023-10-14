package com.codurance.atm.account;

public class AccountService {
    public Account findBy(AccountNumber accountNumber, String pin) {
        return new Account(accountNumber, 0);
    }
}
