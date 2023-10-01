package com.codurance.atm;

import com.codurance.atm.account.Account;
import com.codurance.atm.account.AccountService;
import com.codurance.atm.infrastructure.CliPrompt;
import com.codurance.atm.screens.TransactionScreen;
import com.codurance.atm.screens.WelcomeScreen;
import com.codurance.atm.screens.WithdrawScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StepDefinitions {

    private AccountService accountService;
    private WelcomeScreen welcomeScreen;
    private WithdrawScreen withdrawScreen;
    private CliPrompt cliPrompt;

    @Given("there's an Account with number {int}, PIN {int} and balance {int}")
    public void givenExistingAccountNumberAndPinWithBalance(int accountNumber, int pin, int balance) {
        Account validAccount = new Account(String.valueOf(accountNumber), balance);

        accountService = mock(AccountService.class);
        given(accountService.findBy(String.valueOf(accountNumber), String.valueOf(pin))).willReturn(validAccount);
    }

    @And("I entered {int} as Account number and {int} as PIN")
    public void andEnteringAccountNumberAndPin(int accountNumber, int pin) {
        cliPrompt = mock(CliPrompt.class);
        given(cliPrompt.accountNumber()).willReturn(String.valueOf(accountNumber));
        given(cliPrompt.pin()).willReturn(String.valueOf(pin));

        welcomeScreen = new WelcomeScreen(cliPrompt, accountService);
    }

    @When("I withdraw {int}")
    public void whenIWithdraw(int ignoredWithdraw) {
        TransactionScreen transactionScreen = (TransactionScreen) welcomeScreen.show();
        withdrawScreen = (WithdrawScreen) transactionScreen.show();
    }

    @Then("I should see the message \"Your current balance is {int}\".")
    public void shouldSeeTheMessageWithExpectedBalance(int expectedBalance) {
        withdrawScreen.show();

        verify(cliPrompt).promptGenericMessage(String.format("Your current balance is %d", expectedBalance));
    }
}
