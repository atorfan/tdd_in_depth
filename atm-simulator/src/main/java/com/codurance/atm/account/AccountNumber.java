package com.codurance.atm.account;

public class AccountNumber {

    private final String accountNumberString;

    private AccountNumber(String accountNumberString) {
        this.accountNumberString = accountNumberString;
    }

    public String value() {
        return accountNumberString;
    }

    public static AccountNumber fromString(String accountNumberString) {
        if (notOnlyNumbers(accountNumberString)) {
            throw new InvalidAccountNumber("Account Number should only contain numbers");
        }
        if (notValidLength(accountNumberString)) {
            throw new InvalidAccountNumber("Account Number should have 6 digits length");
        }
        return new AccountNumber(accountNumberString);
    }

    private static boolean notOnlyNumbers(String accountNumber) {
        return !accountNumber.matches("\\d+");
    }

    private static boolean notValidLength(String accountNumber) {
        return accountNumber.length() != 6;
    }
}
