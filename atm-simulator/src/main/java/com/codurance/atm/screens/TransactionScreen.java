package com.codurance.atm.screens;

import com.codurance.atm.account.Account;
import com.codurance.atm.infrastructure.CliPrompt;

public class TransactionScreen implements Screen {
    private final CliPrompt cliPrompt;
    private Account account;

    public TransactionScreen(CliPrompt cliPrompt, Account account) {
        this.cliPrompt = cliPrompt;
        this.account = account;
    }

    @Override
    public Screen show() {
        Integer choosedMenuOption = cliPrompt.transactionScreenMenu(account.accountNumber(), account.balance());
        if (1 == choosedMenuOption) {
            return new WithdrawScreen(this.cliPrompt, this.account);
        }
        return null;
    }

    @Override
    public ScreenEnum screenName() {
        return ScreenEnum.TRANSACTION;
    }
}
