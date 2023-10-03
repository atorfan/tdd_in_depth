package com.codurance.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorShould {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void substractOneNumberToAnother() {
        assertEquals(calculator.subtract(3, 2), 1);
    }

    @ParameterizedTest
    @CsvSource({
            "2,3,6",
            "3,2,6",
            "6,9,54"
    })
    public void multiplyTwoIntegers(int a, int b, int mult) {
        assertEquals(mult, calculator.multiply(a, b));
    }
}
