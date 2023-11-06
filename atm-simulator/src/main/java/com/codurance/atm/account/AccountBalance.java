package com.codurance.atm.account;

public record AccountBalance(int balance) {

    public static AccountBalance empty() {
        return new AccountBalance(0);
    }

    public AccountBalance substract(Integer quantityToWithdraw) {
        return new AccountBalance(this.balance - quantityToWithdraw);
    }

    @Override
    public String toString() {
        return String.valueOf(balance);
    }
}
