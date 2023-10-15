package com.codurance.atm.account;

public class AccountBalance {

    private final int balance;

    public AccountBalance(int balance) {
        this.balance = balance;
    }

    public static AccountBalance empty() {
        return new AccountBalance(0);
    }

    @Override
    public String toString() {
        return String.valueOf(balance);
    }
}
