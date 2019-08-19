package com.ga.examples;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IndependentPractice {

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
            new IndependentPractice.Person("Tom", "Male", 30, 50000),
            new IndependentPractice.Person("John", "Male", 30, 60000),
            new IndependentPractice.Person("Jenny", "Female",  20, 70000),
            new IndependentPractice.Person("Mark", "Male", 35, 30000),
            new IndependentPractice.Person("Chris", "Male", 37, 20000),
            new IndependentPractice.Person("Paige", "Female",  31, 25000),
            new IndependentPractice.Person("Helen", "Female",  60, 100000),
            new IndependentPractice.Person("Erin", "Female",  50, 500000),
            new IndependentPractice.Person("Zach", "Male", 10, 1000),
            new IndependentPractice.Person("Jane", "Female",  45, 200000),
            new IndependentPractice.Person("Jeff", "Male", 70, 80000)
        );

        //TODO: Find the person with the highest salary.

        //TODO: Find the person with the lowest salary.

        //TODO: Create a map where the key is age and the value if the name of the person. Be sure to handle duplicate keys.

        //TODO: Create a map where the key is the gender and the value is a list of Persons.  Filter the results to only
        //include persons over the age of 30 and that have a salary greater than 20,000.
    }
}
