package com.codurance.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorShould {

    @Test
    void substractOneNumberToAnother() {
        Calculator calculator = new Calculator();

        int result = calculator.substract(3, 2);

        assertEquals(result, 1);
    }
}
