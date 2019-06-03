package com.generalassembly.oop.interfaces;

public interface Automobile {
    int getYear();
    String getMake();
    String getModel();
    void startEngine();
    int wheels = 4;
    public static void main(String[] args) {
        int wheels = Automobile.wheels;
//        Automobile.wheels = 5; // illegal

    }
}




