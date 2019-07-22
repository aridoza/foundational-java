package com.ga;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleFunction;
import java.util.function.Function;

public class FunctionExamples {

    public static void main(String[] args) {

        //Create a greeting function
        Function<String, String> greetingFunction = s -> "Good Morning " + s + "!";
        String greeting = greetingFunction.apply("Kyle");
        System.out.println(greeting);

        //Create a square function
        Function<Integer, Integer> squareFunction = s -> s * s;
        Integer squareOfNine = squareFunction.apply(9);
        System.out.println(squareOfNine);
    }
}
