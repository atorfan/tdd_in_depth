package com.codurance.atm.screens;

import com.codurance.atm.account.AccountService;
import com.codurance.atm.infrastructure.CliPrompt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class WelcomeScreenTest {

    CliPrompt cliPrompt;
    WelcomeScreen welcomeScreen;

    @BeforeEach
    void setUp() {
        cliPrompt = mock(CliPrompt.class);
        welcomeScreen = new WelcomeScreen(cliPrompt, new AccountService());
    }

    @Test
    void shouldAskForAccountNumberAndPin() {
        when(cliPrompt.accountNumber()).thenReturn("123456");
        when(cliPrompt.pin()).thenReturn("123456");

        Screen nextScreen = welcomeScreen.show();

        verify(cliPrompt).accountNumber();
        verify(cliPrompt).pin();

        assertTrue(nextScreen instanceof TransactionScreen);
    }

    @Test
    void shouldAskAgainForAccountNumberWhenNotOnlyDigitsProvided() {
        when(cliPrompt.accountNumber()).thenReturn("a23456").thenReturn("123456");
        when(cliPrompt.pin()).thenReturn("123456");

        welcomeScreen.show();

        verify(cliPrompt, times(2)).accountNumber();
        verify(cliPrompt, times(1)).promptGenericMessage("Account Number should only contain numbers\n");
    }

    @Test
    void shouldAskAgainForAccountNumberWhenMoreThan6DigitsProvided() {
        when(cliPrompt.accountNumber()).thenReturn("0123456").thenReturn("123456");
        when(cliPrompt.pin()).thenReturn("123456");

        welcomeScreen.show();

        verify(cliPrompt, times(2)).accountNumber();
        verify(cliPrompt, times(1)).promptGenericMessage("Account Number should have 6 digits length\n");
    }

    @Test
    void shouldAskAgainForAccountNumberWhenLessThan6DigitsProvided() {
        when(cliPrompt.accountNumber()).thenReturn("12345").thenReturn("123456");
        when(cliPrompt.pin()).thenReturn("123456");

        welcomeScreen.show();

        verify(cliPrompt, times(2)).accountNumber();
        verify(cliPrompt, times(1)).promptGenericMessage("Account Number should have 6 digits length\n");
    }

    @Test
    void shouldAskAgainForPinWhenNotOnlyDigitsProvided() {
        when(cliPrompt.accountNumber()).thenReturn("123456");
        when(cliPrompt.pin()).thenReturn("a23456").thenReturn("123456");

        welcomeScreen.show();

        verify(cliPrompt, times(2)).pin();
        verify(cliPrompt, times(1)).promptGenericMessage("PIN should only contain numbers\n");
    }

    @Test
    void shouldAskAgainForPinWhenMoreThan6DigitsProvided() {
        when(cliPrompt.accountNumber()).thenReturn("123456");
        when(cliPrompt.pin()).thenReturn("0123456").thenReturn("123456");

        welcomeScreen.show();

        verify(cliPrompt, times(2)).pin();
        verify(cliPrompt, times(1)).promptGenericMessage("PIN should have 6 digits length\n");
    }

    @Test
    void shouldAskAgainForPinWhenLessThan6DigitsProvided() {
        when(cliPrompt.accountNumber()).thenReturn("123456");
        when(cliPrompt.pin()).thenReturn("12345").thenReturn("123456");

        welcomeScreen.show();

        verify(cliPrompt, times(2)).pin();
        verify(cliPrompt, times(1)).promptGenericMessage("PIN should have 6 digits length\n");
    }
}
