# Interfaces

Students will be able to define an interface in Java and understand when to use one. Additionally students will be able to differentiate between abstract classes and interfaces and understand when to use one over the other. 

- Topics:
    - Defining and Implementing an Interface
    - Write a Class that uses an Interface
- Activity: to be added.
- Sizing: 2 (small)

## Introduction

In Java an interface defines an API, which is essentially a contract that a class will contain the methods defined in the interface.

> Note: We're using API as "application program interface", in that we know certain methods we can use to access something, in this case a Java class. You may have more coloquially heard the term "API" used to mean a web API, usually meant to access some sort of data. In the generic sense, application program interface describes that process as well.

An interface is not a class; it _cannot be instantiated_. It does not inherit from `Object`. Again, it is just a contract that declares to the compiler that whatever instance is assigned to it, will contain the methods in the interface.

An interface is similar to an abstract class, in that its methods are declared but not defined. 

> Aside: Technically, since Java 8 you can also provide implementations in interface methods, and we will learn more detail about that later when we cover default methods in interfaces. 

### Defining an Interface

All this will become clearer when we get into the sample code, but first let's see how to define an interface:

```java
public interface Automobile {
    // Contents
}
```

That defines the shell of an interface. The declaration of an interface is really very similar to a class, except that it uses the word _interface_ in place of _class_.
 
Like a class, an inteface can be public or default (package-private) visibility.

Let's add some functionality to our `Automobile` interface:

```java
public interface Automobile {
    int getYear();
    String getMake();
    String getModel();
    void startEngine();
}
```
 
Our Automobile is now contracted to provide a year, make, and model, and some functionality to start the engine. Notice how these methods are defined exactly like abstract methods in abstract classes.

Now I can have some code that says something like:

```java
Automobile auto = getAutomobileById("12357"); // get an Automobile from our database
String make = auto.getMake();
String model = auto.getModel();
int year = auto.getYear();
auto.startEngine();
```

Note that we did not specify a visibility for the interface methods. That is because all interface methods are always public, and we should also mention they are always non-static.

### Implementing Interfaces

To implement an interface means that you are committing to fulfill the interface contract for the class that implements it. 

The syntax is as follows:

```java
class MyClass implements MyInterface {
    
}
```

Where _MyClass_ is the class you are defining, and _MyInterface_ is the interface you are promising to enforce. In a more concrete example, we might take our `Automobile` interface and create a `HondaAccord` class that looks like this:

```java
class HondaAccord implements Automobile {
    // Instance variable!
    public int year; 

    // Constructor
    public HondaAccord(year) {
        this.year = year;
    }

    // Methods that the interface needs
    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public String getMake() {
        return "Honda";
    }

    @Override
    public String getModel() {
        return "Accord";
    }

    @Override
    public void startEngine() {
        System.out.println("Vroom vroom!");
    }
}
```

## Why Interfaces?

**Why is this important?**

Think about it - let's say we are an auto manufacturing company, and last year we bought from you a large library of Java code for managing cars. Let's say we are coming out with a new kind of car, a new model called Tesla Satellite, and I want to use the functionality in your Automobile library.

All we have to do is implement your Automobile interface, and we can use your existing library to manage our new car that did not even exist when your library was written. It was one thing to write a `HondaAccord` class since we already know everything about Hondas, but now we're really able to realize the usefulness of an interface.

By _implementing_ your interface, the Java compiler will ensure that our new `TeslaSatellite` class has implemented all of the methods in our Automobile contract, err, interface, thus ensuring that it is usable by our libraries.

![](resources/unimplemented-interface.png)

So let's implement those methods and try again

![](resources/implemented-interface.png)
 
> Tip: As we saw before, the @Override annotation is optional, but desirable. 

### Fields in Interfaces

Surprisingly interfaces may contain field variables, but these fields _must_ be assigned values in the interface, they are static. Remember that static means the field can be accessed from the interface name, as in the following example, or from any class that implements that interface. And these variables are _final_, they may not be changed, as indicated by the red underline in IntelliJ.

![](resources/reassign-interface-variable.png)

### Interfaces Extending Interfaces

Just like classes can extend classes, interfaces can extend other interfaces, and they inherit all of the methods of the base interface. This is useful for complex systems we might find in real life.

### Interfaces Extending Multiple Interfaces

One important difference between classes and interfaces, is that where a class can only extend one class, an interface can extend many interfaces, and then it would inherit all of the methods (and fields) from all of the parent interfaces. 

### Classes Implementing Multiple Interfaces

Think back to our `Automobile` interface. Imagine we also have a `TowVehicle` interface that deals with towing capability, carrying capacity, and fuel type. 

```java
public interface TowVehicle {
    int getCarryingCapacity();
    int getTowingCapacity();
    String getFuelType();
}
```

We might want to create a `DodgeRam` class that implements _both_ interfaces. That would look like this:

```java
class DodgeRam implements Automobile, TowVehicle {
    // Methods that the Automobile interface needs
    @Override
    public int getYear() {
        return 2019;
    }

    @Override
    public String getMake() {
        return "Dodge";
    }

    @Override
    public String getModel() {
        return "Ram";
    }

    @Override
    public void startEngine() {
        System.out.println("VROOM!");
    }

    // Methods that the TowVehicle class needs
    @Override
    int getCarryingCapacity() {
        return 2000;
    }

    @Override
    int getTowingCapacity() {
        return 13000;
    }

    @Override
    String getFuelType() {
        return "Diesel";
    }
}
```

Take a second to consider the `DodgeRam` class. What methods are legal to run on it compared to the `HondaAccord` and the `TeslaSatellite`?

While a class cannot extend multiple classes, it is perfectly legal to _implement_ multiple interfaces. To do so, include a comma-separated list of interfaces to implement after the _implements_ keywords.

#### Another Example
 
Let's think through another example together as a class. Let's use a `Person` class that implements 3 different interfaces for various qualities like `Sentient`, `Sapient`, `Biped`, etc.
 
<!-- 
COMMENT (Brandi): What does this note mean?

NOTE TO INSTRUCTOR: In the exercises for this less, we will ask the class to create these interfaces. See code definitions in the src directory for this lesson. 
-->
 
Then we could have:
 
```java 
class Person implements Satient, Sapient, Biped { 
    // Contents
} 
```

Then it would be perfectly legal to say

```java
Person person = new Person();
Sentient sentient = person;
Sapient sapient = person;
Biped biped = person;
```

Each of the variables would contain the same instance, however you would only be allowed to call the methods exposed by the interface, depending on the variable.
So the following are all legal (assuming the interfaces define those methods):

```java
sentient.feel();
sapient.think();
biped.walk();
```

However the following is illegal

``` java
sentient.walk();
```

Since even though the instance is a person, the interface is a `Sentient`, and so you can only call methods from the `Sentient` interface.

This feature provides a way to have a sort of multiple inheritance in Java that is otherwise not supported.

### Interfaces vs Abstract Classes 

What is the difference between an interface and an abstract class? The main difference is that a class can only extend one class, abstract or not, whereas it can _implement_ many interfaces. This can be useful in the use cases above, where we needed to refer to a single instance under different APIs.

So why use an abstract class ever? The short answer is that an abstract class allows you to provide *some* implementation, which is useful when you want to define some methods but not others, as we saw in our earlier example with the Monitors.

### Default Methods in Interfaces

In fact, that difference has started to blur since Java 8, when the ability to define _default methods_ was added to interfaces.

A default method essentially provides some functionality that we want to add to all classes that implement this interface. Such methods become public, non-static members of all implementing classes. And like any public method, they may be overridden by implementing class.

For example:

```java
public interface Sapient {
    void think();
    default void speak() {
        System.out.println("I think therefore I am!");
    }
}
```

Now our Person class (that implements Sapient) gets the _speak_ method:
```java
new Person().speak(); // displays I think therefore I am!
```

### Exercise - Creating and implementing interfaces

Let's create all of the interfaces we discussed in this lesson, and create a class that implements all of these. Then let's call all of the methods on the class.

|Interface|Methods| 
|---|---|
|Sapient| void think(); |
|Sentient| void feel(); |
|Biped| void walk(); |

Then add a default method to the `Sapient` method called void speak().

Finally, create a `Person` class that implements all of these interfaces. For the implementation, do `System.out.println("some appropriate message")`

Then assign it to variables of each of the types, `Sapient`, `Sentient`, `Biped`, and `Person`, and see what methods you can call on each. 

> Tip: Want to use IntelliJ ike a pro? Place the variable name on a new line, followed by a dot. Then hit Ctrl-Space and IntelliJ will show you the methods you can call!

Solution: (See src code and graphic)

![](resources/intellij-ctrl-dot.png)

Note that in this method display, the methods implemented directly are **bolded**, whereas the inherited methods (like `clone` and `equals`, which are inherited from `Object`) are just plain, not bolded.

## Summary

Remember that interfaces:

* Cannot be instatiated
* Cannot be static
* Cannot implement methods

You should use interfaces over abstract classes when:

* You're defining 100% abstract methods
* You need future developers to follow a certain pattern (this is our contract!)
* You need to use more than one interface (remember a class can only extend one class!)

You should use an abstract class when:

* You need both static and non-static methods
* You need both abstract and non-abstract methods
* You don't want all your fields to be "final"

The difference is somewhat subtle and both interfaces and abstract classes are used for abstraction, but the key takeaway for knowing which one to use is whether the classes that would implement or extend the interface or abstract class will *share lines of code*. 

This may require some thinking about your particular task or situation. There's no one right answer for each and every situation. It's okay though. We trust you!

![](https://media.giphy.com/media/vpUbmR24hx6mc/giphy.gif)

## Extra Resources

Need some more examples about abstract classes and interfaces? Check out this [GeeksforGeeks article](https://www.geeksforgeeks.org/difference-between-abstract-class-and-interface-in-java/).
