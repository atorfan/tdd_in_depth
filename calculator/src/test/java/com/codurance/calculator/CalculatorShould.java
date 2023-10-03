package com.codurance.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorShould {

    @Test
    void substractOneNumberToAnother() {
        Calculator calculator = new Calculator();

        int result = calculator.subtract(3, 2);

        assertEquals(result, 1);
    }
}
