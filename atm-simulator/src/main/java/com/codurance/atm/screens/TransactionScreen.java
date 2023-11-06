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
        return switch (cliPrompt.transactionScreenMenu(account.accountNumber(), account.balance())) {
            case 1 -> new WithdrawScreen(this.cliPrompt, this.account);
            case 2 -> new DepositScreen(this.cliPrompt, this.account);
            default -> null;
        };
    }

    @Override
    public ScreenEnum screenName() {
        return ScreenEnum.TRANSACTION;
    }
}
