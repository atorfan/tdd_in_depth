package com.codurance.stringcalculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StringCalculator {

    public String add(String numbers) {
        if (numbers.isEmpty()) return "0";

        var delimiter = ",";
        if (numbers.startsWith("//")) {
            String[] separatorAndNumbers = numbers.split("\n");
            String separator = separatorAndNumbers[0];
            delimiter = separator.substring(2);
            numbers = separatorAndNumbers[1];
        }
        if (numbers.endsWith(delimiter)) return "Number expected but EOF found";
        if (numbers.contains(",\n")) {
            return String.format(
                    "Number expected but '\\n' found at position %s.", numbers.indexOf(",\n") + 1
            );
        }

        delimiter = delimiter.equals("sep") ? delimiter : "\\" + delimiter;

        List<String> listOfNumbers = Arrays.stream(numbers.split(delimiter))
                .flatMap(s -> Stream.of(s.split("\n")))
                .toList();
        for (String number : listOfNumbers) {
            if (!number.matches("(0|[1-9]\\d*)(\\.\\d+)?")) {
                return "'" + delimiter + "' expected but ',' found at position 3.";
            }
        }

        return listOfNumbers
                .stream()
                .map(BigDecimal::new)
                .reduce(BigDecimal::add)
                .map(BigDecimal::toString)
                .orElse("0");
    }
}
