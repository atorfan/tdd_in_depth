package com.codurance.atm.account;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBalance that = (AccountBalance) o;
        return balance == that.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }
}
