package com.codurance.atm.account;

public class AccountNumber {

    private final int accountNumber;

    private AccountNumber(String accountNumberString) {
        this.accountNumber = Integer.parseInt(accountNumberString);
    }

    public AccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String value() {
        return String.valueOf(accountNumber);
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
