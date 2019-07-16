

|                     Title                    |  Type  | Duration |  Creator |
|:-------------------------------------------:|:------:|:--------:|:--------:|
| Functional Programming - Functional Interfaces | lesson |   1:30   | Kyle Dye |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Functional Programming | Functional Interfaces

### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
* Have a strong understanding about Functions, Suppliers, Consumers, Operators, and Predicates

### STUDENT PRE-WORK
*Before this lesson, you should already be able to:*
- Understand the Java Basics lessons
- Understand the OOP in Java lessons
- Understand the Intro to Lambda Expressions
- Understand Stream Operations and Collectors

### INSTRUCTOR PREP
*Before this lesson, instructors will need to:*
- Read through the lesson
- Add additional instructor notes as needed
- Edit language or examples to fit your ideas and teaching style
- Open, read, run, and edit (optional) the starter and solution code to ensure it's working and that you agree with how the code was written

---

### LESSON GUIDE

| TIMING |         TYPE         |                                           TOPIC                                          |
|:------:|:--------------------:|:----------------------------------------------------------------------------------------:|
|  5 min |        Opening       |                                 Introduction to Functional Interfaces                                |
| 10 min |     Introduction     |                         Functional Interfaces Deep Dive                        |
| 10 min |     Demo     |                         Functional Interfaces Demo                        |
| 5 min |     Introduction     |                         Function                        |
| 5 min |     Demo     |                         Function Demo                        |
| 5 min |         Introduction         |   BiFunction   |
| 5 min |     Demo     |                          BiFunction Demo                         |
|  5 min |         Introduction         |                      Supplier                     |
| 5 min |     Demo     |                         Supplier Demo                       |
|  5 min |         Introduction         |      Consumer      |
| 5 min |     Demo     |                                      Consumer Demo | 
| 20 min | Independent Practice | Take what we've learned and complete a program using functions, suppliers, consumers, operators, and predicates |
|  5 min |      Conclusion      |                                       Review/Recap                                       |

## Opening - Introduction to Functional Interfaces   
We have learned a lot in the first 2 modules.  Now we are going to dive deeper into Functional Interfaces.  This is the heart and soul of a lambda expression. To recap, a lambda expression is an anonymous function which provides a very concise and functional syntax which is further used for writing anonymous methods. Lambda expressions are similar to anonymous classes.  Remember that the lambda expression syntax is as follows:

    (arg1, arg2,...) -> lambda expression body

To begin this lesson, we will take a look at how to create your own functional interfaces.

## Introduction - Functional Interfaces Deep Dive
**What is a functional interface?**  A functional interface is an interface that contains only one abstract method. These interfaces should be annotated with the @FunctionalInterface annotation to aid the compiler to throw an error when more than one abstract method is defined.

**Why use a functional interface?** The benefit of using a functional interface is that we can leverage lambda expressions to represent them, instead of having to use anonymous classes. It is also less verbose in terms of lines of code. Let's go through the demo to see some examples.

**What is a method reference?**  You use lambda expressions to create anonymous methods. Sometimes, however, a lambda expression does nothing but call an existing method. In those cases, it's often clearer to refer to the existing method by name. Method references enable you to do this; they are compact, easy-to-read lambda expressions for methods that already have a name. You'll see an example of a method reference later in one of our demos.

## Demo - Functional Interfaces
For this demo, we will be using 4 different functional interfaces examples so that you can get familiar and comfortable with them. 

### Example 1 - A functional interface that has no input and returns void.
Given the following functional interface, we can use it to print "Hello World!"

    @FunctionalInterface
    public interface VoidFunctionalInterface {
        public void greet();
    }

    //Use the VoidFunctionalInterface to say Hello.
    VoidFunctionalInterface greetTheWorld = () -> System.out.println("Hello World!");
    greetTheWorld.greet();

Output:  
Hello World!

Taking a look at the interface... You'll notice that it's annotated with @FunctionalInteface.  It also has only one abstract method, which meets the criteria of a functional interface.  

To use the functional interface, we create a variable of type VoidFunctionalInterface called "greetTheWorld".  The value of the variable is a lambda expression that takes in no arguments and prints out "Hello World!".

The last thing we do is call the greet method for it to execute.

### Example 1 Continued - Using an Anonymous class instead of lambdas.
Let's write the same example as before, but instead, let's use an anonymous class.

    //Anonymous class example of the above.
    VoidFunctionalInterface voidFunctionalInterfaceImpl = 
        new VoidFunctionalInterface() {
            @Override
            public void greet() {
                System.out.println("Hello World!");
            }
        };
    voidFunctionalInterfaceImpl.greet();

The output is the same as before, but what stands out to you when you compare this version with the lambda expression version?  I don't know about you, but the anonymous class version seems verbose and harder to read when compared to the lambda expression version.  Let's look at some more examples.

### Example 2 - A functional interface that takes in one input and returns void
Given the following functional interface, it will greet a person based on a given name.

    @FunctionalInterface
    public interface GreetByName {
        public void greet(String name);
    }

    GreetByName greetSomeone =
            (name -> System.out.println("Hello " + name + "!  How are you doing today?"));
    greetSomeone.greet("Mark");

Output:  
Hello Mark! how are you doing today.

If you look at the greet method of the GreetByName functional interface.  You'll notice that it takes in a String parameter called "name".  This parameter will become the input parameter to the lambda expression that is used further down in the example.  In this case, the lambda input parameter is called "name".  The parameter names don't have to match, but it is good practice to name them according to what they are.

### Example 3 - A functional interface that has one input and produces one output.
The following example will use a functional interface to take an integer given as input and output the square of that given input.

    @FunctionalInterface
    public interface SquareMe {
        public Integer square(Integer input);
    }

    SquareMe squareMe = (number) -> number * number;
    System.out.println("The square of 11 is " + squareMe.square(11));

Output:  
The square of 11 is 121

Similar to example 2, the functional interface method "square" takes in one input called "input".  However, this type the "square" method returns an integer.  If you look at the lambda expression, the input parameter "number" matches up to the "input" parameter of the "square" method. The body of the lambda expression will take the input and multiply it by itself.

To get the output, you have to call the "square" method on the "squareMe" variable. ie.

    squareMe.square(11)

### Example 4 - A functional interface that takes multiple inputs and returns one output.
The following example will use a functional interface method that takes in a first, middle, and last name and concatenate them together.

    @FunctionalInterface
    private static interface CreateFullName {
        public String fullName(String firstName, String middleName, String lastName);
    }

    //Use the CreateFullName interface to greet a person by their full name.
    CreateFullName whatsMyName =
            ((firstName, middleName, lastName) -> firstName + " " + middleName + " " + lastName);

    String fullName = whatsMyName.fullName("Michael", "Jeffrey", "Jordan");
    System.out.println("Hello " + fullName + ", it's a pleasure to meet you.");

Output:  
Hello Michael Jeffrey Jordan, it's a pleasure to meet you.

As before, compare the input parameters of the "fullName" functional interface method with the input parameters of the lambda expressions.  The lambda body does the dirty work of concatenating the name portions together.

## Built-In Functional Interfaces
In the previous section, we discussed how to create and use your own custom functional interfaces. However, Java was nice enough to provide us with a number of built-in functional interfaces that should meet a majority of our needs.  In the next 4 sections will discuss some of these built in functional interfaces.  

There is something that I want you to keep in mind while going through the following sections. These built-in functional interfaces are structures and NOT semantics.  In other words, their use cases are created by the implementor. 

## Introduction - Function
Function is a built-in functional interface that has one abstract method "apply" that accepts one input and produces one output. 

Functional Interface looks like:

    @FunctionalInterface
    public interface Function<T, R> {

        R apply(T t);

        ...
    }

Usage of a Function is:

    Function<T, R> functionVariable = (T arg) -> lambda body that returns the R type

- T is the input argument of a type specified
- R is the return of the function of a specified type. Let's check out the demo to see it in action.

**Key abstract method**
- apply - this triggers the Function execution to run the lambda expression body.

**Professor Note**  
Please have the class compare the SquareMe functional interface in Example 3 in the last demo with the Function interface above.  The SquareMe's abstract method is called "square", whereas the Function abstract method is called "apply".  Even though the method names are different, the SquareMe interface could be replaced with a built-in Function and create the same results.

## Demo - Function
Let's take a look at a couple of examples of how to use Function.

The first example will take in a String that represents a name and the output will be a greeting.

    //Create a greeting function
    Function<String, String> greetingFunction = s -> "Good Morning " + s + "!";
    String greeting = greetingFunction.apply("Kyle");
    System.out.println(greeting);

Output:  
Good Morning Kyle!

So let's talk about what's going on with this example.  Let's examine the first line:

    Function<String, String> greetingFunction = s -> "Good Morning " + s + "!";

If you look at Function generic "Function<String, String>".  The first String represents the type of the input.  The second String represents the type of the output.

Looking at the lambda expression, the "s" input parameter will be of type String which coincides with the first String of the Function generic.  The lambda body will return a string as well, which coincides with the second String of the Function generic.

Let's look at the 2nd line:  

    String greeting = greetingFunction.apply("Kyle");

The "apply" method is what executes the defined lambda expression and returns the result to the "greeting" variable.

Here's another example of a Function that takes in an Integer and outputs an Integer that represents the square of the number.  This could be used to replace our custom SquareMe functional interface of Example 3 in the last demo.

    Function<Integer, Integer> squareFunction = s -> s * s;
    Integer squareOfNine = squareFunction.apply(9);
    System.out.println(squareOfNine);


## Introduction - BiFunction  
BiFunction is another built-in functional interface.  Like Function, BiFunction has one abstract method called "apply".  However, BiFunction's apply method accepts 2 input parameters and produces 1 output. The layman's term way of thinking about this is that it takes in two inputs and gives one output.

Functional Interface looks like:

    @FunctionalInterface
    public interface BiFunction<T, U, R> {

        R apply(T t, U u);
        ...
    }

Usage of a BiFunction is:
    
    BiFunction<T, U, R> functionVariable = (T arg, U arg) -> lambda body that returns the R type

- T is the 1st input argument of a type specified
- U is the 2nd input argument of a type specified
- R is the return type of the function. Let's check out the demo to see it in action.

**Key Methods**
- apply - this triggers the Function execution to run the lambda expression body.

## Demo - BiFunction 
Let's take a look at a couple of examples of how to use a BiFunction.  The first example will take 2 Integers as input and output an Integer result that represents the sum of the 2 Integers.

    BiFunction<Integer, Integer, Integer> addFunction = (a, b) -> a + b;
    Integer addResult = addFunction.apply(4,5);
    System.out.println(addResult); 

Output:  
9

Let's examine the first line:

    BiFunction<Integer, Integer, Integer> addFunction = (a, b) -> a + b;

The first 2 Integers in BiFunction generic represent the types of input #1 and input #2, respectively.  The last Integer represents the type of the output.

The 2nd line runs the "apply" method which will trigger the lambda expression to run by passing 4 and 5 as the input.  The lambda body will run 4 + 5 and return the result.

The next BiFunction example will take in 2 Integers and return an Integer that represents the result of multiplying the 2 Integers together.

    BiFunction<Integer, Integer, Integer> multiplyFunction = (a, b) -> a * b;
    Integer multiplyResult = multiplyFunction.apply(4,5);
    System.out.println(multiplyResult);

Output:  
20


## Introduction - Supplier
Supplier is a built-in functional interface that accepts no input and gives an output. 

Functional Interface looks like:

    @FunctionalInterface
    public interface Supplier<T> {

        T get();
    }

Usage of a Supplier is:
 
    Supplier<T> functionVariable = () -> lambda body that returns the T type

- T is the return of the function of a specified type

**Key Methods**
- get - this will trigger execution of the lambda body

**A Good Supplier Strategy**  
A good strategy for using Supplier is for scenarios that require no input but may be expensive to run.  Suppliers leverage lazy evaluation, meaning they won't executed until actually needed.  You can use that to your advantage to avoid making the expensive method call until it's absolutely need.  The following demo will help explain it better.

## Demo - Supplier
The following demo will show an example of how using a Supplier could save you from running an expensive operation when it's not necessary.  Let's say you have the following:

    public static void main(String[] args) {

        //Let's see an example without the supplier when calling an expensive operation.
        String doTheyMatch =
                eagerMatch(expensiveComputeOperation("bb"),    
                           expensiveComputeOperation("aa"));
        System.out.println(doTheyMatch);
    }

    private static String eagerMatch(boolean b1, boolean b2) {
        return b1 && b2 ? "match" : "incompatible!";
    }

    private static boolean expensiveComputeOperation(String input) {
        System.out.println("executing expensive computation...");
        // expensive computation here
        return input.contains("a");
    }

Output:  
executing expensive computation...  
executing expensive computation...  
incompatible!  

As you can see, the expensiveComputeOperation is called twice.  Even though the "bb" parameter returns false, the "aa" operation still runs when it doesn't need to.  Now let's look at how you could avoid that by using Suppliers.

    public static void main(String[] args) {

        //Let's use a supplier.
        doTheyMatch = lazyMatch(() -> expensiveComputeOperation("bb"), () -> expensiveComputeOperation("aa"));
        System.out.println(doTheyMatch);
    }

    private static boolean expensiveComputeOperation(String input) {
        System.out.println("executing expensive computation...");
        // expensive computation here
        return input.contains("a");
    }

    private static String lazyMatch(Supplier<Boolean> a, Supplier<Boolean> b) {
        return a.get() && b.get() ? "match" : "incompatible!";
    }

Output:  
executing expensive computation...  
incompatible!  

Since we are now wrapping the expensiveComputeOperation with a Supplier, we now have the ability to only run that method when needed. Since a.get() in the lazyMatch method returns false, the b.get() will never execute.  This saves us from running the expensiveComputeOperation method when it's not needed.

## Introduction - Consumer
Consumer is a built-in functional interface that accepts one input and has no output. 

Functional Interface looks like:

    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

Usage of a Consumer is:

    Consumer<T> functionVariable = (T arg) -> lambda body that has no return (void)

- T is the input argument of a specified type

**Key Methods**
- accept - this triggers execute of the lambda body.


## Demo - Consumer
Let's take a look at a couple of examples of using Consumer. The following example will say Hello to a passed in String.

    Consumer<String> sayHello = (name) -> System.out.println("Hello " + name);
    sayHello.accept("John");

Output:  
Hello John

As you can see the lambda expression takes in 1 argument "name".  The lambda body return no output. It simply prints out "Hello John".  

The next example takes a list of names and iterates the list and prints out the names using a method reference.

    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Lisa", "Amanda", "Matt");
        Consumer<String> printNames = ConsumerExamples::printNames;
        names.stream().forEach(name -> printNames.accept(name));
    }

    private static void printNames(String name) {
        System.out.println(name);
    }

Output:  
John  
Lisa  
Amanda  
Matt  

If you look at the Consumer variable, you'll notice "ConsumerExamples::printNames".  This is what's called a method reference.  What this means is that if a method adheres to the contract of a functional interface, you can use the method instead of creating your own lambda expression.  If you look at the printNames method, it takes in only 1 input and it produces no output (void).

We could have also written the example to have the Consumer provide the lambda expression as follows, but I wanted to show you the method reference concept.

    public static void main(String[] args) {

        List<String> names = Arrays.asList("John", "Lisa", "Amanda", "Matt");

        Consumer<String> printNames = (name) -> System.out.println(name);//ConsumerExamples::printNames;

        names.stream().forEach(name -> printNames.accept(name));
    }

    private static void printNames(String name) {
        System.out.println(name);
    }


## Independent Practice
For the Independent Practice, we will make use of some of the built-in functional references that you have learned.

**Hint:**
You will need to use:
- Function
- BiFunction
- apply
- method references
- forEach

### Independent Practice Template

    package com.ga;

    import java.util.Arrays;
    import java.util.List;
    import java.util.stream.Stream;

    public class IndependentPractice {

        public static void main(String[] args) {

            List<Integer> list1 = Arrays.asList(1,3,4,5,6);
            List<Integer> list2 = Arrays.asList(1,3,4,5,6);

            //Write a BiFunction that compares to see if list1 and list2 are equal.

            //Rewrite the previous example to leverage a method reference on the Integer class

            List<Integer> numberList = Arrays.asList(4,9,16,25,36,49,64,81);

            //Write a Function that will return the square of a given number.  Then iterate
            //the numberList and print out the results.

        }
    }


## Conclusion - Review Recap
To recap, we've learned 
- How to create our own custom functional interfaces
- We've learned what a method reference is
- We've learned about some of the useful built-in functional interfaces

Some quiz questions for you are:
- If I needed a built-in functional interface that provided a method that could take 2 inputs and give me one output, what would I use?
- If I needed a built-in functional interface that provided a method that didn't have input but gave me one output, what would I use?

## References
[Baeldung Functional Interfaces](https://www.baeldung.com/java-8-functional-interfaces)

[Java Code Geeks - Consumer and Supplier](https://examples.javacodegeeks.com/core-java/java-8-consumer-supplier-example/)