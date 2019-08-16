package com.ga.examples;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreatingStreams {

    public static void main(String[] args) {

        //Create a Stream from a List
        List<String> stringList = Arrays.asList("hello", "world");
        Stream stringStream = stringList.stream();
        stringStream.forEach(value -> System.out.println(value));

        //Create a Stream from an array
        Integer[] intArray = new Integer[]{1,2,3,4};
        Stream intArrayStream = Stream.of(intArray);
        intArrayStream.forEach(value -> System.out.println(value));

        //Create a Stream from individual objects
        Stream objectStream = Stream.of(100,200,300);
        objectStream.forEach(value -> System.out.println(value));

        //Create a Stream using the Stream Builder
        Stream.Builder<String> stringStreamBuilder = Stream.builder();

        stringStreamBuilder.accept("I really love ");
        stringStreamBuilder.accept("streams and ");
        stringStreamBuilder.accept("lambda expressions!!! ");

        Stream stringBuilderStream = stringStreamBuilder.build();
        stringBuilderStream.forEach(value -> System.out.println(value));
    }
}
