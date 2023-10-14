package com.codurance.atm.screens;

import com.codurance.atm.account.*;
import com.codurance.atm.infrastructure.CliPrompt;

public class WelcomeScreen implements Screen {
    private final CliPrompt cliPrompt;
    private final AccountService accountService;

    private String pin;
    private AccountNumber accountNumber;

    public WelcomeScreen(CliPrompt cliPrompt, AccountService accountService) {
        this.cliPrompt = cliPrompt;
        this.accountService = accountService;
    }

    public Screen show() {
        Account account = askAccountNumber();
        return new TransactionScreen(cliPrompt, account);
    }

    @Override
    public ScreenEnum screenName() {
        return ScreenEnum.WELCOME_SCREEN;
    }

    private Account askAccountNumber() {
        try {
            accountNumber = AccountNumber.fromString(cliPrompt.accountNumber());
        } catch (InvalidAccountNumber invalidAccountNumber) {
            cliPrompt.promptGenericMessage(invalidAccountNumber.getMessage() + "\n");
            return askAccountNumber();
        }
        return askPin();
    }

    private Account askPin() {
        do{
            pin = cliPrompt.pin();
        } while (isInvalidPIN(pin));

        Account account = findAccount(accountNumber, pin);
        if (account == null) show(); //start over
        return account;
    }

    private Account findAccount(AccountNumber accountNumber, String pin) {
        try {
            return accountService.findBy(accountNumber, pin);
        } catch (InvalidAccountPin e) {
            cliPrompt.promptGenericMessage("Invalid Account Number/PIN\n");
        }
        return null;
    }

    private boolean isInvalidPIN(String pin) {
        if(!pin.matches("\\d+")) {
            cliPrompt.promptGenericMessage("PIN should only contain numbers\n");
            return true;
        }
        if(pin.length() != 6) {
            cliPrompt.promptGenericMessage("PIN should have 6 digits length\n");
            return true;
        }
        return false;
    }
}
