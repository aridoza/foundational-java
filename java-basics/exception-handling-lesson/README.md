---
title: Exception Handling lesson
duration: "1:30"
creator:
    name: Drew Mahrt & Isha Arora
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Exception Handling


### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
- Describe what an Exception
- Describe the purpose of try/catch blocks
- Implement try/catch blocks

---

### LESSON GUIDE

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-mins)  | Discuss lesson objectives |
| 5 min  | [Introduction](#introduction-exception-5-mins)  | Exception |
| 10 min  | [Introduction](#introduction-trycatch-block-10-mins)  | Try/Catch Block |
| 10 min  | [Demo](#demo-arrayindexoutofboundsexception-10-mins)  | ArrayIndexOutOfBoundsException |
| 5 min  | [Guided Practice](#guided-practice-divide-by-zero-5-mins)  | Divide By Zero |
| 5 min  | [Introduction](#introduction-when-you-should-use-trycatch-blocks-5-mins)  | When you should use Try/Catch blocks |
| 10 min  | [Independent Practice](#independent-practice-file-reading-and-writing-10-mins)  | File Reading and Writing |
| 5 min  | [Conclusion](#conclusion-5-mins)  | Review / Recap |

<a name="opening"></a>

## Opening (5 mins)

We often check for code that could cause errors manually through conditional statements, but Java (as well as many other programming languages) provides a very useful tool called a Try/Catch block. This helps to not only shape the behavior of our apps if an error occurs, but also lets us stop our apps from completely crashing if an error-prone portion of code is used (such as file streaming or networking operations).

> Check: Ask the students if they have encountered any errors that have "exception" in the name.

***

<a name="introduction"></a>

## Introduction: Exception (5 mins)

Before we can start talking about the try/catch block, we need to talk about Exceptions. Exceptions are events that occur while a program is running that interrupts the normal flow of the code. These can be null pointer exceptions, divide by zero, array out of bounds, etc. You can see many of the built-in exceptions in the [Java documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html).

There are two types of exceptions- Checked and Unchecked. A **checked exception** is an exception that occurs at compile time, that means a programmer is forced to handle these exceptions otherwise program will not compile. Checked exceptions are subclasses of Exception class. An **unchecked exception** occurs at the time program is executing. Also know as Runtime exceptions. You don't have to handle them, you can if you want to.

This is the exception hierarchy. Red is unchecked exceptions. Blue is checked.

![](https://cdn2.howtodoinjava.com/wp-content/uploads/ExceptionHierarchyJava.png)
<sub>[source](https://howtodoinjava.com/java/exception-handling/checked-vs-unchecked-exceptions-in-java/)</sub>

When an Exception occurs, we say that it is **thrown**. This will become important when we look at the Try/Catch blocks. While many parts of Java throw exceptions on their own, you can also manually throw exceptions as well.

***

<a name="introduction"></a>
## Introduction: Try/Catch Block (10 mins)

Alone, exceptions aren't particularly useful, but when paired with a Try/Catch block, they become very important. A Try/Catch block looks like this:

```java
try{
  //Your code goes here
}catch(Exception e){
  //Execute this code if an error occurs
}
```

If you remember from earlier, we said that Exceptions are **thrown**. We also say that they are **caught** in the catch statement. `Exception` is the most generic type of Exception that all other exceptions are derived from, and you can catch multiple types of exceptions from a single block of code.

It is important to note that the code in the catch block is only executed if an Exception is thrown that matches the type of Exception declared.

> Check: Talk with the person next to you: Why would we want to be able to catch multiple types of exceptions?

***

<a name="demo"></a>

## Demo: IndexOutOfBoundsException (10 mins)

Let's take a look at a case where we try to access a value from an array with an index outside of its bounds. Open up the starter-code for [IndexOutOfBounds](https://git.generalassemb.ly/GA-Cognizant/foundational-java/tree/master/java-basics/exception-handling-lesson/starter-code/IndexOutOfBounds). As you can see, this program has a list of superheroes and lets us enter a number to access our favorite superhero.

If we run the program and type in a number that is outside of the bounds of the arraylist, we get an [IndexOutOfBoundsException](https://docs.oracle.com/javase/8/docs/api/index.html?java/lang/IndexOutOfBoundsException.html) (example of unchecked exception), and everything crashes! Instead of having the program crash, we can still log the error, but let the program handle the exception gracefully by letting the user know what they entered isn't valid.

Let's add a Try/Catch around the code that is causing the error.

> Check: Take 1 minute to discuss what code you think should be inside of the try block.

```java
try {
	System.out.println(superheroes.get(num));
}catch(IndexOutOfBoundsException e) {
	System.out.println("The number you entered is invalid. Please enter from 0 to 2");
}
```

As you can see, the code that is causing the error is `superheroes.get(num)`. By putting that inside of the try block, we can catch the exception, and show a message to the user. The program will continue running after the error has occurred, but we know the error occurred.

> Check: Why didn't we put Exception instead of IndexOutOfBoundsException?

***

## When you should use Try/Catch blocks (10 mins)

In most situations Try/Catch blocks are not mandatory, so using them is completely up to you. If you have an expectation that a certain error has a decent chance of occurring, you can add the Try/Catch to handle it.

There are, however, some circumstances where you are absolutely required to have Try/Catch blocks, such as File reading/writing and networking. Let's look at a few lines of code that force us to use try/catch. You can use any open Java project to add these two lines of code.

```java
URL url = new URL("http://www.google.com");
HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
```
After importing the dependencies you will ses that the IDE is still showing a compiler error. If you hover over the error you will see two options either to surround the code with try/catch OR add throws declaration. Lets go with try/catch for now, we will discuss the other option bit later.

We can consolidate the two try/catch blocks like this:

```java
URL url = null;
try {
    url = new URL("http://www.google.com");
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
} catch (MalformedURLException e) {
    e.printStackTrace();
} catch (IOException e){
    e.printStackTrace();
}
```
> Both IOException and MalformedURLException are examples of checked exception. You have to handle them in order for the program to compile.

Post Java 8 we can combine our catch blocks in something like below. It will do exactly the same as above but with fewer lines of code. 

```
URL url = null;
try {
    url = new URL("http://www.google.com");
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
} catch (MalformedURLException | IOException e) {
    e.printStackTrace();
}
```

***

<a name="guided-practice"></a>

## throws keyword

Now lets talk about the other option our IDE was giving us, adding throws declaration. Another way of handling a checked exception is by using `throws` keyword. We use this keyword to tell the calling method that it is your responsibility to handle the error this piece of code might throw. 

In the previous example where we were trying to connect to a URL,

```java
URL url = new URL("http://www.google.com");
HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
```

Instead of try/catch we could have used `throws` keyword. Again you can use any open project to write this code.

```java
public void connectToURL() throws IOException {
	URL url = new URL("http://www.google.com");
	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
}
```
Now whichever method will call `connectToURL()` can either handle the exception there or futher throw it to the calling method.

The biggest advantage of using this keyword is having all exception handling at one place as your application grows and become more complex. Also, it helps to declutter your code.

## throw keyword
We use this keyword to throw an exception manually from anywhere in the code. This concept is best explained through an example. 

In this next example, we will be looking at a program that divides two numbers for us and displays the result. Open up the [DivisionExample starter-code](https://git.generalassemb.ly/GA-Cognizant/foundational-java/tree/master/java-basics/exception-handling-lesson/starter-code/DivisionExample).

> Check: Give the students 2 minutes to look at the code and determine how we should handle the error if it occurs.

In this code if you run with any value higher than 0 in divisor the program runs smoothly. But as soon as you input 0 in divisor the program fails with the exception `/ by zero`. This is a type of [ArithmeticException](https://docs.oracle.com/javase/8/docs/api/?java/lang/ArithmeticException.html).

In this case, in `division()` method we will check if the divisor is 0, throw an exception.

We can handle this exception by surrounding our code with try/catch block as done previously. But this time we will let the calling method, in our case `main()` handle the exception.

```java
public static void division(int numerator, int divisor) {
		
	if(divisor == 0)
		throw new ArithmeticException("Divisor is 0");
	
	float result = numerator/divisor;
	System.out.println("Result of the division is: " + result);
}
```

We need to put the actual division operation in the try block because that's where the error is actually going to happen. In our catch block, we let the user know that they can't give 0.

```
public static void main(String[] args) {
...
	try {
		division(numerator, divisor);
	}catch(ArithmeticException e) {
		System.out.println("Error: Divisor must be greater than 0");
	}
}
```

> Check: Is it better to check for divide by 0 with an Exception or manually checking to see if the divisor is 0?

***


<a name="ind-practice"></a>

## Lab: File Reading and Writing (45 mins)

Create an app that quizes users on their superhero trivia. Open the Superheroes [starter-code](https://git.generalassemb.ly/GA-Cognizant/foundational-java/tree/master/java-basics/exception-handling-lesson/starter-code/Superheroes) and start working from there.

Your app reads from an `input.txt` file the superhero info, stores it in an ArrayList. Based on the answers keeps count of every right and wrong answer. If the number of right answers is greater than wrong ones, declare user a winner and write user's name and result of the quiz in an `output.txt` file.

You can refer to solution-code [here](https://git.generalassemb.ly/GA-Cognizant/foundational-java/tree/master/java-basics/exception-handling-lesson/solution-code/Superheroes).
***

<a name="conclusion"></a>

## Conclusion (5 mins)

Exceptions are a very important part of keeping our apps running when problems occur. Sometimes, problems are out of our hands, such as certain file IO or networking situations, and we need to be prepared to handle those exceptions.

***

### ADDITIONAL RESOURCES
- [Try blocks](https://docs.oracle.com/javase/tutorial/essential/exceptions/try.html)
- [Catch blocks](https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html)
