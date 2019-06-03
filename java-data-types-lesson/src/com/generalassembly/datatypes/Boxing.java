package com.generalassembly.datatypes;

public class Boxing {
    public static void main(String[] args) {
        Integer myAge = null;       // sets myAge to null. This is fine since myAge is an Object not a primitive
        int mySistersAge = 23;      // sets mySistersAge to 23. This is a primitive.
        myAge = mySistersAge + 3;   // unboxes mySistersAge to the primitive 23, then adds 3 then re-boxes the result and assigns it to myAge
        Integer myBrothersAge = 30;
        int ageDelta = myBrothersAge - myAge;    // unboxes the two wrappers, performs the subtraction, and assigns it to the primitive

    }
}
