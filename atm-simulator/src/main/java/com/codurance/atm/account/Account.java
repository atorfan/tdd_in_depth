package com.codurance.atm.account;

public class Account {
    private final AccountBalance accountBalance;
    private final AccountNumber accountNumber;

    public Account(AccountNumber accountNumber, AccountBalance accountBalance) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public String accountNumber() {
        return accountNumber.value();
    }

    public String balance() {
        return accountBalance.toString();
    }
}
