package com.ga.examples;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorsDemo {

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            Person person = (Person) o;
            return age == person.age &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

    public static void main(String[] args) {

        List<CollectorsDemo.Person> largePersonList =
                Arrays.asList(
                        new CollectorsDemo.Person("Tom", 30),
                        new CollectorsDemo.Person("John", 29),
                        new CollectorsDemo.Person("Jenny", 20),
                        new CollectorsDemo.Person("Mark", 35),
                        new CollectorsDemo.Person("Chris", 37),
                        new CollectorsDemo.Person("Paige", 31),
                        new CollectorsDemo.Person("Helen", 60),
                        new CollectorsDemo.Person("Erin", 50),
                        new CollectorsDemo.Person("Zach", 10),
                        new CollectorsDemo.Person("Jane", 45),
                        new CollectorsDemo.Person("Jeff", 70));

        List<CollectorsDemo.Person> duplicatePersonList =
                Arrays.asList(
                        new CollectorsDemo.Person("Tom", 30),
                        new CollectorsDemo.Person("Tom", 30),
                        new CollectorsDemo.Person("Jenny", 20));

        //toSet
        Set<Person> personSet =
            duplicatePersonList.stream()
                .collect(Collectors.toSet());

        personSet.stream().forEach(person  -> System.out.println(person));

        //toCollection
        Vector<Person> personVector =
            largePersonList.stream()
                .filter(person -> person.getAge() > 50)
                .collect(Collectors.toCollection(Vector::new));

        personVector.stream().forEach(person  -> System.out.println(person));

        //toMap
        Map<String, Integer> nameToAgeMap =
            largePersonList.stream()
                .collect(Collectors.toMap(person -> person.getName(), person -> person.getAge()));

        nameToAgeMap.forEach((key, value) -> System.out.println("Name is " + key + " and age is " + value));

        //Duplicate key exception.
        Map<String, Integer> nameToAgeDuplicateMap =
                duplicatePersonList.stream()
                        .collect(Collectors
                                .toMap(person -> person.getName(),
                                        person -> person.getAge(),
                                        (first, second) -> second));

        nameToAgeDuplicateMap.forEach((key, value) -> System.out.println("Name is " + key + " and age is " + value));

        //joining
        String names =
            largePersonList.stream()
                .map(person -> person.getName())
                .collect(Collectors.joining(", ", "", ""));

        System.out.println(names);

        //partitionBy
        Map<Boolean, List<Person>> youngerAndOlderThanFortyMap =
            largePersonList.stream()
                .collect(Collectors.partitioningBy(person -> person.getAge() >= 40));

        //Let's iterate the person's older than 40.
        System.out.println("Persons older than 40");
        youngerAndOlderThanFortyMap.get(true).forEach(person -> System.out.println(person));

        System.out.println("Persons younger than 40");
        youngerAndOlderThanFortyMap.get(false).forEach(person -> System.out.println(person));

        //groupingBy
        Map<Character, List<Person>> firstInitialMap =
            largePersonList.stream()
                .collect(Collectors.groupingBy(person -> new Character(person.getName().charAt(0))));

        //Check the number of elements in the map. It should be 8.
        System.out.println("Number of distinct first initials in the map is " + firstInitialMap.size());

        System.out.println("Persons with initial T");
        firstInitialMap.get('T').forEach(person -> System.out.println(person));

        System.out.println("Persons with initial J");
        firstInitialMap.get('J').forEach(person -> System.out.println(person));

        System.out.println("Persons with initial M");
        firstInitialMap.get('M').forEach(person -> System.out.println(person));

        System.out.println("Persons with initial C");
        firstInitialMap.get('C').forEach(person -> System.out.println(person));

        System.out.println("Persons with initial P");
        firstInitialMap.get('P').forEach(person -> System.out.println(person));

        System.out.println("Persons with initial H");
        firstInitialMap.get('H').forEach(person -> System.out.println(person));

        System.out.println("Persons with initial E");
        firstInitialMap.get('E').forEach(person -> System.out.println(person));

        System.out.println("Persons with initial C");
        firstInitialMap.get('C').forEach(person -> System.out.println(person));

        //mapping
        Map<String, List<Integer>> anotherNameToAgeMap =
            largePersonList.stream()
                .collect(
                        Collectors.groupingBy(person -> new String(person.getName()),
                            Collectors.mapping(Person::getAge, Collectors.toList())
                ));

        anotherNameToAgeMap.forEach((key, value) -> System.out.println("Name is " + key + " and age is " + value.get(0)));
    }
}
