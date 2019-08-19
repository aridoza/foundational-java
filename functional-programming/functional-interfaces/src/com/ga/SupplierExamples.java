package com.ga;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SupplierExamples {

    public static void main(String[] args) {

        //Let's see an example without the supplier when calling an expensive operation.
        String doTheyMatch =
                eagerMatch(expensiveComputeOperation("bb"), expensiveComputeOperation("aa"));
        System.out.println(doTheyMatch);

        //Let's use a supplier.
        doTheyMatch = lazyMatch(() -> expensiveComputeOperation("bb"), () -> expensiveComputeOperation("aa"));
        System.out.println(doTheyMatch);
    }

    private static String eagerMatch(boolean b1, boolean b2) {
        return b1 && b2 ? "match" : "incompatible!";
    }

    private static boolean expensiveComputeOperation(String input) {
        System.out.println("executing expensive computation...");
        // expensive computation here
        return input.contains("a");
    }

    private static String lazyMatch(Supplier<Boolean> a, Supplier<Boolean> b) {
        return a.get() && b.get() ? "match" : "incompatible!";
    }
}
