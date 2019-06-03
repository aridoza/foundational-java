package com.generalassembly.oop;

public class Rectangle extends Shape{
    private int length;
    private int height;

    public Rectangle(int length, int height) {
        this.length = length;
        this.height = height;
    }

    @Override
    public double getCircumference() {
        return 2*length + 2*height;
    }

    @Override
    public double getArea() {
        return length *height;
    }
}
