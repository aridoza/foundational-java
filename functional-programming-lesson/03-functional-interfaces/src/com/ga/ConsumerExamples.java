package com.ga;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExamples {

    public static void main(String[] args) {

        Consumer<String> sayHello = (name) -> System.out.println("Hello " + name);
        sayHello.accept("John");

        List<String> names = Arrays.asList("John", "Lisa", "Amanda", "Matt");

        Consumer<String> printNames = (name) -> System.out.println(name);//ConsumerExamples::printNames;

        names.stream().forEach(name -> printNames.accept(name));
    }

    private static void printNames(String name) {
        System.out.println(name);
    }
}
