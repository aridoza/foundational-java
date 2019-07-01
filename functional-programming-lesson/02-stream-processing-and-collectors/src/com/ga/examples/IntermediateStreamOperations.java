package com.ga.examples;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class IntermediateStreamOperations {

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

        //Different intermediate stream operations.

        //Filter - Get numbers greater than 50
        List<Integer> numberList = Arrays.asList(1,2,50,100);
        List<Integer> filteredNumberList =
                numberList.stream()
                .filter(number -> number > 50)
                .collect(Collectors.toList());
        filteredNumberList.stream().forEach(number -> System.out.println(number));
        //Outputs:
        //100

        //Map - Take the Person list and create a list of their ages.
        List<Person> personList =
                Arrays.asList(
                        new Person("Tom", 30),
                        new Person("Jane", 45),
                        new Person("Jeff", 70));

        List<Integer> ageList =
                personList.stream()
                .map(person -> person.getAge())
                .collect(Collectors.toList());
        ageList.stream().forEach(age -> System.out.println(age));
        //Output is:
        //30
        //45
        //70

        //FlatMap - flatten the list of person lists
        List<Person> personList1 =
                Arrays.asList(
                        new Person("Tom", 30),
                        new Person("Jeff", 70));

        List<Person> personList2 =
                Arrays.asList(
                        new Person("Jane", 45));

        List<List<Person>> listOfPersonLists = Arrays.asList(personList1, personList2);

        List<Person> flatPersonList =
                listOfPersonLists.stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        flatPersonList.stream().forEach(person -> System.out.println(person));
        //Output is
        //Person{name='Tom', age=30}
        //Person{name='Jeff', age=70}
        //Person{name='Jane', age=45}

        //Sorted - Sort the person list by name alphabetically
        List<Person> sortedList =
                personList.stream()
                .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
                .collect(Collectors.toList());
        sortedList.stream().forEach(person -> System.out.println(person));
        //Output is
        //Person{name='Jane', age=45}
        //Person{name='Jeff', age=70}
        //Person{name='Tom', age=30}

        //No stream processing is done because there's isn't a terminal stream operation present.
        personList.stream()
            .filter(person -> {
                System.out.println("Person is: " + person);
                return person.getAge() > 30;
            });
        //No processing happens because intermediate stream operations only returns a stream

        //Let's add a terminal operation.
        personList.stream()
                .filter(person -> {
                    System.out.println("Person is: " + person);
                    return person.getAge() > 30;
                })
                .collect(Collectors.toList());

        //Notice the unnecessary processing that occurs simply because we didn't order the stream operations correctly.
        List<Person> largePersonList =
                Arrays.asList(
                        new Person("Tom", 30),
                        new Person("John", 29),
                        new Person("Jenny", 20),
                        new Person("Mark", 35),
                        new Person("Chris", 37),
                        new Person("Paige", 31),
                        new Person("Helen", 60),
                        new Person("Erin", 50),
                        new Person("Zach", 10),
                        new Person("Jane", 45),
                        new Person("Jeff", 70));

        List<Person> sortedAndFilteredList =
            largePersonList.stream()
                .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
                .filter(person -> person.getAge() > 31)
                .filter(person -> person.getName().startsWith("J"))
                .collect(Collectors.toList());

        sortedAndFilteredList.stream().forEach(person -> System.out.println(person));
        //Output is
        //Person{name='Jane', age=45}
        //Person{name='Jeff', age=70}

        //Here's a better way to do the previous example
        List<Person> filteredAndSortedList =
                largePersonList.stream()
                        .filter(person -> person.getName().startsWith("J"))
                        .filter(person -> person.getAge() > 31)
                        .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
                        .collect(Collectors.toList());

        filteredAndSortedList.stream().forEach(person -> System.out.println(person));
        //Output is
        //Person{name='Jane', age=45}
        //Person{name='Jeff', age=70}
    }
}
