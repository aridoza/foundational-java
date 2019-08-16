package com.ga;

import java.util.function.BiFunction;

public class BiFunctionExamples {

    public static void main(String[] args) {

        //Create an add function using a BiFunction
        BiFunction<Integer, Integer, Integer> addFunction = (a, b) -> a + b;
        Integer addResult = addFunction.apply(4,5);
        System.out.println(addResult);

        //Create a multiply function using a BiFunction
        BiFunction<Integer, Integer, Integer> multiplyFunction = (a, b) -> a * b;
        Integer multiplyResult = multiplyFunction.apply(4,5);
        System.out.println(multiplyResult);
    }
}
