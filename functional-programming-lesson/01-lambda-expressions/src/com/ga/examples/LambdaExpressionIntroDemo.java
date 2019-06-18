package com.ga.examples;

public class LambdaExpressionIntroDemo {

    interface Computation {
        int operation(int a, int b);
    }

    public static void main(String[] args) {

        Computation add = (int a, int b) -> { return a + b; };

        System.out.println("5 + 6 = " + add.operation(5,6));

        Computation subtract = (int a, int b) -> { return a - b; };

        System.out.println("10 - 6 = " + subtract.operation(10,6));
    }
}
