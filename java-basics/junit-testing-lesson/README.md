---
title: JUnit Testing
type: Lesson
duration: "1:15"
creator: James Davis (NYC)

---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) JUnit Testing

### Learning Objectives

At the end of this lesson, you'll be able to:
* Add conditional methods to a test case.
* Perform a JUnit test.

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 10 min  | Opening  | Man vs. Machine |
| 5 min   | Introduction  | Unit Tests |
| 20 min  | Demo  | Creating a Unit Test |
| 40 min  | Independent Practice  | Automated Testing |
| 5 min   | Conclusion  | Review and Recap |

## Opening: Man vs. Machine (10 min)

There are many ways to test something. You can do it by hand, which a lot of QA teams do. However, this could get tedious because you usually have to test the full feature even for small changes.

For instance, imagine you're a home inspector checking out a newly built home. As the inspector, you'll have to test if a bedroom is indeed a bedroom. The room must have at least a bed, a dresser, and a closet.

Let's say an interior designer adds a lamp to the dresser. You then have to go back to the room and check if the dresser, bed, and closet are still there.

Now let's say the designer added a rug. You'll have to check again if the bed, dresser, and closet are still there.

Every time something changes, you have to ensure what you're checking does what it's required to do. This can be repetitive, and in some cases, a waste of time.

> Check: Take one minute to discuss with the person next to you how to prevent this. Be prepared to share your answers with the class.

There are a few ways to tackle this problem. We'll talk about one today called **automation**.

Think about the previous example. Now imagine you built a robot to inspect the house for you. So every time the designer changes something in the house, the robot would inspect the room for you. And while that's happening, you're on vacation in some remote  tropical island.

This is the difference between manual and automated testing. You code tests to religiously check your code for defects, while you and your QA team have more time to do other tasks.

----

## Introduction: Unit Tests (5 min)

There are two types of automated tests: unit tests and UI tests.

> Check: What's the difference between these two types of automated tests?

Unit tests check if the code's logic is correct, while UI tests check if the elements on screen work as expected.

This lesson will focus on unit tests.

### What Is a Unit Test?

A unit test is one that tests a piece of code (a unit). In Java, a unit in most cases is a class. A unit test asks the question, "Is this class and its methods working as expected?"

----

## Demo: Creating a Unit Test (20 min)

To perform unit testing, we create test classes that test other classes (units).

Create a new project in IntelliJ. In the **`src`** folder, you'll find two folders:

* **`main`**: the location of your app's main code (classes, activities, resources, and more).
* **`test`**: the location of tests, where we'll add our test classes.

We'll build a simple app that returns a student's name and grade.

First, let's create a `Student` class, which has the methods `getFullName` and `getLetterGrade`. The first constructor takes a first name and last name, while the second one takes a letter grade.

Then, we'll create a new test in the `src/test` package and call it `StudentTest`.

The idea of testing is to have methods that **implement** the class being tested, and **assert** that the methods of the class are working.

In the `StudentTest` class, add methods that test if the `getFullName` and `getLetterGrade` methods are correct. Make sure to add the `@Test` annotation, or else JUnit won't recognize it.

<details>
<summary>Here's what it should look like:</summary>

```java
package co.ga.junittesting;

import org.junit.Test;
import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void testIfFullNameIsCorrect() {

    }

    @Test
    public void testIfLetterGradeIsCorrect() {

    }
}
```

</details>

To assert something, you would use JUnit's `assert_____()` static methods. The main ones are:

```java
    assertEquals(4, 2 + 2);
    assertTrue(true);
    assertFalse(false);
    assertNull(null);
    assertNotNull("Not null");
```

Each of these take an **expected value** and an **actual value**. The expected value is what the method should return, and the actual value is what the method actually returns.

So, filling out the rest of the class:

```java
package co.ga.junittesting;

import org.junit.Test;
import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void testIfFullNameIsCorrect() {
	Student student = new Student("Leslie", "Knope", "A");

	String expected = "Leslie Knope";
	String actual = student.getFullName();

	assertEquals(expected, actual);
    }

    @Test
    public void testIfLetterGradeIsCorrect() {
	Student student = new Student("Charlie", "Brown", "C");

	String expected = "C";
	String actual = student.getLetterGrade();

	assertEquals(expected, actual);
    }
}
```

To run the test, right click on the class in the "Project View" and click "Run StudentTest."

**Note**: If you write multiple test classes, you can right click on the folder that contains the classes and click "Run tests in ______" to run all of them.

----

## Independent Practice: Automated Testing (40 min)

Now it's your turn to write some tests.

The `Math` class provides some handy methods to perform mathematical operations on numbers. Let's write some tests to make sure it's working properly.

* `Math.abs(x)` returns the absolute value of x.
	* Test for both integers and decimal numbers.

* `Math.sqrt(x)` returns the square root of x.

* `Math.pow(x, y)` returns the value of x to the power of y.

* `Math.max(x, y)` returns the larger value between x and y.
	* Test for both integers and decimal numbers.

* `Math.min(x, y)` returns the smaller value between x and y.
	* Test for both integers and decimal numbers.

Need a hint? Check out the [Oracle docs for the Math class](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html).

> Check: With five minutes left, let's review the solution. 

----

## Conclusion (5 min)

* What's the difference between a manual and an automated test?
* What's a unit test?
* Give an "Explain it like I'm 5" definition of how JUnit works.

