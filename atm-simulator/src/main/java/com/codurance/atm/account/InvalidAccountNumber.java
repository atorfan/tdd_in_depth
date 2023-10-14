package com.codurance.atm.account;

public class InvalidAccountNumber extends RuntimeException {

    public InvalidAccountNumber(String message) {
        super(message);
    }
}
