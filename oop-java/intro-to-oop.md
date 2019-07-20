| Title | Type | Duration | Creator |
| --- | -- | -- | --- |
| Intro to Object-Oriented Programming | Lesson | 0:30 | Victor Grazi, NYC |

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Intro to Object-Oriented Programming

### LEARNING OBJECTIVES

*After this lesson, you will be able to:*
- Define the four principles of OOP
- Explain how classes and objects factor into OOP

### LESSON GUIDE

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 5 min  | Opening  | Discuss lesson objectives |
| 10 min  | Introduction  | 4 Principles of OOP |
| 10 min  | Demo  | Classes |
| 5 min  | Conclusion  | Review / Recap |

## Opening (5 mins)

*Object-Oriented Programming* (OOP) is a concept that was introduced around 1962 with the advent of the Simula programming language, and became popular some 25 years later in C++.

Where earlier languages were _procedural_ in nature, object-oriented programming introduced the concept of _objects_, which were compartments of data and functionality that could easily retain and modify their own instance data. 

-----

## The Four Principles of OOP (10 mins)

There are four pillars of object-oriented programming, and these are:

* Abstraction
* Polymorphism
* Inheritance
* Encapsulation

> Why are they ordered like that? Because it spells "A PIE". Yum! If you like pie and acronyms, this just might help you remember these four concepts!

### Abstraction

The idea behind abstraction is that the average person doesn't need to know the inner workings of something in order to use it successfully. For example, you don't have to be a mechanic in order to drive a car.

### Encapsulation

Encapsulation is related to abstraction, but goes a step further. Not only does the average user not need to have access to the inner workings on something in order to use it, but if they do have that access it may actually be harmful!

You could technically start your car with a screwdriver or with electricity directly, but you really shouldn't. You might hurt yourself or damage your car. Bad idea! Likewise your users don't always need direct access to sensitive parts of your code. 

![](https://res.cloudinary.com/briezh/image/upload/v1560812857/bike-brakes_pvfblg.jpg)

> Image credit to [this article](https://dev.to/charanrajgolla/beginners-guide---object-oriented-programming), which we highly recommend reading if this course feels a little too fast-paced!

### Inheritance

Inheritance allows classes to "inherit" code from one another. The base class is called the "parent" and the inheriter/beneficiary class is called the "child" class. It's just one more way to keep our code shorter and simpler.

### Polymorphism

Polymorphism simply means "many forms". It refers to the fact that a method could have multiple implementations, either differing between an parent and a child class (method overriding) or depending on the type and number of arguments (method overloading).

----

## Classes and Modifiers (10 mins)

Before object-oriented programming came along, languages like the C programming language sported a feature called `structs` - a way of defining a common format to allocate blocks of consistent data types.
 
For example you could say:

```java
struct Person { 
    double birthdate;
    int id; 
    char name[50];
} 
```

That would basically allow you to carve out memory for all of the structure's variables (e.g. birthdate, id, name) simply by assigning a new struct!

Now, once we have data as a struct, why not add _functionality_ to that structure (e.g., derive a person's age given today's date), and include that inside the struct itself. Well guess what, that is exactly what a class is, a data structure with functions (correction  - "methods"!)

> In our example of deriving a person's age from their birthdate, we might make a method called `getCurrentAge()` or something similar.

### Defining a class

We've already seen several examples of classes. They all conform to the common pattern:

```java
public class MyClass {
    // guts of the class
}
```

In this example, _public_ is the visibility. Top-level classes can only be public or default (unspecified). Top-level means that they are not inner classes, and we will define inner classes later in this lesson.

_public_ classes (lower case p) can be referenced by any other class, and this is the most common class visibility. A file may contain _no more than one_ public class. The rule is that every top level public Java class must reside in a file that exactly matches the file name (minus the `.java` file type, of course). 

#### Default Classes

Default classes are classes that don't specify an access modifier, e.g.,:

```java
class MyDefaultClass {}
```

Default classes can be accessed in the current file, as well as by other classes in the same package. For this reason, the default visibility level is called "package" or "package-private".

Package and public are the only access levels available for top-level classes, but as we will see, inner classes can also be protected or private.

#### Access levels

The access levels, going from most restrictive to least restrictive are:

```
private --> package --> protected --> public
```

"private", "protected", and "public" are all *access modifiers*, meaning that you specifically add them in so that you can change the access (or protection) level away from the default we described above.

This nifty table from [Oracle's docs on access control](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html) shows what's unique about each level.

![](https://res.cloudinary.com/briezh/image/upload/v1560810976/Screen_Shot_2019-06-17_at_3.35.38_PM_fq9ffm.png)

----

## Conclusion (5 mins)

Object-oriented programming is the practice of using classes and objects to make code that is modular (encapsulated) and often reusable. A class can be thought of as a blueprint or a pattern for a dress. There is one blueprint, but with it you can build many buildings. There is one dress pattern, but with it you can manufacture many dresses.

Check for understanding - can you define each of the four OOP pillars?
- Abstraction
- Polymorphism
- Inheritance
- Encapsulation

## Additional Resources

* [Oracle Docs: Access Control](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html)
* [OOP for Beginners (article)](https://dev.to/charanrajgolla/beginners-guide---object-oriented-programming)
* [4 Principles of OOP (article)](https://medium.com/@cancerian0684/what-are-four-basic-principles-of-object-oriented-programming-645af8b43727)


