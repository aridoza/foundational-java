package com.generalassembly.oop.interfaces;

public class Person implements Sentient, Sapient, Biped {
    @Override
    public void walk() {
        System.out.println("I am walking!");
    }

    @Override
    public void think() {
        System.out.println("I am thinking!");
    }

    @Override
    public void feel() {
        System.out.println("I have a feeling!");
    }

    public static void main(String[] args) {
        Person person = new Person();
        Sentient sentient = person;
        Sapient sapient = person;
        Biped biped = person;
        person.feel();
    }
}
