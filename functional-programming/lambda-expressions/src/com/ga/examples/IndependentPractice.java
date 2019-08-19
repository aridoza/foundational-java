package com.ga.examples;

import java.util.Arrays;
import java.util.List;

public class IndependentPractice {

    static class Person {

        private String name;
        private int age;

        public Person(String name, int age) {

            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {

        List<Person> personList = createPersonList();

        //TODO: Create a List of Person Objects whose name starts with the letter M

        //TODO: Create a List of Strings containing the names of the Persons over the age of 40

        //TODO: Create a List of Person Objects whose name starts with the letter J and are under the age of 47
        
    }

    private static List<Person> createPersonList() {

        return Arrays.asList(
            new Person("Mark", 45),
            new Person("Henry", 30),
            new Person("John", 18),
            new Person("Morgan", 6),
            new Person("Amanda", 23),
            new Person("Tiffany", 60),
            new Person("Jim", 50),
            new Person("Janet", 45)
        );
    }
}
