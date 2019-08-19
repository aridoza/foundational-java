|                     Title                    |  Type  | Duration |  Creator |
|:-------------------------------------------:|:------:|:--------:|:--------:|
| Functional Programming - Functional Interfaces | lesson |   1:25   | Kyle Dye |

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Functional Interfaces

### Learning Objectives

*After this lesson, students will be able to:*

- Use functional interfaces to simplify lambda expressions.
- Use built-in functional interfaces, including functions, suppliers, and consumers.
- Decide when to use a built-in or custom functional interface.

### Lesson Guide

| TIMING |         TYPE         |                                           TOPIC                                          |
|:------:|:--------------------|:----------------------------------------------------------------------------------------|
| 10 min |        Opening       |                                 Introduction to Functional Interfaces                                |
| 15 min |   Guided Practice    |                         Functional Interfaces Deep Dive                        |     |
| 10 min |   Guided Practice    |                         `Function`                        |
| 10 min |    Guided Practice   |                       `Supplier`                     |
| 10 min |    Guided Practice   |      `Consumer`      |
| 20 min | Independent Practice | Complete a Program Using `Function`s, `Supplier`s, `Consumer`s, `Operator`s, and `Predicate`s |
| 10 min |      Conclusion      |                                       Review/Recap                                       |

## Opening (10 min)

Let's dive deeper into functional interfaces, which are the heart and soul of lambda expressions. 

To recap, a lambda expression is a concise syntax for expressing an anonymous method. Remember that the syntax for a lambda expression is generally as follows:

    (arg1, arg2,...) -> lambda expression body

A **functional interface** is nothing more than an interface with exactly one abstract method. I'll let you in on a secret: A lambda expression is syntactic "sugar" for defining the implementation of a functional interface, supplying the arguments and implementation while skipping all of the boilerplate code of having to say `public void blah(args) { implementation}`. It's pretty sweet! So, when you provide a lambda expression somewhere, what you're really doing is subclassing that abstract class and providing the implementation for that one abstract method. 

This is important, so let that sink in. You should find the rest of this section straightforward, so let's discuss it a bit.

- How does Java know which functional interface you are overriding if you don't specify it? 
        - Answer: The name of the functional interface is specified in the method signature, so Java uses that!
        
- How does Java know which method to override if you are not supplying a method name? 
        - Answer: Because there is exactly one abstract method in the functional interface, that is the one Java overrides! 

Let's start by learning how to create your own functional interfaces.

-----

## Functional Interfaces Deep Dive (15 min)

**What is a functional interface?**  

As we said, a functional interface is an interface that contains exactly one abstract method. When writing these interfaces, you should annotate them with the optional `@FunctionalInterface` annotation. If you don't, the code will still work. But adding the annotation is defensive and signals the compiler to fail the build, in case anyone accidentally adds new abstract methods to the interface in the future.

For example:

```java
@FunctionalInterface
public interface MyFunctionalInterface {
    ...
}
```

**Why use a functional interface?** 

Functional interfaces make lambda expressions possible. Using a functional interface lets us leverage lambda expressions to represent them instead of having to explicitly subclass them. This is considerably less verbose than creating anonymous inner classes. Let's walk through the demo to see some examples.

One other major difference (that you may never notice) is that the compiler assigns a funny name for every anonymous inner class, whereas lambdas do not. If you look at your generated classes, you can recognize these files because they have a `$` sign and a number following the parent class name (for example: `MyClass$21.class`).

**What is a method reference?** 

As if lambdas were not concise enough, there are times when you can simplify them even further! When the body of a lambda expression does nothing but call a method using the input parameter as an argument, you can refer to the existing method by name, eliminate the call parameters and the arrow, use the special `::` notation, and the input is understood. 

For example, the following lambda calls one method, `System.out.println()`, on the input parameter:

`param -> System.out.println(param)`

This can be replaced with a method reference, as follows (notice the use of the `::` to represent the method reference syntax): 

`System.out::println`

Using this syntax, you can see how we eliminated the input parameter, the arrow, and the method argument simply by using the method name.

### Demo: Functional Interfaces

For this demo, we'll use a few different functional interface examples so that you can get comfortable with them. 

### Example 1: A Functional Interface That Has No Input and Returns `void`

Given the following functional interface, we can use it to print `Hello World!`:

    @FunctionalInterface
    public interface Greeter {
        public void greet();
    }

    //Use the Greeter to say hello:
    Greeter greetTheWorld = () -> System.out.println("Hello World!");
    greetTheWorld.greet();
    
Output:  
    `Hello World!`

Take a look at the interface. It has only one abstract method, which meets the criteria for a functional interface. You'll notice that it's also annotated with the optional `@FunctionalInteface` annotation.

To use the functional interface, we created the `greetTheWorld` variable of type `Greeter` and assigned it a lambda expression that takes no arguments, prints out `Hello World!`, and returns `void`. Guess what? That is exactly the signature of the abstract `greet()` method in our functional interface; it doesn't have parameters and returns `void`.

The last thing we did was call the `greet()` method for it to execute.

### Example 2: A Functional Interface That Takes In One Input and Returns `void`

Given the following functional interface, it will greet a person based on a given name:

    @FunctionalInterface
    public interface GreetByName {
        public void greet(String name);
    }

    GreetByName greetSomeone =
            (name -> System.out.println("Hello " + name + "!  How are you doing today?"));
            
    greetSomeone.greet("Mark");

Output:  
    `Hello Mark! How are you doing today?`

If you look at the `greet()` method of the `GreetByName` functional interface, you'll notice that it takes one string parameter called `name`. This parameter will become the input parameter to the lambda expression that's used further down in the example. In this case, the lambda input parameter is called `name`. The parameter names don't have to match, but it's good practice to name them according to what they are.

### Example 3: A Functional Interface That Has One Input and Produces One Output

The following example uses a functional interface to take an integer input parameter and output the square of that given input:

    @FunctionalInterface
    public interface SquareMe {
        public Integer square(Integer input);
    }

    SquareMe squareMe = (number) -> number * number;
    System.out.println("The square of 11 is " + squareMe.square(11));

Output:  
    `The square of 11 is 121`

Similar to Example 2, the functional interface method `square()` takes in one input called `input`. This time around, however, the `square()` method returns an integer. If you look at the lambda expression, the input parameter `number` matches up to the `input` parameter of the `square()` method. The body of the lambda expression will take the input and multiply it by itself.

To get the output, you have to call the `square()` method on the `squareMe` variable. For example:

    squareMe.square(11)

----

## `Function` (10 min)

Java was nice enough to provide us with a robust supply of built-in functional interfaces that should meet the majority of our needs. In the next few sections, we'll discuss some of these built-in functional interfaces, starting with `Function`.  

**`Function`** is a built-in functional interface that has one abstract method, `apply()`, which accepts one input and produces one output. 

The functional interface for the `Function` interface looks like:

    @FunctionalInterface
    public interface Function<T, R> {

        R apply(T t);

        ...
    }

Usage of a function is:

    Function<T, R> functionVariable = (T arg) -> lambda body that returns the R type.

- `T` is the input argument of a type specified.
- `R` is the return of the function of a specified type. Let's check out the demo to see it in action.

**Key abstract method**: `apply()`. This triggers the function to execute the lambda expression body.

> **Knowledge Check**: With a partner, compare and contrast the `SquareMe` functional interface in Example 3 with the functional interface here. The `SquareMe`'s abstract method is called `square()`, whereas the `Function` abstract method is called `apply()`. Even though the method names are different, the `SquareMe` interface could be replaced with a built-in function and produce the same results.

### Demo: `Function`

Let's take a look at a couple of examples of how to use `Function`.

The first will take in a string that represents a name, and the output will be a greeting:

    // Create a greeting function.
    Function<String, String> greetingFunction = s -> "Good Morning " + s + "!";
    String greeting = greetingFunction.apply("Kyle");
    System.out.println(greeting);

Output:  
    `Good Morning Kyle!`

Let's talk about what's going on with this example. 

Take a look at the first line:

    Function<String, String> greetingFunction = s -> "Good Morning " + s + "!";

`Function<String, String>` is how we express our function using generics. The first string represents the type of the input. The second string represents the type of the output.

Looking at the lambda expression, the `s` input parameter in our example will be of type `String`, which coincides with the first `String` of the function generic. The lambda body will also return a `String`, which coincides with the second `String` of the function generic.

Let's look at the second line:  

    String greeting = greetingFunction.apply("Kyle");

The `apply()` method is what executes the defined lambda expression and returns the result to the `greeting` variable.

### Why Is This Useful?

Now we know that Java supplies all of these pre-built functional interfaces and we see that they are kind of elegant, but how do they add value?

Well, the good news is, you can start using them immediately, without even knowing their names. How? Well, by supplying lambdas! Because lambdas don't care about the name of the functional interface, you can use them when the semantics are clear, even if you don't know the function name at all!

For example, the `Stream` class has a method called `map()`:

Specifically:

    <R> Stream<R> map(Function<? super T, ? extends R> mapper);

This cryptic declaration actually means something very simple: That `Stream`s have a `map()` method that accepts a `Function` parameter that returns another stream. This is why we can say something like:

        List<String> concatenatedList =
            stringList.stream()
                .map(s -> "Hello, " + s)
                .collect(Collectors.toList());

The lambda in the `.map()` method is: 

    s->"Hello, " + s
    
The `s` variable is the input parameter in the `Function.apply()` method, and the lambda body `"Hello, " + s` is the output result of the `Function.apply()` method. So, you called the `map()` method without ever having to know you were supplying an implementation for the `Function.apply()` method.

------

## `Supplier` (10 min)

`Supplier` is a built-in functional interface that accepts no inputs and produces a generic output. 

The functional interface for the `Supplier` interface looks like:

    @FunctionalInterface
    public interface Supplier<T> {

        T get();
    }

Usage of a `Supplier` is:
 
    Supplier<T> functionVariable = () -> lambda body that returns the T type.

- `T` is the return of the function of a specified type.

**Key Methods**: `get()`. This will trigger execution of the lambda body.

**A Good `Supplier` Strategy** 

`Supplier`s are especially valuable in situations that may be expensive to run, even though they might not even be used! `Supplier`s leverage lazy evaluation, meaning they won't execute until actually needed. You can use that to your advantage to avoid the expensive method... unless and until it's absolutely needed.

### Demo: `Supplier`

The following demo shows an example of using a `Supplier` to save you from running an expensive operation when it isn't necessary.  

Let's say you have the following:

    public static void main(String[] args) {

        // Let's see an example without the Supplier when calling an expensive operation.
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
    `executing expensive computation...  
    executing expensive computation...  
    incompatible!`  

As you can see, the `expensiveComputeOperation()` is called twice. Even though the `bb` parameter returns `false`, the `aa` operation still runs when it doesn't need to. Now, let's look at how you could avoid that by using `Supplier`s:

    public static void main(String[] args) {

        // Let's use a Supplier.
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
    `executing expensive computation...  
    incompatible!`  

Because we are now wrapping the `expensiveComputeOperation()` with a `Supplier`, we have the ability to run that method only if and when it is needed. Because `a.get()` in the `lazyMatch()` method returns `false`, the `b.get()` will never execute. This saves us from running the `expensiveComputeOperation()` method when it isn't needed.

-------

## `Consumer` (10 min)

`Consumer` is a built-in functional interface that accepts one input and has no output. 

The functional interface for the `Consumer` interface looks like:

    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

Usage of a `Consumer` is:

    Consumer<T> functionVariable = (T arg) -> lambda body that has no return (void).

- `T` is the input argument of a specified type.

**Key Methods**: `accept()`. This triggers execution of the lambda body.

### Demo: `Consumer`

Let's take a look at a couple of examples of using `Consumer`. The following example will say `Hello` to a passed-in `String`:

    Consumer<String> sayHello = (name) -> System.out.println("Hello " + name);
    sayHello.accept("John");

Output:  
    `Hello John`

As you can see, the lambda expression takes in one argument (`name`). The lambda body returns no output; it simply prints out `Hello John`.  

The next example takes a list of names, iterates the list, and prints out the names using a method reference:

    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Lisa", "Amanda", "Matt");
        Consumer<String> printNames = ConsumerExamples::printNames;
        names.stream().forEach(name -> printNames.accept(name));
    }

    private static void printNames(String name) {
        System.out.println(name);
    }

Output:  
    `John  
    Lisa  
    Amanda  
    Matt`  

If you look at the consumer variable, you'll notice `ConsumerExamples::printNames`. This is what's called a **method reference**. This means that, if a method adheres to the contract of a functional interface, you can use the method instead of creating your own lambda expression. For example, if you look at the `printNames()` method, it takes in only one input and produces no output (`void`).

------

## Independent Practice (20 min)

For the independent practice, we'll make use of some of the built-in functional references you've learned.

**Hint:** 

You will need to use:
- `Function`
- `apply()`
- Method references
- `forEach()`

### Independent Practice Template

    package com.ga;

    import java.util.Arrays;
    import java.util.List;
    import java.util.stream.Stream;

    public class IndependentPractice {

        public static void main(String[] args) {

            List<Integer> list1 = Arrays.asList(1,3,4,5,6);
            List<Integer> list2 = Arrays.asList(1,3,4,5,6);

            // Write a BiFunction that compares to see if list1 and list2 are equal.

            // Rewrite the previous example to leverage a method reference on the Integer class.

            List<Integer> numberList = Arrays.asList(4,9,16,25,36,49,64,81);

            // Write a Function that will return the square of a given number. Then, iterate
            //the numberList and print out the results.

        }
    }

------

## Conclusion (10 min)

To recap, we've learned: 

- How to create our own custom functional interfaces.
- What a method reference is.
- Some useful built-in functional interfaces.

As we wrap up, discuss the following with a partner:
- If I needed a built-in functional interface that provided a method that could take two inputs and give one output, what would I use?
- If I needed a built-in functional interface that provided a method that didn't have input but gave one output, what would I use?

#### References

- [Baeldung Functional Interfaces](https://www.baeldung.com/java-8-functional-interfaces)
- [Java Code Geeks — `Consumer` and `Supplier`](https://examples.javacodegeeks.com/core-java/java-8-consumer-supplier-example/)
