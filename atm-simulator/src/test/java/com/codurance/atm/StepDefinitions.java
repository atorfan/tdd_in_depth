package com.codurance.atm;

import com.codurance.atm.account.Account;
import com.codurance.atm.account.AccountBalance;
import com.codurance.atm.account.AccountNumber;
import com.codurance.atm.account.AccountService;
import com.codurance.atm.infrastructure.CliPrompt;
import com.codurance.atm.screens.WelcomeScreen;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StepDefinitions {

    private AccountService accountService;
    private CliPrompt cliPrompt;
    private AtmSimulator atmSimulator;

    @Before
    public void beforeScenario() {
        accountService = mock(AccountService.class);
        cliPrompt = mock(CliPrompt.class);
        atmSimulator = new AtmSimulator(new WelcomeScreen(cliPrompt, accountService));
    }

    @Given("there's an Account with number {int}, PIN {int} and balance {int}")
    public void givenExistingAccountNumberAndPinWithBalance(int accountNumber, int pin, int balance) {
        Account validAccount = new Account(new AccountNumber(accountNumber), new AccountBalance(balance));
        given(accountService.findBy(new AccountNumber(accountNumber), toString(pin))).willReturn(validAccount);
    }

    @And("I entered {int} as Account number and {int} as PIN")
    public void andEnteringAccountNumberAndPin(int accountNumber, int pin) {
        given(cliPrompt.accountNumber()).willReturn(toString(accountNumber));
        given(cliPrompt.pin()).willReturn(toString(pin));
    }

    @When("I withdraw {int}")
    public void whenIWithdraw(int ignoredWithdraw) {
        atmSimulator.showScreen();
    }

    @Then("I should see the message \"Your current balance is {int}\".")
    public void shouldSeeTheMessageWithExpectedBalance(int expectedBalance) {
        verify(cliPrompt).promptGenericMessage(String.format("Your current balance is %d", expectedBalance));
    }

    private String toString(int intValue) {
        return String.valueOf(intValue);
    }
}
