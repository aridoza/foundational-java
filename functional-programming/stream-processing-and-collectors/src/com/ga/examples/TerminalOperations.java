package com.ga.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TerminalOperations {

    public static class Person {

        private String name;
        private int age;

        public Person(String name,
                      int age) {
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

        List<TerminalOperations.Person> largePersonList =
                Arrays.asList(
                        new TerminalOperations.Person("Tom", 30),
                        new TerminalOperations.Person("John", 29),
                        new TerminalOperations.Person("Jenny", 20),
                        new TerminalOperations.Person("Mark", 35),
                        new TerminalOperations.Person("Chris", 37),
                        new TerminalOperations.Person("Paige", 31),
                        new TerminalOperations.Person("Helen", 60),
                        new TerminalOperations.Person("Erin", 50),
                        new TerminalOperations.Person("Zach", 10),
                        new TerminalOperations.Person("Jane", 45),
                        new TerminalOperations.Person("Jeff", 70));

        //forEach - print out each person's name
        largePersonList.stream()
            .forEach(person -> System.out.println("Hello my name is " + person.getName()));

        //findFirst - find first person whose over the age of 35.
        Person personOverThirtyFive =
            largePersonList.stream()
                .filter(person -> person.getAge() > 35)
                .findFirst()
                .orElse(null);

        System.out.println(personOverThirtyFive);

        //collect - find persons whose names start with J
        largePersonList.stream()
            .filter(person -> person.getName().startsWith("J"))
            .collect(Collectors.toList())
        .forEach(person -> System.out.println(person));


        //toArray - convert persons list to Array.
        Person[] personArray =
            largePersonList.stream()
                .toArray(Person[]::new);

        System.out.println(personArray[0]);
        System.out.println(personArray[1]);
        System.out.println(personArray[2]);
        System.out.println(personArray[3]);
        System.out.println(personArray[4]);
        System.out.println(personArray[5]);
        System.out.println(personArray[6]);
        System.out.println(personArray[7]);
        System.out.println(personArray[8]);
        System.out.println(personArray[9]);
        System.out.println(personArray[10]);
    }
}
