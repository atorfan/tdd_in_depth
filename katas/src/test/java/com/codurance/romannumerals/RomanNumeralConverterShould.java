package com.codurance.romannumerals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralConverterShould {

    @ParameterizedTest
    @CsvSource({
            "1,I", "2,II", "3,III", "4,IV", "5,V",
            "6,VI", "7,VII", "8,VIII", "9,IX", "10,X",
            "11,XI", "12,XII", "13,XIII", "14,XIV", "15,XV",
            "40,XL", "44,XLIV", "50,L", "90,XC", "100,C",
            "400,CD", "500,D", "900,CM", "1000,M",
            "846,DCCCXLVI", "1999,MCMXCIX", "2008,MMVIII"
    })
    void convert_to_roman_number(int arabicNumber, String romanNumber) {
        assertEquals(romanNumber, new RomanNumeralConverter().convert(arabicNumber));
    }
}
