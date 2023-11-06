package com.codurance.atm.screens;

import com.codurance.atm.account.Account;
import com.codurance.atm.infrastructure.CliPrompt;

public class WithdrawScreen implements Screen {
    private final CliPrompt cliPrompt;
    private Account account;

    public WithdrawScreen(CliPrompt cliPrompt, Account account) {
        this.cliPrompt = cliPrompt;
        this.account = account;
    }

    @Override
    public Screen show() {
        Integer quantityToWithdraw = cliPrompt.withdrawQuantity();
        account.withdraw(quantityToWithdraw);
        cliPrompt.promptGenericMessage("Your current balance is " + this.account.balance());
        return null;
    }

    @Override
    public ScreenEnum screenName() {
        return ScreenEnum.WITHDRAW;
    }
}
