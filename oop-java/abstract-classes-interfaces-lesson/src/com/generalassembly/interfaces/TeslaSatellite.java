package com.generalassembly.oop.interfaces;

public class TeslaSatellite implements Automobile {

    @Override
    public int getYear() {
        return 2020;
    }

    @Override
    public String getMake() {
        return "Tesla";
    }

    @Override
    public String getModel() {
        return "Satellite";
    }

    @Override
    public void startEngine() {
        System.out.println("Silent hum!");
    }
}
