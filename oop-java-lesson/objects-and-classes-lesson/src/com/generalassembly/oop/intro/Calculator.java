package com.generalassembly.oop.intro;

public class Calculator {
    public double sum(double first, double second) {
        return first + second;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.sum(10 , 20));
        System.out.println(calculator.sum(3.14 , 2.718));
        System.out.println(calculator.sum(1.414,3.14));

    }
}
