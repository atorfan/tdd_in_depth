/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.codurance.fizzbuzz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzShould {

    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "4,4"})
    void convert_number_to_FizzBuzz_string(int input, String expectedOutput) {
        assertEquals(expectedOutput, new FizzBuzz().convert(input));
    }

    @ParameterizedTest
    @CsvSource({"3", "6", "9"})
    void convert_multiples_of_3_to_Fizz(int input) {
        assertEquals("Fizz", new FizzBuzz().convert(input));
    }
}
