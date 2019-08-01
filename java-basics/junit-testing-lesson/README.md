---
title: jUnit Testing
type: lesson
duration: "1:15"
creator: James Davis (NYC)

---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) jUnit Testing

### LEARNING OBJECTIVES

*After this lesson, you will be able to:*
* Given a class, add conditional methods to a test case.
* Perform a jUnit test.

### LESSON GUIDE

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 10 min  | Opening  | Discuss lesson objectives |
| 5 min   | Introduction  | Unit tests |
| 20 min  | Demo  | Creating a Unit Test |
| 40 min  | Independent Practice  | Automated testing |
| 5 min   | Conclusion  | Review / Recap |

## Opening: Man vs. Machine (10 minutes)

There are many, many ways to test something.

You can do it by hand, which is fine. A lot of QA teams test apps by hand. However, this could get tedious. Mainly, because they usually have to test the full feature once one small thing changes.

Imagine that you're a home inspector who's checking out a newly built home. Let's say you (the inspector) have to test if a bedroom is indeed a bedroom. The room must have at least a bed, a dresser, a closet.

Let's also say a interior designer walks in and adds a lamp to the dresser. Then, you have to go back to the room and check if the dresser, bed, and closet are still there.

Now, the designer added a rug. So, you have to check again if the bed, dresser, and closet are still there.

Every time something changes, you have to ensure the thing you are checking does what it is required to do. This can be repetitive, and in some cases a waste of time.

> Check: Take one minute and discuss with the person next to you - what are some ways to prevent this? Share out!

There are a few ways to tackle this problem. They way we will talk about today is **automation**!

Think about the previous example. Now imagine, instead of yourself, you built a robot to inspect the house for you. So, every time the designer changed something in the house, the robot would inspect the room for you! And while that's happening, you are on vacation in some remote, tropical place.

This is the difference between manual and automated testing. You code tests to religiously check your code for defects while you and the QA team have more time to do other things.

----

## Introduction: Unit tests (5 mins)

There are many, many types of automated tests: 
- Unit tests
- Smoke tests
- Integration tests
- Regression tests
- Acceptance tests

This lesson will focus on Unit tests.

### What is a Unit test?

A Unit Test is one that tests a piece of code (a unit). A unit, in most cases in Java, is a Class.

So, think of a unit test as "Is this class and its methods working as expected?"

----

## Demo: Creating a Unit Test (20 mins)

Follow along!

To test our classes (units), we create test classes that test other classes.

Create a new project in IntelliJ. In the **src** folder, you will find three folders:

* **main** - the location of your app's main code (classes, activities, resources, etc.)
* **test** - the location of tests, where we'll add our test classes

We'll build a simple app that returns a student's name and grade.

First, let's create a Student class, which has the methods `getFullName` and `getLetterGrade`. The constructor takes a first name, last name, and number grade.

Then, go create a new test in the src/test package and call it `StudentTest`.

The idea of testing is to have methods in the Test that **implement** the class being tested and **assert** that their methods are working.

In the `StudentTest` class, add methods that test if the `getFullName` and `getLetterGrade` methods are correct. Make sure to add the `@Test` annotation, or else jUnit doesn't recognize it.

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

To assert something, you would use jUnit's `assert_____()` static methods. The main ones are:

```java
	assertEquals(4, 2 + 2);
	assertTrue(true);
    assertFalse(false);
    assertNull(null);
    assertNotNull("Not null");
```

Each of these take an *expected value* and an *actual value*. The expected value is what you think the method should return, and the actual value is what the method actually returns.

So, filling out the rest of the class:

```java
package co.ga.junittesting;

import org.junit.Test;
import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void testIfFullNameIsCorrect() {
				Student student = new Student("Leslie", "Knope", 93);

				String expected = "Leslie Knope";
				String actual = student.getFullName();

				assertEquals(expected, actual);
    }

		@Test
    public void testIfLetterGradeIsCorrect() {
				Student student = new Student("Charlie", "Brown", 76);

				String expected = "C";
				String actual = student.getLetterGrade();

				assertEquals(expected, actual);
    }
}
```

To run the test, you right click on the class in the Project View and click "Run StudentTest".

**Note**: If you write multiple test classes, you can right click on the folder that contains the classes and click "Run tests in ______" to run all of them!

----

## Independent Practice: Automated Testing (40 minutes)

Now, it's your turn to write some tests!

`Math` class provides some handy methods that allow us to perform mathematical operations on numbers. Let's write some tests to make sure it's working properly!

* `Math.abs(x)` returns the absolute value of x.
	* Test for both integer and decimal point numbers

* `Math.sqrt(x)` returns the square root of x.

* `Math.pow(x, y)` returns the value of x to the power of y.

* `Math.max(x, y)` returns the larger value between x and y.
	* Test for both integer and decimal point numbers

* `Math.min(x, y)` returns the smaller value between x and y.
	* Test for both integer and decimal point numbers

*Need a hint? Check out the [Oracle docs for the Math class](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html).*

> Check: With 5 minutes left, let's review the solution. 

----

## Conclusion (5 mins)

* What is the difference between a manual and an automated test?
* What is a unit test?
* Give me an "explain it like I'm 5" definition of how jUnit works.

