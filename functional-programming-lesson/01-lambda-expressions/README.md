
|                     Title                    |  Type  | Duration |  Creator |
|:-------------------------------------------:|:------:|:--------:|:--------:|
| Functional Programming: Lambda Expressions | Lesson |   1:35   | Kyle Dye |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Functional Programming: Lambda Expressions

### Learning Objectives

At the end of this lesson, students will be able to:
* Explain how lambda expressions and streams improve code readability.
* Use lambda expressions to significantly decrease the amount of code needed to accomplish a task.
* Create a lambda expression with streams.

---

### Lesson Guide

| Timing |         Type         |                                           Topic                                          |
|:------:|:--------------------:|:----------------------------------------------------------------------------------------:|
| 10 min |     Introduction     |                         Lambda Expression Syntax                                         |
| 10 min |         Demo         |   To Lambda or Not to Lambda, That Is the Question                                       |
| 10 min |     Introduction     |                          Streams and Collections                   |
| 10 min |         Demo         |                 Iterating Through a Collection Using Streams                    |
| 10 min |     Introduction     |              `map` and `collect`                        |
| 10 min |         Demo         | Rewrite Previous Demo Using Collectors |
| 10 min |     Introduction     |                                      Filters                                     |
| 20 min | Independent Practice | Complete a Program Using Streams, Lambda Expressions, Filtering, and Collecting |
|  5 min |      Conclusion      |                                       Review/Recap                                       |

## Lambda Expression Syntax (10 min)

![](./LambdaSyntax.png)

A lambda expression is an anonymous function with a concise and functional syntax that's used for writing anonymous methods. Lambda expressions are similar to anonymous classes in that they enable you to express functionality as data — for example, to pass functionality into a method as a parameter. But where anonymous classes require a lot of boilerplate code to set up the class, lambda expressions provide a concise syntax specifically for expressing code as data.

A lambda expression consists of the following:
- A comma-separated collection of formal parameters enclosed in parentheses.
- Followed by an arrow `->`.
- Followed by the body, consisting of either a single expression or a statement block.

You may — but are not required to — specify the data type of the parameters in a lambda expression, and where there's no ambiguity, they're usually omitted for brevity. In addition, you may omit the parentheses around the parameter list, provided there's exactly one parameter. 

Finally, if the body consists of more than one statement, it must be enclosed in squiggly brackets. However, if it consists of exactly one statement, the brackets may be omitted, in which case the `return` keyword must also be omitted, as well as the semicolon at the end of the lambda expression.

**Example:**

    (int a, int b) -> { return a * b; }
    
The above example has two `int` type parameters: `a` and `b`. The expression body will multiply the `int` parameter `a` with the `int` parameter `b`.

In this example, the type `int` is usually optional (depending on context), and can be expressed as:  

    (a, b) -> { return a * b; }  
    
Because it's a one-statement lambda, we can also drop the brackets, `return`, and semicolon:

    (a, b) -> a * b  
    
-----    

## Demo: To Lambda or Not to Lambda, That Is the Question (10 min)

In the following demo, we'll create a `Computation` interface. We'll use this interface to solve simple math problems. We'll create two versions of the demo: one without lambda expressions and one with lambda expressions.

**Example without lambda expressions:**

```java    
package com.ga.examples;

public class NonLambdaExpressionIntroDemo {

    // Here's the Computation interface
    interface Computation {
        int operation(int a, int b);
    }

    public static void main(String[] args) {

        // Notice the use of the anonymous inner class.
        Computation add = new Computation() {

            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };

        System.out.println("5 + 6 = " + add.operation(5,6));

        // Notice the use of yet another anonymous inner class.
        Computation subtract = new Computation() {

            @Override
            public int operation(int a, int b) {
                return a - b;
            }
        };

        System.out.println("10 - 6 = " + subtract.operation(10,6));

    }
}
```

The output will be the following:

- `5 + 6 = 11`
- `10 - 6 = 4`

**Example with lambda expressions:**

```java
package com.ga.examples;

public class LambdaExpressionIntroDemo {

    interface Computation {
        int operation(int a, int b);
    }

    public static void main(String[] args) {

        Computation add = (a, b) -> a + b;

        System.out.println("5 + 6 = " + add.operation(5,6));

        Computation subtract = (a, b) -> a - b;

        System.out.println("10 - 6 = " + subtract.operation(10,6));
    }
}
```

Notice the reduction of the amount of code needed to do the computation:

- **Example 1:** 680 characters and 32 lines  
- **Example 2:** 416 characters and 19 lines  

This is a 39 percent reduction in characters and a 41 percent reduction in the lines of code. Also, in most cases, code that uses lambdas becomes more expressive and more readable.

Let's take a look at some more examples of lambda expressions. The next example has the following functional interface:

```java
interface HelloYou {
    void greetYou(String name);
}
```

If you notice, the `greetYou` method takes in one parameter (`name`) and has a `void` return type. The following code implements this method with the lambda on line 1:

```java
HelloYou helloYou = name -> System.out.println("Hello " + name);
helloYou.greetYou("Michael"); // => Hello Michael
```

You'll notice that the lambda has one parameter, so you can omit the parentheses. The parameter also corresponds to the single input parameter for the `greetYou` method. The `println` method in the lambda body has a `void` return type, which corresponds to the return type of the `greetYou` method.

This next example has the following functional interface:

```java
 interface Greeting {
    void greet();
}
```

The `greet` method has no input parameters and has a `void` return type. The following code shows how to implement this with a lambda expression (line 1):

```java
Greeting greeting = () -> System.out.println("Hello whoever you are!");
greeting.greet(); // => Hello whoever you are!
```  

To represent the empty parameter for the `greet` method, you'd use `()`. The `println` method has a `void` return type, which corresponds to the return type of the `greet` method.

------

## Streams and Collections (10 min)

### What Is a Stream?

A **collection** is a data structure that stores elements, while a **stream** is a sequence of elements that moves values from a source — such as a collection — through a pipeline of steps. Stream operations leverage lambda expressions to produce a result, as we'll see shortly.

### What Is a Pipeline?

A **pipeline** is a sequence of operations — such as filtering, modifying, or aggregating — that are applied to a source stream to produce a result. The result can be anything: a primitive, an object, a collection, or even another stream. The operations are supplied as parameters, expressed as lambda expressions. Some of the common stream operations are `map`, `filter`, `sum`, `average`, `sort`, and `forEach`.

### Types of Stream Operations

Stream operations are used to modify streams to create an end result. Stream operations come in two major flavors:  
- **Intermediate operations** take a stream as input, filtering and modifying the input elements to produce a new stream as output. Some of the commonly used intermediate operations are `map`, `filter`, `sort`, and `flatMap`. 
- **Terminal operations** consume a stream to produce a final result. Once a terminal operation is reached, the stream is fully consumed, and no further operations can be applied to the stream. Some common terminal operations are `collect`, `reduce`, and `forEach`.

### Getting a Stream From a Collection

Let's say we have the following list:

```java
List<String> stringList = Arrays.asList("Hello", "World");
```

To generate a stream from the list, we say:

```java
Stream stream = stringList.stream();
```

To print out the elements of the `stringList` collection using a stream, we could do the following:

```java
stringList.stream().forEach(stringValue -> {
    System.out.println(stringValue);
});
```

Or even simpler:

```java
stringList.forEach(x -> System.out.println(x));
```

We can use the `forEach` operator directly on the list because Java collections have a built-in `forEach()` method, which implicitly calls `stream().forEach()`.

The output would be:

`Hello`  
`World`

-----

## Demo: Iterating Through a Collection Using Streams (10 min)

In this demo, we'll take an existing list of `String` objects and iterate through it to produce a new list of strings, consisting of the values from the original list concatenated with a constant string value.

```java
package com.ga.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListIterationWithConcatenationDemo {

    public static void main(String[] args) {
        // The original list
        List<String> stringList = Arrays.asList("My name is ", "My friends call me ", "My mother calls me ");
        List<String> concatenatedList = new ArrayList<>();

        stringList.forEach(stringValue -> concatenatedList.add(stringValue + "Jim");

        // Output the values of the concatenated list using streams.
        concatenatedList.forEach(stringValue -> System.out.println(stringValue));

        // Output the values of the original list using streams to show that the list is unaltered.
        stringList.forEach(stringValue -> System.out.println(stringValue));
    }
}
```
    
The output would be:  

`My name is Jim`
`My friends call me Jim`
`My mother calls me Jim`
`My name is`
`My friends call me`
`My mother calls me`


The original list is unaltered when using streams.

> **Knowledge Check:** How many lambda expressions are in the demo?

-----

## `map` and `collect` (10 min)

### What Does the `map` Function Do?  

The `map()` intermediate operation is a method in the `Stream` class that represents a functional programming concept. `map()` is used to transform one object into a different object by using a lambda expression.

**Syntax:**  

`.map(argument -> function to apply)`

> Notice the lambda expression in the syntax above.

**Example:**  
    
```java
List<String> numbersList = Arrays.asList("1", "2", "3", "4", "5");

Stream<Integer> numberListStream =
    numbersList.stream()
        .map(number -> Integer.valueOf(number));
```

In the example above, we're iterating through the list, and for each value, the `number` argument represents the next number in the list. The function `Integer.valueOf(number)` is applied to each of those `number` values in turn, producing a new stream of `Integer` values that itself can be further filtered, aggregated, and converted to other objects.

> **Note:** The `map()` function will always return a stream because it's an intermediate operation.

### What Are Collectors?

In the previous examples, we've seen how to iterate through streams and how to map streams to new objects to produce new streams. But at the end of the day, we want values — not streams. So how do you come up with an end result? That's where collectors come in.

Collectors are terminal operators used to implement various reduction operations, such as accumulating elements into collections, summarizing elements according to various criteria, and more. In short, collectors produce an end result from an input stream.

Java provides a `Collectors` class with many static methods. Let's zoom in on one of these methods — `toList()` — which we'll use to expand our earlier example by consuming a stream of `String` objects representing integers to produce a new list of corresponding `Integer` values.

```java
List<String> numbersList = Arrays.asList("1", "2", "3", "4", "5");

List<Integer> newNumbersList =
    numbersList.stream()
        .map(number -> Integer.valueOf(number))
        .collect(Collectors.toList());
```

The `map` operation converts each `String` from the input stream into an `Integer`, producing a new list of `Integer` values.
 
The `collect` operation consumes the resulting `Integer` stream and produces a list of corresponding `Integer` objects.
 
> **A side note about how intermediate operators are evaluated:** Intermediate operators, such as `map`, are lazy in nature. This means they don't execute until a terminal operator is encountered. In the case of the code above, the `map` logic won't execute until the `collect` is encountered.

----

## Rewrite Previous Demo Using Collectors (10 min)

For this demo, we'll revisit the `My name is Jim` demo above, but this time we'll use a collector instead of manually adding elements to a new list.

```java
package com.ga.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapWithConcatenationDemo {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("My name is ", "My friends call me ", "My mother calls me ");

        List<String> concatenatedList =
            stringList.stream()
                .map(stringValue -> stringValue + "Jim")
                .collect(Collectors.toList());

        // Output the values of the concatenated list using streams.
        concatenatedList.stream().forEach(stringValue -> System.out.println(stringValue));

        // Output the values of the original list using streams to show that the list is unaltered.
        stringList.stream().forEach(stringValue -> System.out.println(stringValue));
    }
}
```

The output would be:  

`My name is Jim`
`My friends call me Jim`
`My mother calls me Jim`
`My name is`
`My friends call me`
`My mother calls me`   

<-- **Instructor notes:**
- Point out to the class that the code is more concise and readable by using collectors.
- Also point out the stream pipeline of `stringList.stream()`, `map`, and `collect`.-->

-----

## Filters (10 min)

So far, we've discussed how streams can be manipulated via intermediate and terminal operations. We've seen how to use the intermediate operation `map` and the terminal operations `forEach` and `collect`. But what if we had a requirement where we only needed a subset of the data based on certain criteria? This is where the `filter` intermediate operation comes in.

### What Is a Filter?

The `filter` method essentially selects a subset of elements from the original list based on a "predicate" condition expressed as a lambda. The `filter` method accepts a `Predicate` object, which provides a function applied to a condition. We'll see in the "Functional Interfaces" lesson that the lambda is used to express the `Predicate` functional interface by taking an input value and producing a boolean. 

If the condition evaluates to `true`, the object is selected. Otherwise, it's ignored.  

**Example:**

```java
List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// We want to create a list of only the even numbers.
List<Integer> evenNumberList =
    numberList.stream()
        .filter(number -> number % 2 == 0)
        .collect(Collectors.toList());

evenNumberList.forEach(number -> System.out.println(number));
```

In this example, we use a lambda expression in the `filter` method to express this condition: "If the number is even, return `true` and keep it. Otherwise, return `false` and ignore it."

The output would be:  

`2`
`4`
`6`
`8`
`10`

-----

## Independent Practice (20 min)

For the Independent Practice, we'll take the template program below and complete the `TODO` parts commented out. To confirm that your results are correct, iterate through each list you create and output the results.

**Hints:**  
- You'll need to leverage the intermediate operations `map` and `filter`.
- You'll also need to use the terminal operation `Collectors.toList`.

### Independent Practice Template

```java
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
            return "Person {" +
                    "name = '" + name + '\'' +
                    ", age = " + age +
                    ' }';
        }
    }

    public static void main(String[] args) {

        List<Person> personList = createPersonList();

        // TODO: Create a list of Person objects whose names start with the letter "M"

        // TODO: Create a list of Strings containing the names of persons over the age of 40

        // TODO: Create a list of Person objects whose names start with the letter "J" and are under the age of 47
        
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
```

The completed example can be found in the repo, in the file named `IndependentPracticeCompleted.java`.

----

## Conclusion (5 min)

We've covered a lot in this brief introduction to lambda expressions, streams, filtering, and collecting. Hopefully, this introduction shows how this **functional** style can improve code readability and reduce the amount of code needed to achieve a task as compared to the classic **imperative** style.

#### Let's Review

With a partner, draft a one-sentence answer to each of these questions:
- What is a lambda expression?
- What makes lambdas great?
- What is a stream?
- What is filtering?
- What is collecting?

## Additional Resources
- [Oracle Java Tutorial: Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Java 8 `map`, `filter`, and `collect` Examples](https://dzone.com/articles/how-to-use-map-filter-collect-of-stream-in-java-8)
- [Java API: `Collectors`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)
