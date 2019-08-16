package com.ga;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class IndependentPracticeCompleted {

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1,3,4,5,6);
        List<Integer> list2 = Arrays.asList(1,3,4,5,6);

        //Write a BiFunction that compares to see if list1 and list2 are equal and returns a boolean.
        BiFunction<List<Integer>, List<Integer>, Boolean> listCompare = (listA, listB) -> listA.equals(listB);
        Boolean result = listCompare.apply(list1, list2);
        System.out.println("Are the 2 lists equals? " + result);

        //Rewrite the previous example to leverage a method reference on the Integer class
        BiFunction<List<Integer>, List<Integer>, Boolean> listCompare2 = List::equals;
        Boolean result2 = listCompare2.apply(list1, list2);
        System.out.println("Are the 2 lists equals? " + result2);

        List<Double> numberList = Arrays.asList(4.0,9.0,16.0,25.0,36.0,49.0,64.0,81.0);

        //Write a Function that will return the square root of a given number.  Then iterate
        //the numberList and print out the results.
        Function<Double, Double> squareRoot = number -> Math.sqrt(number);

        numberList.forEach(number -> System.out.println(squareRoot.apply(number)));
    }
}
