package com.generalassembly.oop;

public class Cat {
    protected int lives = 9;
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public Cat() {
    }

    int getLives() {
        return lives;
    }

    public static void main(String[] args) {
        Cat cat = new Cat("Garfield");
        System.out.println(cat);
    }

    @Override
    public String toString() {
        return "Cat{name='" + name + '\'' + '}';
    }
}

