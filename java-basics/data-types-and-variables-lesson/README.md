---
title: Data Types and Variables
type: Lesson
duration: "1:30"
creator:
    name: Kristen Tonga
    city: NYC
---


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Data Types and Variables


### Learning Objectives
At the end of this lesson, you'll be able to:
- Identify and describe the Java data types and use cases.
- Describe the different types of variables (local, instance, constant) and when to use them.
- Use class methods to manipulate data in the Math and String classes.
- Describe the difference between NaN and null.

---

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-min)  | Learning Objectives |
| 10 min  | [Introduction](#introduction-data-types-in-java-10-mins)  | Data Types in Java |
| 15 min  | [Demo](#demo-lets-start-with-numbers-15-mins)  | Let's Start With numbers |
| 10 min  | [Demo](#demo-using-special-number-operators-10-mins)  | Using Special Number Operators |
| 10 min  | [Introduction](#introduction-primitives-vs-objects-10-mins)  | Primitives vs. Objects |
| 15 min  | [Demo](#demo-creating-a-new-string-15-mins)  | Creating a New String |
| 10 min  | [Demo](#demo-converting-between-data-types-10-mins)  | Converting Between Data Types |
| 15 min  | [Independent Practice](#independent-practice-practice-15-minutes)  | Practice |
| 5 min  | [Conclusion](#conclusion-5-mins)  | Review and Recap |

<a name="opening"></a>

## Opening (5 min)

In programming, we need a way to store information while our programs are running. This could be anything from names and numbers to dates and many other things, which are all known as **data types**. These data types are stored in **variables**, just like in algebra class. Today, we'll be exploring how data types and variables are the basic building blocks of programming and how we use them to store information in our programs.

## Introduction: Data Types in Java (10 mins)

From Wikipedia:

In computer science and computer programming, a data type — or simply, type — is a classification identifying one of various types of data that determines:
- The possible values for that type.
- The operations that can be done on values of that type.
- The meaning of the data.
- The way values of that type can be stored.

Data types are similar across different languages, including English.

<!-- Instructor note: Put the categories on the board and ask for students to provide the data type, or in some other way create a list on the board that includes the data types below. Include both lowercase primitive and capitalized object types. This chart will be referenced later.-->

|Category    | Data Type                     | Description          | Example |
|------------|------------------------------|----------------------|---------|
|True or False  | `boolean`, `Boolean`                   | Represents either true or false                               |true, false|
|Integers    | `short`, `int`, `Integer`, `long`, `Long`    | Whole numbers, with no delimiter. Can optionally have underscores to make large numbers easier to read	| 42, 1024, 1_000_000 |
|Decimals    | `float`, `Float`, `double`, `Double`       | Decimals, with no delimiter                                   | 42.123, 2.5 |
|Characters  | `char`                               | Single character surrounded by single quotation marks                 | 'a', 'A'|
|Strings     | `String`                             | Single words or sentences surrounded by double quotation marks        | "lots of kittens", "a lazy lizard"    |                                                                | true, false

There are also a few odd ones:
- Byte, which is one bit of data. You don't need to worry about it right now.
- Collections (we'll talk more about this soon).

We'll elaborate on all of the categories on the board, and show you some helper methods to manipulate them.

> Check: Come up with a few examples of data. Predict what type it falls into.

## Demo: Let's Start With Numbers (15 mins)

#### Decimals vs. Integers

<!-- Instructor note: As you code the following examples, ask the students the questions below. If they guess correctly, ask them to explain why.-->

First off, let's talk a bit about those numeric data types.

What do you expect to be printed to the console?

```java
int num1 = 5;
System.out.println("num1, type int = " + num1);
=> num1 = 5
```

How about here?

```java
int num2 = 5 / 2;
System.out.println("num2, type int = 5/2 = " + num2);
=> num2 = 2
```

But why is `num2` not 2.5? Well, in low-level languages (unlike JavaScript, Ruby, or PHP), numbers are strictly typed, and a type is either an integer or decimal. An `int` stores an integer, not a decimal, as demonstrated in the previous function.

So, what data type would we use if we wanted to assign a variable to a decimal?

How about `float`?

```java
float num3 = 5 / 2;
System.out.println("num3, type float = 5/2 = " + num3);
=> num3 = 2
```

> Check: That didn't work quite as expected. Can anyone guess why?

Because both 5 and 2 are automatically assigned data type `int`, when the calculation is done, the answer is also an `int`. We must tell the computer that the divisors are of a decimal type, not an integer type.

```java
float num4 = 5f / 2f;
System.out.println("num4, type float = 5f/2f = " + num4);
=> num4 = 2.5
```

```java
double num5 = 5d / 2d;
System.out.println("num5, type double = 5d/2d " + num5);
=> num5 = 2.5
```

Note: In the previous example, we used both a `float` and a `double` data type to save decimal numbers.

> Check: What is the difference between `float` and `double`, and when should you use each one?

#### Numeric Data Types and Bits

To answer the previous question, it's helpful to understand that a data type defines not only the type of data, but also the methods that can be used to manipulate that data. **Primitive** data types in Java also have a certain pre-assigned size in memory. This is represented in a number of bits.

| Name  | Width in Bits | Range    |
|-------|---------------|----------|
| `float`   | 32          | 3.4e–038 to 3.4e+038 |
| `double`  | 64          | 1.7e–308 to 1.7e+308 |

More memory means more information can fit into a variable. A `double` data type is much larger than a `float` data type.

What does that mean for working with decimals? `float` data types are more memory efficient, while `double` provides more accuracy (it's recommended for currency).

There's also a `BigDecimal` class, which is used when even more decimal points are needed.

The same data type differentiation exists in integers, between `short`, `int`, and `long`.

| Name  | Width in Bits | Range    |
|-------|---------------|----------|
| `short` | 16           | -32,768 to 32,768                  |
| `int`   | 32           | -(2^31) to 2^31 (approx 2 billion) |
| `long`  | 64           | -(2^63) to 2^63                    |

`int` will cover almost all of your integer needs.

> Check: What's the most common data type for decimals? What's the most common data type for integers?

#### Using Standard Arithmetic Operators

Now that we understand a bit more about the numeric data types, let's look at what we can do with them.

Here are the standard arithmetic operators (those you've been learning since grade school):

<!-- Instructor note: Depending on time and student understanding, this can be breezed over (it will have been covered during the JS unit). Be sure to mention modulo, as it's an odd one. When going through each example, ask students to calculate what will be printed to the console.-->

``` java
System.out.println(2 + 2);
System.out.println(2 - 2);
System.out.println(2 / 2);
System.out.println(2 * 2);
System.out.println(2 % 2); // What does this do?
```

## Demo: Using Special Number Operators (10 mins)

Coding languages can at times be limiting with the number of operations they allow you to do. For example, how do you square or cube a number?

Java provides a special `Math` object with some useful methods.

Taking a number to a power? Then use `Math.pow(num1,num2)`.

``` java
// 3^2 becomes
System.out.println( Math.pow(3,2) );
=> 9
```

Taking a square root? Then use `Math.sqrt(num1)`.

``` java
// √(4) becomes
Math.sqrt(4);
=> 2
```

Need a random number? Then use `Math.random()`.

``` java
// returns double value with positive sign greater than or equal to 0.0 and less than 1.0
Math.random()
=> ?
// returns random number in range
int range = Math.abs(max - min) + 1;
(Math.random() * range) + min;
```

> Check: Who provides the Math object? Where do you think you might be able to find more information? ([Oracle Math Documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/Math.html))

## Introduction: Primitives vs. Objects (10 mins)

Before we get into Strings, let's take a step back. Have you noticed that all the data types we've used so far begin with a lowercase letter? What do you notice about the `String` data type?

What's the difference? Do you notice it begins with a capital letter? This is a naming convention used to distinguish between primitive and object data types. Primitive types always start with a lowercase letter (e.g., `int`, `double`, `boolean`), while classes start with an uppercase letter (e.g., `String`, `Integer`).

**Primitive data types** are **pass by value**. Using a primitive as a parameter is like writing a number on a Post-it note and handing it off. `int a = 1;` is a **copy** of the numeric data, not a reference to where the data is stored.

**Object data types** contain attributes and methods, and start with a capital letter. These are **pass by reference**.

In other words, using an object is like using a Dewey Decimal System in the library. A variable assigned to an object is given a number that references where a book can be found in the computer's library but is not a copy of the book itself. `Person a = new Person(Nancy, Drew);` is a reference to the data object that contains all info and methods in the class of that object.

> Java naming convention tips: Classes should start with a capital letter (e.g., `String`, `Integer`), primitives always start with a lowercase letter (e.g., `int`, `boolean`), variables and method names start with a lowercase letter (e.g., `main()`), and multi-word names are camel case (e.g., `vannaWhite`). Class constants (i.e., `final` instance and static variables) are generally all capitalized, with words separated by underscores (e.g., `private final Object MY_MUTEX`).

> Discuss with the person next to you: What does a primitive contain? What does an object contain? What's one easy way to tell the difference between an object and a primitive data type? Be ready to share your answers.

#### Words: `char` and `String`

With that basic introduction to primitives and objects, let's talk about words.

A `char` is a primitive data type. What's an example of a `char`?

A `String` begins with a capital letter because a `String` is an object.

> Discuss with the person next to you: What's an object? Be ready to share your answer.

Strings are collections of letters and symbols known as **characters**, and we use them to deal with words and text.

A `String` is actually an array of `char` data:

``` java
String str = "abc";
// is actually
char data[] = {'a', 'b', 'c'};
```

## Demo: Creating a New String (15 mins)

Strings are a special type of object.

Try this with me. You can instantiate (or create an instance) of a `String` in a few ways:

``` java
// variable can be assigned like a primitive
String a = "I'm a string."
```

Which is really short for:

``` java
// variable assigned like an object
String a = new String("I'm a string too!")
```

#### String Helper Methods

Because a `String` is an object, it has predefined methods we can use.

To find the length of a string, use its `length` property:

```java
"hello".length();
=> 5
```

To get the first letter of a `String`:

```java
"hello".charAt(0);
=> "h"
```

To replace part of a `String`:

```java
"hello world".replace("hello", "goodbye");
=> "goodbye world"
```

To make a `String` uppercase:

```java
"hello".toUpperCase();
=> "HELLO"
```

To add two strings together:
```java
"hello".concat(" world");
=> "hello world"
```

Or concatenate (add together) using `+`:

```java
String twoStringsTogether = "Hello" + " World";
=> "Hello World"
```

##### A Special Note on Equality Among Strings:

What if you want to compare two strings?

```java
String blue = "blue";
boolean withSign = (blue == "blue");            //=> true
boolean withWords = (blue).equals("blue");      //=> true
```

Do you know which one of these would be preferred? Well, let's do another example to show you which and why:

```java
String blue = "blue";
String bl = "bl";
String ue = "ue";
System.out.println(bl+ue);                      //=> blue
boolean withSigns = (bl+ue == blue);            //=> false
boolean withWords = (bl+ue).equals(blue);       //=> true
```

Why isn't `withSigns` true? The output looks the same. Remember, `String` is actually an object, and objects are **passed by reference.**

`==` compares the place where the object was stored on the computer to see whether they're the same. `String blue` contains a reference to where it's stored on the computer, and that's different from where `String bl` is stored. `equals`, on the other hand, is a method that can be called on an instance (`str1`) of a `String` object and compares whether the `char` arrays in each `String` are the same, not whether the references are the same.

In short, use `equals` when comparing strings.

> Check: Why can we call methods on a variable with data type `String` but not on an `int`?

## Demo: Converting Between Data Types (10 mins)

Sometimes, it's necessary to convert between data types. User input is **always** a `String`, such as when you enter your email address, age, income, and more. If you'd like to operate on those numbers though, you'll have convert it to a numeric data type.

Remember how we talked about the size of primitive data types? A `float` is smaller than a `double`, and a `double` is smaller than a `long`.

When converting from smaller types to larger types — for example, `int` (4 bytes) to `double` (8 bytes) — conversion is done automatically. This is called **implicit casting**.

```java
int a = 100;
double b = a;
System.out.println(b);
```

If, on the other hand, you're converting from a bigger data type to a smaller data type — for example, `double` (8 bytes) to `int` (4 bytes) — the change in data type must be clearly marked. This is called **explicit casting**.

```java
double a = 100.7;
int b = (int) a;
System.out.println(b);
```

While that's useful for numbers, to cast successfully, a variable must be an **instance of** the second object.

What do you think would happen if you tried to cast a `String` to an `int`? There's a different way to convert strings to numbers.

Did you notice there's both an `int` and an `Integer` data type? The `Integer` data type is a wrapper around an `int` that provides certain methods.

For example, to convert a `String` to an `Integer`, you can use:

```java
String strValue = "42";
int intValue = new Integer(strValue).intValue();
```

Similar methods exist for all of the wrappers.

#### NaN

If a `String` is converted to a number but contains an invalid character, the result of the conversion will be **NaN**, which stands for  "Not a Number."

NaN is toxic, and if a calculation is attempted on that variable, or a method called subsequently, your program will break.

Use `isNaN()` to test for NaN.

#### Null

A null value is an empty value. Taken from a Stack Overflow post:

```
"Zero" is a value. It is the unique, known quantity of zero, which is meaningful in arithmetic and other math.

"Null" is a non-value. It is a "placeholder" for a data value that is not known or not specified.
```

> Check: When might you get NaN value? What is a null value?

<a name="ind-practice"></a>

## Independent Practice (15 minutes)
<!-- Instructor note: This can be a pair programming activity or done independently.-->

Grab the starter code found in `VariablePractice` and complete all tasks. We'll go over the answers in 12 minutes.

***

<a name="conclusion"></a>

## Conclusion (5 mins)

- Identify the different types of data.
- What type of data do you think is passed as a web request?

### ADDITIONAL RESOURCES
- [Oracle Java Docs on Primitive Data Types](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [Oracle Java Docs on Math Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Math.html)
