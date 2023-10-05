package com.codurance.romannumerals;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeralConverter {

    public String convert(int arabicNumber) {
        var romanNumbers = new LinkedHashMap<Integer, String>();
        romanNumbers.put(1000, "M");
        romanNumbers.put(900, "CM");
        romanNumbers.put(500, "D");
        romanNumbers.put(400, "CD");
        romanNumbers.put(100, "C");
        romanNumbers.put(90, "XC");
        romanNumbers.put(50, "L");
        romanNumbers.put(40, "XL");
        romanNumbers.put(10, "X");
        romanNumbers.put(9, "IX");
        romanNumbers.put(5, "V");
        romanNumbers.put(4, "IV");
        romanNumbers.put(1, "I");

        String result = "";
        for (Map.Entry<Integer, String> entry : romanNumbers.entrySet()) {
            while (arabicNumber >= entry.getKey()) {
                result += entry.getValue();
                arabicNumber -= entry.getKey();
            }
        }
        return result;
    }
}
