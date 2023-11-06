package com.codurance.atm.infrastructure;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Scanner;

public class CliPrompt {
    private static final String HOME_SCREEN_MENU =
            "Account number {0}, balance {1}\n" +
            "1. Withdraw\n" +
            "2. Deposit\n" +
            "3. Fund Transfer\n" +
            "4. Exit\n" +
            "Please choose option [4]: ";;
    private final Scanner scanner;
    private final Printer printer;

    public CliPrompt(InputStream is, Printer printer) {
        this.scanner = new Scanner(is);
        this.printer = printer;
    }

    public String accountNumber() {
        printer.prompt("Enter Account Number: ");
        return scanner.next();
    }

    public String pin() {
        printer.prompt("Enter PIN: ");
        return scanner.next();
    }

    public Integer transactionScreenMenu(String accountNumber, String balance) {
        printer.prompt(MessageFormat.format(HOME_SCREEN_MENU, accountNumber, balance));
        return Integer.parseInt(scanner.next());
    }

    public Integer withdrawQuantity() {
        printer.prompt("How much money do you want to withdraw? ");
        return Integer.parseInt(scanner.next());
    }

    public Integer depositQuantity() {
        printer.prompt("How much money do you want to deposit? ");
        return Integer.parseInt(scanner.next());
    }

    public void promptGenericMessage(String anyMessage) {
        printer.prompt(anyMessage);
    }
}
