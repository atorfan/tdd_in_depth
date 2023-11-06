package com.codurance.atm.screens;

import com.codurance.atm.account.Account;
import com.codurance.atm.infrastructure.CliPrompt;

public class DepositScreen implements Screen {
    private final CliPrompt cliPrompt;
    private Account account;

    public DepositScreen(CliPrompt cliPrompt, Account account) {
        this.cliPrompt = cliPrompt;
        this.account = account;
    }

    @Override
    public Screen show() {
        Integer quantityToDeposit = cliPrompt.depositQuantity();
        account.deposit(quantityToDeposit);
        cliPrompt.promptGenericMessage("Your current balance is " + this.account.balance());
        return null;
    }

    @Override
    public ScreenEnum screenName() {
        return ScreenEnum.DEPOSIT;
    }
}
