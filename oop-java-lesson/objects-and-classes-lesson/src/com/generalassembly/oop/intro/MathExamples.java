package com.generalassembly.oop.intro;

import org.junit.Test;

public class MathExamples {
    @Test
    public void test() {
        int a = 4;
        int b = 2;
        System.out.println(a + "**" + b + "=" + (Math.pow(a, b))); // displays 4**2=16.0
        System.out.println("max(a" + "," + b + ")=" + (Math.max(a, b))); // displays max(a,2)=4
        System.out.println("min(a" + "," + b + ")=" + (Math.min(a, b))); // displays min(a,2)=2

        System.out.println("11/4=" +  11/4);
        System.out.println("11./4.=" +  11./4.); // displays
        System.out.println("11./4=" +  11./4); // displays 2.75

    }


}
