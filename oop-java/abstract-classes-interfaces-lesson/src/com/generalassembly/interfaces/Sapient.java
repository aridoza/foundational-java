package com.generalassembly.oop.interfaces;

public interface Sapient {
    void think();
    default void speak() {
        System.out.println("I think therefore I am!");
    }
}
