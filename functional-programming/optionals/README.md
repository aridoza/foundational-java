|                     Title                    |  Type  | Duration |  Creator |
|:-------------------------------------------:|:------:|:--------:|:--------:|
| Functional Programming - Optionals | lesson |   1:30   | Kyle Dye |

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Optionals

### LEARNING OBJECTIVES

*After this lesson, students will:*

* Explain why optionals are used.
* Use optionals in conjunction with streams.

### LESSON GUIDE

| TIMING |         TYPE         |                                           TOPIC                                          |
|:------:|:--------------------:|:----------------------------------------------------------------------------------------:|
| 5 min  |     Introduction     |                         Intro to Optionals                        |
| 5 min  |         Demo         |   How to Create Optionals   |
| 15 min |  Guided Practice     |                          Checking for Values in Optionals                         |
| 20 min |  Guided Practice     |                          Getting Optional Values                        |
| 20 min |  Guided Practice     |                          How Map, flatMap, and Filter Work with Optionals     |
| 15 min | Independent Practice | Take What We've Learned and Complete a Program Using Optionals |
| 10 min |      Conclusion      |                                       Review/Recap                                       |

## Introduction (5 min)

If you've worked with Java long enough, you've eventually come across the dreaded "NullPointerException." There's nothing worse than having your code completely stop execution because you expect a variable to contain a value, when actually none is present. To circumvent this, Java 8 released the optional class.  

**What is an Optional?**

The optional class is a single-value container that may or may not contain a value. Being structured as such, it forces the developer to think about how to handle the situation when a value is not present, and it reduces the amount of boilerplate code needed to test for null values.

-----

## How to Create Optionals (5 min)

The optional class has three static methods for creation:

* _.empty_ 
* _.of_
* _.ofNullable_.

To create an empty optional (i.e., one that contains no value), you can do the following:

```java
Optional<String> emptyOptional = Optional.empty();
System.out.println(emptyOptional.isPresent());
```

Output:  
    false

The _isPresent()_ method is used to check if a value exists. It will be covered more in the next section.

You can also create an optional from an existing value by using the _.of_ method:

```java
String name = "Mark";
Optional<String> optionalName = Optional.of(name);
System.out.println(optionalName.isPresent());
System.out.println(optionalName.get());

```

Output:  
    true
    Mark

The output shows that the value of "Mark" is present in the optional. But what happens if a null value is passed to the _.of_ method? Let's see:

```java
String nullValue = null;
Optional.of(nullValue);
```

Output:  
    Exception in thread "main" `java.lang.NullPointerException`.

We got the dreaded NullPointerException. This leads us to the last static method of the optional class, _.ofNullable_:

```java
String stringValue = "myString";
Optional<String> optionalValue = Optional.ofNullable(stringValue);
System.out.println(optionalValue.isPresent());
```

Output:  
    false

As you can see, we didn't get a NullPointerException, but instead we have an empty optional. With that said, the preferred static method to use when creating an optional class with a value that could be null would be _.ofNullable_.

-----

## Checking for Values in Optionals (15 min)

The optional class provides three useful methods for checking the state of an optional: 

- _isPresent_ - Returns "true" if the optional has a value present.
- _isEmpty_ - Returns "true" if the optional does not have a value (this is available starting with Java 11).
- _ifPresent_ - A functional style replacement: Checks if a value is empty, then accepts a lambda representing a consumer instance.

Let's take a look at a demo to see how we'd use these methods.

### Demo

You've seen the _isPresent_ method in the previous demo:

```java
String name = "Mark";
Optional<String> optionalName = Optional.of(name);
System.out.println(optionalName.isPresent());
```

Output:  
    true

The _ifPresent_ is a functional style way of wrapping a null check around logic. 

Let's examine the following non-optional way of checking for null:

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

Looking at the example, the _ifPresent_ method uses a lambda expression to represent a consumer function. If you remember from the last module, a consumer accepts 1 input and returns void. By using _ifPresent_, it removes the need for the boilerplate "if(age != null)" check.

Starting with Java 11, the optional class now has an _isEmpty_ method that does the opposite of the _isPresent_ method.

```java
Optional<String> optionalValue = Optional.ofNullable(null);
System.out.println(optionalValue.isEmpty());
```

Output:  
    true

-----

## Getting Optional Values (20 min)

The optional class provides a _get_ method to get the value in the optional. It also provides some _orElse_-type methods to help in cases where a value is not present in the optional:

- _get_ - Gets the value from the optional.
- _orElse_ - Provides a default value if the optional is empty.
- _orElseGet_ - Provides a default value via a supplier functional interface if the optional is empty.
- _orElseThrow_ - Provides a supplier functional interface to return an exception if the optional is empty.

Let's take a look at some examples.

### Demo

The first example shows how to use the _get_ method:

```java
Optional<String> nameOptional = Optional.ofNullable("Amanda");
System.out.println(nameOptional.get());
```

Output:  
    Amanda

The output is as expected. But what if the optional has no value? Let's see an example:

```java
Optional<String> nameOptional = Optional.ofNullable(null);
System.out.println(nameOptional.get());
```

Output:  
    Exception in thread "main" `java.util.NoSuchElementException`: No value present

You get an exception. This is where the _orElse_ methods come to the rescue!

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

Since the optional is empty, the _orElse_ is triggered.

Now let's see an example of the _orElseGet_ method:

```java
String nullValue = null;
String orElseGetName = Optional.ofNullable(nullValue).orElseGet(() -> getDefaultName());
System.out.println(orElseGetName);

```
Output:  
    World

On the surface, it appears that both _orElse_ and _orElseGet_ do the same thing. But there is a subtle difference between the two that could mean a big deal in terms of performance. Let's take a look.

Using the same method as before:

```java
public static String getDefaultName() {
    System.out.println("In getDefaultName method");
    return "World";
}
```

This time, let's run the same code as before, but let's populate the optional with a value.

```java
System.out.println("orElse with populated Optional");
String orElseSideEffect = Optional.ofNullable("John").orElse(getDefaultName());
System.out.println(orElseSideEffect);
```

> Check: Before we look at the output, what do you expect to see? If you expect that the _getDefaultName_ method will NOT be called, you are 100% wrong. Whoops!

Let's examine the output:

    orElse with populated Optional  
    In getDefaultName method  
    John  

This can be a big deal if the _getDefaultName_ method has to do something slow, such as hitting a database. This results in unnecessary executions.  

Now let's look at an example using _orElseGet_:

```java
System.out.println("orElseGet with populated Optional");
String orElseGetName2 = Optional.ofNullable("John").orElseGet(() -> getDefaultName());
System.out.println(orElseGetName);
```

Output:  
    orElseGet with populated optional  
    World  

As you can see, the _getDefaultName_ method is not triggered as expected; the reason is that the supplier lambda is using lazy evaluation. The lambda is supplied, but since it is not needed, it is never executed, so it doesn't trigger the _getDefaultName_ method. With that said, the preferred way to provide a default value is generally going to be _orElseGet_.

Now let's take a look at an example of _orElseThrow_.

```java
String name = Optional.ofNullable(nullValue).orElseThrow(() -> new IllegalArgumentException("Name is missing"));
```

Output:  
    Exception in thread "main" `java.lang.IllegalArgumentException`: Name is missing

As you can see, an exception is thrown if the optional is empty.

------

## How Map, flatMap, and Filter Work with Optionals (20 min)

The optional class provides three useful stream-like operation methods to manipulate optionals:

- _filter_ - Runs a test on the optional value based on a given predicate. If the predicate returns "true," the optional is returned as-is. If the predicate returns "false," then an empty optional is returned.
- _map_ - Runs a computation on the optional's unwrapped value and wraps the result of the computation in a new optional before returning.
- _flatMap_ - Similar to the map method, except that where map operates on an optional containing an arbitrary value, _flatMap_ assumes the value itself is an optional, and will first unwrap that optional value before performing the computation. This is useful in processing Streams of optionals. You want to unwrap those optionals before processing, so you use _flatMap_. 

The definitions may be a lot to digest, so let's take a look at some examples to help clear things up.

### Demo

For the first example, let's take a look at _filter_:

```java
Optional<Integer> ageOptional = Optional.ofNullable(25);
boolean canBuyAlcohol = ageOptional.filter(age -> age >= 21).isPresent();
System.out.println(canBuyAlcohol);
```

Output:  
    true  

As mentioned in the _filter_ definition earlier, if the predicate returns "true," then the optional is returned as-is. So in this case, the _isPresent_ method returns "true."

Let's take a look at the opposite scenario, in which the age is less than 21.

```java
ageOptional = Optional.ofNullable(20);
canBuyAlcohol = ageOptional.filter(age -> age >= 21).isPresent();
System.out.println(canBuyAlcohol);
```

Output:  
    false  

The _filter_ method returns an empty optional, which causes the _isPresent_ method to return "false."

Let's take a look at the _map_ method. Let's say we have the following Person class:

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

We are going to run the same logic as before, where we check the person's age to see if they are old enough to buy alcohol. But this time, we'll use the _map_ method.

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

There's a lot going on here, so let's dissect.  

1. The _map_ method runs a computation on an optional's unwrapped value and returns an optional containing the result. 
1. The _map_ method accesses the Optional<Person>'s unwrapped "age" value (in this example). 
1. The _filter_ method then receives an Optional<Integer> representing the age of 25. 
1. The _filter_ predicate is true, so it returns the Optional<Integer> as-is to the _isPresent_ method.

Now let's look at a _flatMap_ example that does the same logic and talk through its details. Let's assume we have the following Person class:

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

Let's first examine the `PersonWithOptional` class. The member variable _age_ is an Optional<Integer>. In other words, the "age" is wrapped in an optional. Looking at the _flatMap_ definition, _flatMap_ will first unwrap the value from the optional before performing its computation.  

Looking at the _flatMap_ line in the example, it is accessing the Optional<PersonWithOptional>'s unwrapped "age" value just as the _map_ method does. The difference is that flatMap expects this optional to contain another optional, and will unwrap that extra later. Had we used _map_, the return from _map_ would have been Optional<Optional<Integer>>.

-------

## Independent Practice (15 min)

For independent practice, we will take the following template program below and complete the "TODO" portions that are in the comments. To confirm that your results are correct, iterate each list that you create and output the results.  

**Hint:**  
- You'll need to use _ofNullable_.
- You'll need to leverage the _flatMap_ and _filter_ methods.
- You'll also need to use _orElseGet_.

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

------

## Conclusion (10 min)

To recap, we've learned:

- What optionals are used for.
- How to create optionals.
- How to get values from optionals.
- How to use optional stream operations such as _filter_, _map_, and _flatMap_.

With a partner, draft a one-sentence answer to each of these questions:

- Why is _Optional.ofNullable_ preferred over using __Optional.of_?
- Which one of the following methods is the preferred way of getting values from an optional: _orElseGet_ over _orElse_ and _get_?
- Using the answer from the previous question, tell me why it's preferred.

#### References
- [Baeldung Optionals](https://www.baeldung.com/java-optional)
- [Tired of NullPointerExceptions?](https://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html)

