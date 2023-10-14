package com.codurance.stringcalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorShould {

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "'';0",
            "0;0",
            "1;1",
            "1,3,;Number expected but EOF found",
            "2;2",
            "3;3",
            "1.1,2.2;3.3",
            "1.1,2.2,3.3,6.3,12.9,7;32.8",
            "1.1,2.2,3.3,6.3,12.9,7,;Number expected but EOF found",
    })
    @MethodSource("provideStringsForNewLine")
    void return_sum_of_numbers(String actual, String expected) {
        assertEquals(expected, new StringCalculator().add(actual));
    }

    public static Stream<Arguments> provideStringsForNewLine() {
        return Stream.of(
                Arguments.of("1\n2,3", "6"),
                Arguments.of("175.2,\n35", "Number expected but '\\n' found at position 6."),
                Arguments.of("13.2,\n2", "Number expected but '\\n' found at position 5."),
                Arguments.of("//;\n1;2", "3"),
                Arguments.of("//|\n1|2|3", "6"),
                Arguments.of("//sep\n2sep3", "5"),
                Arguments.of("//|\n1|2,3", "'|' expected but ',' found at position 3.")
        );
    }
}
