package com.generalassembly.intro;

import org.junit.Test;

public class OperatorPrecedence {
    @Test
    public void testIntDiv() {
        System.out.println(13 / 3);
        System.out.println(14 / 3);
        System.out.println(99 / 100);

        System.out.println(2 * 25 % 19);
        System.out.println((2 * 25) % 19);
        System.out.println(2 * (25 % 19));
        System.out.println(2 * 3 + 4 * 5);
    }

    @Test
    public void testUpcast() {
        int i = 1;
        long j = i;
        System.out.println(j);

        long L = Long.MAX_VALUE;
        float f = L;
        System.out.println(L + "," + f);

        double d = f;
//        float f1 = d;

        float f1 = (float) Double.MAX_VALUE;
        System.out.println(d + "," + f1);
        System.out.println(-3 * -2);
        int x1 = 66;
        long x2 = 66;
        byte x3 = 66;
        char x4 = 66;
        short x5 = 66;
        System.out.println(x1++); // => 66
        System.out.println(x2++); // => 66
        System.out.println(x3++); // => 66
        System.out.println(x4++); // => B
        System.out.println(x5++); // => 66

        System.out.println(x1); // => 67
        System.out.println(x2); // => 67
        System.out.println(x3); // => 67
        System.out.println(x4); // => C
        System.out.println(x5); // => 67

        System.out.println(++x1); // => 68
        System.out.println(++x2); // => 68
        System.out.println(++x3); // => 68
        System.out.println(++x4); // => D
        System.out.println(++x5); // => 68

    }

    @Test
    public void testDivideByZero() {

        System.out.println(-3. / 0); //Displays -Infinity
        System.out.println(3 / 0); // halts execution: java.lang.ArithmeticException: / by zero
        System.out.println(3. / 0); //Displays Infinity
//        Math.

    }

    @Test
    public void testMath() {
        System.out.println(Math.pow(3, 2));
        System.out.println(Math.pow(2, .5));

        System.out.println(Math.PI);
    }


    @Test
    public void testScientific() {
        double f = 9999999;
        for (int i = 0; i < 10000; i++) {
            System.out.println(i + ":" + f);
            f += 1;
        }

    }

    @Test
    public void testScientificInt() {
        long f = 9999999;
        for (int i = 0; i < 10000; i++) {
            System.out.println(i + ":" + f);
            f *= 10;
        }
    }

    @Test
    public void testScientificNotation() {
        System.out.println(1.3e2);
    }

    @Test
    public void testStringConcat() {
        String name = "Mary";
        int age = 12;
        System.out.println("My name is " + name + " and I am " + age + " years old"); // displays My name is Mary and I
    }

    @Test
    public void testSumMethod() {
        int result = sum(1, 2);

    }

    public int sum(int first, int second) {
        int result = first + second;
        return result;
    }
}


