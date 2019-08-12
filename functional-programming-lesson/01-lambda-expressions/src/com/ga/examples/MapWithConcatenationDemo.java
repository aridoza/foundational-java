package com.ga.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapWithConcatenationDemo {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("My name is ", "My friends call me ", "My mother calls me ");

        List<String> concatenatedList =
// todo: I strongly suggest replacing the variable stringValue with something more
//    concise, like value. The practice for lambdas is to keep the variable names concise
//    Even something like s would be acceptable. Not to mention it can be a method reference,
//    but I am not sure if you covered those yet,
            stringList.stream()
                .map(stringValue -> apply(stringValue))
                .collect(Collectors.toList());

        //Now output the values of the concatenatedList using streams.
        concatenatedList.stream().forEach(stringValue -> {
            System.out.println(stringValue);
        });

        //Now output the values of the original stringList using streams to show that the list is unaltered.
        stringList.stream().forEach(stringValue -> {
            System.out.println(stringValue);
        });
    }

    private static String apply(String stringValue) {
        return stringValue + "Jim";
    }
}
