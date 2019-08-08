---
title: OOP and Ninety-Nine Problems in Java 8
type: Homework
duration: "2:00"
creator:
    name: Syed Salahuddin
    city: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) OOP and Ninety-Nine Problems in Java 8

## Exercise

### Requirements

- Complete the Object Oriented programming challenge
- Complete Curated Ninety-Nine Problems in Java 8 Set

### Deliverable

### Object Oriented Programming Challenge

Design a class named Person and its two subclasses named Student and Employee. Make Faculty and Staff subclasses of Employee. A person has a name, address, phone number, and email address. A student has a class status (freshman, sophomore, junior, or senior). Define the status as a constant. An employee has an office, salary, and date hired. Create a Date class defined to create an object for date hired. A faculty member has office hours and a rank. A staff member has a title. Override the toString method in each class to display the class name and the person's name.
Write a test program that creates a Person, Student, Employee, Faculty, and Staff, and invokes their toString() methods.


### Ninety-Nine Problems in Java 8 ( Intro )

This HW is curated list from this repo: https://github.com/shekhargulati/99-problems/blob/master/java8/README.md

This is an adaptation of the [Ninety-Nine Prolog Problems](https://sites.google.com/site/prologsite/prolog-problems) written by Werner Hett at the Berne University of Applied Sciences in Berne, Switzerland.

From the original source:

> The purpose of this problem collection is to give you the opportunity to practice your skills in logic programming. Your goal should be to find the most elegant solution of the given problems. Efficiency is important, but logical clarity is even more crucial. Some of the (easy) problems can be trivially solved using built-in predicates. However, in these cases, you learn more if you try to find your own solution.

> The problems have different levels of difficulty. Those marked with a single asterisk (\*) are easy. If you have successfully solved the preceding problems you should be able to solve them within a few (say 15) minutes. Problems marked with two asterisks (\*\*) are of intermediate difficulty. If you are a skilled Java programmer it shouldn't take you more than 30-90 minutes to solve them. Problems marked with three asterisks (\*\*\*) are more difficult. You may need more time (i.e. a few hours or more) to find a good solution.


In Java, lists are instances of `List<T>` sub-types. You could use `ArrayList<T>` or `LinkedList<T>`. `LinkedList` are better suited for writing functional programs because they provide you methods to get the first and last elements of a List. One of the method that you will miss when working with Java LinkedList is `tail`. `tail` gives you everything except the first element. You could easily implement `tail` as shown below.

```java
public static <T> List<T> tail(LinkedList<T> elements) {
    if (elements == null || elements.isEmpty()) {
        throw new NoSuchElementException();
    }
    if (elements.size() == 1) {
        return Collections.emptyList();
    }
    return elements.subList(1, elements.size());
}
```
### Problem Set

**Pack consecutive duplicates of list elements into sublists**
- https://github.com/shekhargulati/99-problems/tree/master/java8#p09--pack-consecutive-duplicates-of-list-elements-into-sublists

**Flatten a nested list structure**

- https://github.com/shekhargulati/99-problems/tree/master/java8#p07--flatten-a-nested-list-structure

**Duplicate the elements of a list a given number of times**

- https://github.com/shekhargulati/99-problems/tree/master/java8#p15---duplicate-the-elements-of-a-list-a-given-number-of-times


### BONUS

Complete any additional ** (two asterisk) problem.


### Submission

And a link to your repo with all the answers as an issue to this repo.
