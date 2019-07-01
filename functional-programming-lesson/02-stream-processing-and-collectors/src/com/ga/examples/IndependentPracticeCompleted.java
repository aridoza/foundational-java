package com.ga.examples;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class IndependentPracticeCompleted {

    public static class Person {

        private String name;
        private String gender;
        private int age;
        private int salary;

        public Person(String name, String gender, int age, int salary) {
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            Person person = (Person) o;
            return age == person.age &&
                    salary == person.salary &&
                    Objects.equals(name, person.name) &&
                    Objects.equals(gender, person.gender);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, gender, age, salary);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender='" + gender + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    '}';
        }
    }

    public static void main(String[] args) {

        List<Person> largePersonList = Arrays.asList(
            new IndependentPracticeCompleted.Person("Tom", "Male", 30, 50000),
            new IndependentPracticeCompleted.Person("John", "Male", 30, 60000),
            new IndependentPracticeCompleted.Person("Jenny", "Female",  20, 70000),
            new IndependentPracticeCompleted.Person("Mark", "Male", 35, 30000),
            new IndependentPracticeCompleted.Person("Chris", "Male", 37, 20000),
            new IndependentPracticeCompleted.Person("Paige", "Female",  31, 25000),
            new IndependentPracticeCompleted.Person("Helen", "Female",  60, 100000),
            new IndependentPracticeCompleted.Person("Erin", "Female",  50, 500000),
            new IndependentPracticeCompleted.Person("Zach", "Male", 10, 1000),
            new IndependentPracticeCompleted.Person("Jane", "Female",  45, 200000),
            new IndependentPracticeCompleted.Person("Jeff", "Male", 70, 80000)
        );

        //TODO: Find the person with the highest salary.
        Person highestSalaryPerson = largePersonList.stream()
                .max(Comparator.comparing(Person::getSalary))
                .orElse(null);

        System.out.println(highestSalaryPerson);

        //TODO: Find the person with the lowest salary.
        Person lowestSalaryPerson = largePersonList.stream()
                .min(Comparator.comparing(Person::getSalary))
                .orElse(null);

        System.out.println(lowestSalaryPerson);

        //TODO: Create a map where the key is age and the value if the name of the person. For any duplicate keys, use
        // the first entry and ignore any duplicates.
        Map<Integer, String> ageToNameMap =
            largePersonList.stream()
                .collect(
                    Collectors.toMap(
                            person -> person.getAge(),
                            person -> person.getName(),
                            (first, second) -> first));

        ageToNameMap.forEach((key, value) -> System.out.println("Age is " + key + " and the name is " + value));

        //TODO: Create a map where the key is the gender and the value is a list of Persons.  Filter the results to only
        //include persons over the age of 30 and that have a salary greater than 20,000.

        Map<String, List<Person>> genderMap =
            largePersonList.stream()
                .filter(person -> person.getAge() > 30 && person.getSalary() > 20_000)
                .collect(Collectors.groupingBy(person -> person.getGender()));

        genderMap.forEach((key, value) -> {
            System.out.println("For gender " + key + " we have the following persons: ");
            value.stream().forEach(person -> System.out.println(person));
        });
    }
}
