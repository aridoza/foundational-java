
|                     Title                    |  Type  | Duration |  Creator |
|:-------------------------------------------:|:------:|:--------:|:--------:|
| Functional Programming - Optionals | lesson |   1:30   | Kyle Dye |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Functional Programming | Optionals

### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
* Understand the use case for optionals
* See how they are used with streams

### STUDENT PRE-WORK
*Before this lesson, you should already be able to:*
- Understand Lambda Expressions
- Understand Functional Interfaces
- Understand Streams and Collectors

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
| 5 min |     Introduction     |                         Intro to Optionals                        |
| 5 min |         Demo         |   How to create Optionals   |
| 10 min |     Introduction     |                          Checking for values in Optionals                         |
| 10 min |         Demo         |                      Checking for values in Optionals                     |
| 10 min |     Introduction     |                         Getting Optional values                        |
|  10 min |         Demo         |      Getting Optional values      |
| 10 min |     Introduction     |                                      How map, flatMap, and filter work with Optionals     
| 10 min | Demo                  |           How map, flatMap, and filter work with Optionals                      |
| 15 min | Independent Practice | Take what we've learned and complete a program using Optionals |
|  5 min |      Conclusion      |                                       Review/Recap                                       |

## Introduction: Intro to Optionals
If you worked with Java long enough, you will have eventually come across the dreaded "NullPointerException".  There's nothing worse than having your code completely stop execution because you expect a value to be present but it's not. To circumvent this, Java 8 released the Optional class.  

###What is an Optional?###
The Optional class is a single value container that may or may not contain a value. It also forces the developer to think about how to handle the situation when a value is not present.  Another benefit is that it reduces the amount of boilerplate code needed to test if a value is null.  Let's take a look at how you create an Optional in our first demo.

## Demo: How to create Optionals
The Optional class has 3 static methods used to create optionals, _.empty_, _.of_ and _.ofNullable_.

To create an empty optional, you'd do the following:

```java
Optional<String> emptyOptional = Optional.empty();
System.out.println(emptyOptional.isPresent());
```
Output:  
false

The _isPresent()_ method is used to check if a value exists.  It will be covered more in the next section.

You can also create an Optional from an existing value by using the _.of_ method.

```java
String name = "Mark";
Optional<String> optionalName = Optional.of(name);
System.out.println(optionalName.isPresent());
```
Output:  
true

The output shows that the value of "Mark" is present in the Optional.  But what happens if a null value is passed to the _.of_ method.  Let's see.

```java
String nullValue = null;
Optional.of(nullValue);
```
Output:  
Exception in thread "main" java.lang.NullPointerException

We got the dreaded NullPointerException.  This leads us to the last static method of the Optional class, _.ofNullable_.

```java
String stringValue = "myString";
Optional<String> optionalValue = Optional.ofNullable(stringValue);
System.out.println(optionalValue.isPresent());
```
Output:  
false

As you can see, we didn't get a NullPointerException, but instead we have an empty Optional.  So with that said, the preferred static method to use when creating an Optional class with a value would be _.ofNullable_.

## Introduction: Checking for values in Optionals 
The Optional class provides 3 useful methods to check the state of an Optional.  They are: 

- isPresent - returns true if the Optional has a value present
- isEmpty - returns true if the Optional does not have a value. Available starting Java 11
- ifPresent - a functional style replacement of checking if a value is empty.

Let's take a look at a demo to see how we'd use these methods.

## Demo: Checking for values in Optionals
You've seen the _isPresent_ method in the previous demo.

```java
String name = "Mark";
Optional<String> optionalName = Optional.of(name);
System.out.println(optionalName.isPresent());
```
Output:  
true

Starting with Java 11, the Optional class now has an "isEmpty" method that does the opposite of the _isPresent_ method.

```java
Optional<String> optionalValue = Optional.ofNullable(null);
System.out.println(optionalValue.isEmpty());
```
Output:  
true

The _ifPresent_ is a functional style way of wrapping a null check around logic.  Let's examine the following non-Optional way of checking for null.

```java
Integer age = 20;
if(age != null) {
    System.out.println("My age is " + age);
}
```
Output:  
My age is 20

This can be rewritten using the _ifPresent_ method:

```java
Optional<Integer> ageOptional = Optional.ofNullable(age);
ageOptional.ifPresent(myAge -> System.out.println("My age is " + myAge));
```
Output:  
My age is 20

Looking at the example,the ifPresent method uses a lambda expression.  It accepts a Consumer Function.  If you remember from the last module, a Consumer accepts 1 input and returns void.  By using _ifPresent_, it removes the need for the boilerplate "if(age != null)" check.

## Introduction: Getting Optional Values
The Optional class provides a _get_ method to get the value in the Optional. It also provides 3 orElse type methods to help in cases where a value is not present in the Optional.  They are:

- get - gets the value from the Optional.
- orElse - provides a default value if the Optional is empty.
- orElseGet - provides a default value via a Supplier functional interface if the Optional is empty.
- orElseThrow - provides a Supplier functional interface to return an exception if the Optional is empty.

Let's take a look at some examples.

## Demo: Getting Optional Values
The first example shows how to use the _get_ method.

```java
Optional<String> nameOptional = Optional.ofNullable("Amanda");
System.out.println(nameOptional.get());
```
Output:  
Amanda

The output is as expected. But what if the Optional has no value? Let's see an example.

```java
Optional<String> nameOptional = Optional.ofNullable(null);
System.out.println(nameOptional.get());
```
Output:  
Exception in thread "main" java.util.NoSuchElementException: No value present

You get an exception. This is where the orElse methods come to the rescue.

To demonstrate the _orElse_ methods, let's assume we have the following method:

```java
public static String getDefaultName() {
    System.out.println("In getDefaultName method");
    return "World";
}
```

We would use the _orElse_ method as follows:

```java
String nullValue = null;
String orElseName = Optional.ofNullable(nullValue).orElse(getDefaultName());
System.out.println(orElseName);
```
Output:  
World

Since the Optional is empty, the orElse is triggered.

Now let's see an example of the _orElseGet_ method:

```java
String nullValue = null;
String orElseGetName = Optional.ofNullable(nullValue).orElseGet(() -> getDefaultName());
System.out.println(orElseGetName);
```
Output:  
World

On the surface, it appears that both _orElse_ and _orElseGet_ do the same thing. But there is a subtle difference between the two that could mean a big deal in terms of performance.  Let's take a look.

Using the same method as before:

```java
public static String getDefaultName() {
    System.out.println("In getDefaultName method");
    return "World";
}
```

This time, let's run the same code as before. But let's populate the Optional with a value.

```java
System.out.println("orElse with populated Optional");
String orElseSideEffect = Optional.ofNullable("John").orElse(getDefaultName());
System.out.println(orElseSideEffect);
```

Before we look at the output, what do you expect to see?  If you expect that the "getDefaultName" method will NOT be called, you are 100% wrong.  Let's examine the output.

Output:  
orElse with populated Optional  
In getDefaultName method  
John  

Even though we populate the value in the Optional, the "getDefaultName" is executed. I can't explain why the Java people decided to code it that way, but this can be a big deal if the "getDefaultName" method has to hit a database. This results in unnecessary executions.  Now let's look at an example using _orElseGet_
.
```java
System.out.println("orElseGet with populated Optional");
String orElseGetName2 = Optional.ofNullable("John").orElseGet(() -> getDefaultName());
System.out.println(orElseGetName);
```
Output:  
orElseGet with populated Optional  
World  

As you can see, the "getDefaultName" method is not triggered as expected.  The reason being is that the Supplier lambda is using lazy evaluation.  It's able to determine that there is no need to run, so it doesn't trigger the "getDefaultName" method.  With that said, the preferred way to provide a default value is _orElseGet_.

Now let's take a look at an example of _orElseThrow_.

```java
String name = Optional.ofNullable(nullValue).orElseThrow(() -> new IllegalArgumentException("Name is missing"));
```
Output:  
Exception in thread "main" java.lang.IllegalArgumentException: Name is missing

As you can see, an exception is thrown if the Optional is empty.


## Introduction: How map, flatMap, and filter work with Optionals
The Optional class provides 3 useful stream-like operation methods to manipulate Optionals.  They are:

- filter - runs a test on the Optional value based on a given predicate. If the predicate returns true, the Optional is returned as-is.  If the predicate returns false, then an empty Optional is returned.
- map - runs a computation on the Optional's unwrapped value and wraps the result of the computation in an Optional.
- flatMap - similar to the map method.  The difference is that flatMap will first unwrap the Optional value before performing the computation.

The definitions may be a lot to digest, so let's take a look at some examples to help clear things up.

## Demo: How map, flatMap, and filter work with Optionals
For the first example. Let's take a look at _filter_.

```java
Optional<Integer> ageOptional = Optional.ofNullable(25);
boolean canBuyAlcohol = ageOptional.filter(age -> age >= 21).isPresent();
System.out.println(canBuyAlcohol);
```
Output:  
true  

As mentioned in the _filter_ definition earlier, if the predicate returns true, then the Optional is returned as-is. So in this case, the _isPresent_ method returns true.

Let's take a look at the opposite scenario where the age is less than 21.

```java
ageOptional = Optional.ofNullable(20);
canBuyAlcohol = ageOptional.filter(age -> age >= 21).isPresent();
System.out.println(canBuyAlcohol);
```
Output:  
false  

The _filter_ method returns an empty Optional which causes the _isPresent_ method to return false.

Let's take a look at the _map_ method.  Let's say we have the following Person class:

```java
public static class Person {

    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

We are going to run the same logic as before where we check the person's age to see if they are old enough to buy alcohol. But this time, we'll use the _map_ method.

```java
Person person = new Person(25);
boolean canAlsoBuyAlchohol = Optional.ofNullable(person)
        .map(Person::getAge)
        .filter(age -> age >= 21)
        .isPresent();
System.out.println(canAlsoBuyAlchohol);
```
Output:  
true  

There's a lot going on here, so let's dissect.  If you recall the _map_ definition.  The _map_ method runs a computation on a Optional's unwrapped value and returns an Optional containing the result. The _map_ method is accessing the Optional<Person>'s unwrapped "age" value in this example. The _filter_ method then receives an Optional<Integer> representing the age of 25. The _filter_ predicate is true, so it returns the Optional<Integer> as-is to the _isPresent_ method.

Now let's look at a _flatMap_ example that does the same logic and talk through it's details. Let's assume we have the following Person class:

```java
public static class PersonWithOptional {

    private Optional<Integer> age;

    public PersonWithOptional(Integer age) {
        this.age = Optional.ofNullable(age);
    }

    public Optional<Integer> getAge() {
        return age;
    }

    public void setAge(Optional<Integer> age) {
        this.age = age;
    }
}
```

To run the same logic as before with _flatMap_, we'd do the following:  

```java
PersonWithOptional personWithOptional = new PersonWithOptional(25);
boolean ableToBuyAlcohol = Optional.ofNullable(personWithOptional)
        .flatMap(PersonWithOptional::getAge)
        .filter(age -> age >= 21)
        .isPresent();
System.out.println(ableToBuyAlcohol);
```
Output:  
true  

Let's first examine the PersonWithOptional class.  The member variable _age_ is an Optional<Integer>.  In other words, the "age" is wrapped in an Optional. Looking at the _flatMap_ definition, _flatMap_ will first unwrap the value from the Optional before performing it's computation.  

Looking at the _flatMap_ line in the example, it is accessing the Optional<PersonWithOptional>'s unwrapped "age" value just like the _map_ method does. But unlike the _map_ method, it won't wrap the result in an Optional.  It just returns the unwrapped Optional<Integer> to the _filter_ method.  Had we used _map_, the return from _map_ would have been Optional<Optional<Integer>>.


## Independent Practice
For the Independent Practice, we will take the following template program below and complete 
the "TODO" portions that are in the comments.  To confirm that your results are correct,
iterate each list that you create and output the results.  

**Hint:**  
- You'll need to use _ofNullable_
- You'll need to leverage the _flatMap_ and _filter_ methods
- You'll also need to use _orElseGet_

### Independent Practice Template

```java
package com.ga;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class independentPractice {

    private static class Television {

        private Optional<String> skuId;
        private Optional<Boolean> hdmiEnabled;
        private Optional<Boolean> fourKEnabled;
        private Optional<Integer> price;

        private Television(String skuId, boolean hdmiEnabled, boolean fourKEnabled, Integer price) {
            this.skuId = Optional.ofNullable(skuId);
            this.hdmiEnabled = Optional.ofNullable(hdmiEnabled);
            this.fourKEnabled = Optional.ofNullable(fourKEnabled);
            this.price = Optional.ofNullable(price);
        }

        public Optional<String> getSkuId() {
            return skuId;
        }

        public void setSkuId(Optional<String> skuId) {
            this.skuId = skuId;
        }

        public Optional<Boolean> getHdmiEnabled() {
            return hdmiEnabled;
        }

        public void setHdmiEnabled(Optional<Boolean> hdmiEnabled) {
            this.hdmiEnabled = hdmiEnabled;
        }

        public Optional<Boolean> getFourKEnabled() {
            return fourKEnabled;
        }

        public void setFourKEnabled(Optional<Boolean> fourKEnabled) {
            this.fourKEnabled = fourKEnabled;
        }

        public Optional<Integer> getPrice() {
            return price;
        }

        public void setPrice(Optional<Integer> price) {
            this.price = price;
        }
    }

    public static void main(String[] args) {

        Optional<Television> televisionOptional1 =
                Optional.of(new Television("DDDD", true, true, null));

        //TODO #1: Write a lambda using orElseGet to default the price to 500 if the price is missing. Print the output
        //to confirm the price.

        //TODO #3: Call the method that you created in TODO #2 for the following Television objects and print out the
        //output.
        Television television1 =
                new Television("AAAA", true, true, 1500);

        Television television2 =
                new Television("BBBB", true, false, 1000);

        Television television3 =
                new Television("CCCC", false, false, 500);
    }

    //TODO #2 Write a method that takes in a Television object and returns true if the price is greater than $999.
}

```

The completed example can be found in the repo in the file named "IndependentPracticeCompleted.java".


## Conclusion - Review/Recap
To recap, we've learned

- What Optionals are used for
- How to create Optionals
- How to get values from Optionals
- How to use Optional stream operations such as _filter_, _map_, and _flatMap_

####Quiz Questions####
With a partner, draft a one sentence answer to each of these questions:
- Why is _Optional.ofNullable_ preferred over using __Optional.of_?
- Which one of the following methods is the preferred way of getting values from an Optional? _orElseGet_ over _orElse_ and _get_?
- Using the answer from the previous question, tell me why it's preferred?

## References
- [Baeldung Optionals](https://www.baeldung.com/java-optional)
- [Tired of NullPointerExceptions?](https://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html)
