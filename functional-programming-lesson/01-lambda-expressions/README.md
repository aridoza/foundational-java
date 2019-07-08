
|                     Title                    |  Type  | Duration |  Creator |
|:-------------------------------------------:|:------:|:--------:|:--------:|
| Functional Programming - Lambda Expressions | lesson |   1:30   | Kyle Dye |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Functional Programming | Lambda Expressions

### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
* See how lambda expressions and streams improve code readability
* See how lambda expressions significantly decrease the amount of code to accomplish a task
* Create a Lambda Expression with streams

### STUDENT PRE-WORK
*Before this lesson, you should already be able to:*
- Understand the Java Basics lessons
- Understand the OOP in Java lessons

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
|  5 min |        Opening       |                                 Discuss lesson objectives                                |
| 10 min |     Introduction     |                         Describe the syntax of lambda expressions                        |
| 10 min |         Demo         |   Using an interface to add 2 numbers - Show an example with and without using lambdas   |
| 10 min |     Introduction     |                          Streams and iterating collections and streams                         |
|  5 min |         Demo         |                      Example of using streams to create another collection                     |
| 10 min |     Introduction     |                         Discuss the different types of Collectors                        |
|  5 min |         Demo         |      Modify the previous Demo of creating another collection to leverage a collection Collector      |
| 10 min |     Introduction     |                                      Discuss filters                                     |
| 20 min | Independent Practice | Take what we've learned and complete a program using streams, lambda expressions, filtering, and collecting |
|  5 min |      Conclusion      |                                       Review/Recap                                       |

## Opening

## Introduction: Lambda Expression Syntax

# ![](./LambdaSyntax.png)

A Lambda expression is an anonymous function which provides a very concise and functional syntax which is further used for writing anonymous methods. Lambda expressions are similar to anonymous classes.  However, one issue with anonymous classes is that if the implementation of your anonymous class is very simple, such as an interface that contains only one method, then the syntax of anonymous classes may seem unwieldy and unclear. In these cases, you're usually trying to pass functionality as an argument to another method, such as what action should be taken when someone clicks a button. Lambda expressions enable you to do this, to treat functionality as method argument, or code as data. 

A lambda expression consists of the following:
- A comma-separated collection of formal parameters enclosed in parentheses.
- The arrow token, ->
- A body, which consists of a single expression or a statement block.

**Notes:** You can omit the data type of the parameters in a lambda expression. In addition, you can omit the parentheses if there is only one parameter.  

**Example:**

    (int a, int b) -> { return a * b;}
The above example has 2 int type parameters, "a" and "b" respectively.  The expression body will multiply the int parameter "a" with the int parameter "b".

## Demo: To Lambda Or Not To Lambda, That Is The Question

In the following demo, we will create a "Computation" interface.  We will use this interface to solve simple math problems.
We will create 2 versions of the demo: Without Lambda Expressions and With Lambda Expressions.

**Example Without Lambda Expressions:**
    
    package com.ga.examples;
    
    public class NonLambdaExpressionIntroDemo {
    
        //Here's the Computation Interface
        interface Computation {
            int operation(int a, int b);
        }
    
        public static void main(String[] args) {
    
            //Notice the use of the anonymous inner class.
            Computation add = new Computation() {
    
                @Override
                public int operation(int a, int b) {
                    return a + b;
                }
            };
    
            System.out.println("5 + 6 = " + add.operation(5,6));
    
            //Notice the use of yet another anonymous inner class.
            Computation subtract = new Computation() {
    
                @Override
                public int operation(int a, int b) {
                    return a - b;
                }
            };
    
            System.out.println("10 - 6 = " + subtract.operation(10,6));
    
        }
    }

The output will be the following:
5 + 6 = 11
10 - 6 = 4

**Example WITH Lambda Expressions:**

    package com.ga.examples;
    
    public class LambdaExpressionIntroDemo {
    
        interface Computation {
            int operation(int a, int b);
        }
    
        public static void main(String[] args) {
    
            Computation add = (int a, int b) -> { return a + b; };
    
            System.out.println("5 + 6 = " + add.operation(5,6));
    
            Computation subtract = (int a, int b) -> { return a - b; };
    
            System.out.println("10 - 6 = " + subtract.operation(10,6));
        }
    }
    
**Note:** Notice the reduction of the amount of code needed to do the computation.  Stress this to the class!  

**Example 1:** 680 Characters and 32 lines  
**Example 2:** 456 Characters and 19 lines  

This is a 33% reduction in characters and 41% reduction in lines of code! Also, in most cases, the code becomes more reader friendly.

## Introduction: Streams and Collections

**What is a Stream?**  
A stream is a sequence of elements. Unlike a collection, it is not a data structure that stores elements. Instead, a stream carries values from a source, such as collection, through a pipeline.  Stream operations leverage the use of lambda expressions to achieve a task, as you'll see shortly.

**What is a Pipeline?**  
A pipeline is a sequence of stream operations, such as filtering and aggregation operations. Aggregate operations typically accept lambda expressions as parameters, enabling you to customize how they behave.  Some of the common aggregation operations are forEach, filter, sum, average, and sort.

**Types of Stream Operations**  
Stream operations are used to modify streams to create an end result. There are 2 main types of stream operations.  
- Intermediate operations - Intermediate operations take a stream as input and modify it to produce a different stream. Some of the commonly used intermediate operations are map, filter, sorted, and flatMap. 
- Terminal operations - Terminal operations mark the stream as consumed, after which point it can no longer be used further.  In other words, you will use terminal operations to create your end result objects. Some common terminal operations are forEach and collect.

**Getting a Stream from a Collection**

    List<String> stringList = Arrays.asList("Hello", "World");
    //To get the stream of the list, do the following:
    stringList.stream();
    
    //To print out the elements of the stringList collection, we could do the following:
    stringList.stream().forEach(stringValue -> {
        System.out.println(stringValue);
    });

The output would be:  
Hello  
World

## Demo: Iterating a collection using streams

In this demo, we will take an existing list of String objects and iterate it. While doing so, we will create another list of strings
by concatenating a string value to each value of the original string list.

    package com.ga.examples;
    
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    
    public class ListIterationWithConcatenationDemo {
    
        public static void main(String[] args) {
    
            List<String> stringList = Arrays.asList("My name is ", "My friends call me ", "My mother calls me ");
            List<String> concatenatedList = new ArrayList<>();
    
            stringList.stream().forEach(stringValue -> {
                concatenatedList.add(stringValue + "Jim");
            });
    
            //Now output the values of the concatenatedList using streams.
            concatenatedList.stream().forEach(stringValue -> {
                System.out.println(stringValue);
            });
    
            //Now output the values of the original stringList using streams to show that the list is unaltered.
            stringList.stream().forEach(stringValue -> {
                System.out.println(stringValue);
            });
        }
    }
    
The output would be:  
My name is Jim  
My friends call me Jim  
My mother calls me Jim  
My name is   
My friends call me   
My mother calls me   


**Key Takeaway**  
- Stress to the class the fact that the original list is unaltered when using streams.
- Have them identify the number of lambda expressions seen in the demo. Answer: 3

## Introduction: .map and .collect

**What does the map function do?**  
The map() intermediate operation is a method in the Stream class that represents a functional programming concept. In simple words, the map() is used to transform one object into a different object by using a lambda expression.

**Syntax:**  
.map(**argument -> { function to apply}) <-- Notice the lambda expression**

**Example:**  
    
    List<String> numbersList = Arrays.asList("1", "2", "3", "4", "5");
    
    Stream<Integer> numberListStream =
        numbersList.stream()
            .map(number -> { 
                return Integer.valueOf(number);
            });

In the example above, the "number" argument will represent each number list value.  The function
applied is "Integer.valueOf(number)".  The result is a stream of Integer values that can
be filtered, aggregated, or converted to other objects.

**Note:**  
The map() function will always return a stream since it's an intermediate operation.  

**What are collectors?**  
In the previous examples, we've seen how to iterate streams and map streams to different objects.  But how do you come up with an end result? That's where collectors come in. Collectors are terminal operations used to implement various useful reduction operations, such as accumulating elements into collections, summarizing elements according to various criteria, etc.  In layman's terms, this a way to convert a stream into your end result.

There are various functions of the Collectors class.  The one we will be focused on is "toList()".  
Let's expand our earlier example by converting the stream to a list of Integers.

    List<String> numbersList = Arrays.asList("1", "2", "3", "4", "5");
    
    List<Integer> newNumbersList =
        numbersList.stream()
            .map(number -> {
                return Integer.valueOf(number);
            })
            .collect(Collectors.toList());
 

## Demo: Rewrite Previous Demo using Collectors
For this demo, we are going to take the previous demo and use a Collector instead of manually adding elements to a new list.

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
                    .map(stringValue -> {
                        return stringValue + "Jim";
                    })
                    .collect(Collectors.toList());
    
            //Now output the values of the concatenatedList using streams.
            concatenatedList.stream().forEach(stringValue -> {
                System.out.println(stringValue);
            });
    
            //Now output the values of the original stringList using streams to show that the list is unaltered.
            stringList.stream().forEach(stringValue -> {
                System.out.println(stringValue);
            });
        }
    }

The output would be:  
My name is Jim  
My friends call me Jim  
My mother calls me Jim  
My name is   
My friends call me   
My mother calls me   

**Instructor Note:**  
- Point out to the class that the code is more concise and readable by using collectors.
- Also point out the stream pipeline of stringList.stream(), map, and collect.

## Introduction: Filters
So far we discussed about streams and how they can be manipulated via intermediate and terminal operations. We've seen how to use the intermediate operation "map" and the terminal operations "forEach" and "collect".  But what if we had a requirement where we only needed a subset of the data based on certain criteria?  This is where a very useful intermediate operation called "filter" comes in.

**What is a filter?**  
The filter method essentially selects elements based on a condition you provide. That's why the filter 
(Predicate condition) accepts a Predicate object, which provides a function that is applied to a condition. 
If the condition evaluates true, the object is selected. Otherwise, it will be ignored.  

**Example:**

    List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    
    //We want to create a list of only the even numbers.
    List<Integer> evenNumberList =
        numberList.stream()
            .filter(number -> number % 2 == 0)
            .collect(Collectors.toList());

    evenNumberList.stream().forEach(number -> System.out.println(number));
    
The output would be:  
2  
4  
6  
8  
10  

## Independent Practice
For the Independent Practice, we will take the following template program below and complete 
the "TODO" portions that are in the comments.  To confirm that your results are correct,
iterate each list that you create and output the results.  

**Hint:**  
- You'll need to leverage the intermediate operations "map" and "filter"
- You'll also need to use the terminal operation "Collectors.toList"

### Independent Practice Template

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
                return "Person{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
            }
        }
    
        public static void main(String[] args) {
    
            List<Person> personList = createPersonList();
    
            //TODO: Create a List of Person Objects whose name starts with the letter M
    
            //TODO: Create a List of Strings containing the names of the Persons over the age of 40
    
            //TODO: Create a List of Person Objects whose name starts with the letter J and are under the age of 47
            
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

The completed example can be found in the repo in the file named "IndependentPracticeCompleted.java".


## Conclusion
We've covered a lot in this brief introduction to lambda expressions, streams, filtering, and collecting. Hopefully this introduction shows how this functional style can improve code readability. Also, it can reduce the amount of code needed to achieve a task when compared to the imperative style.

#### Let's Review

With a partner, draft a one sentence answer to each of these questions:
- What is a lambda expression?
- What makes lambdas great?
- What is a stream?
- What is filtering?
- What is collecting?

## References
- [Lambda Expressions Oracle](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Java 8 Map, Filter, and Collect Examples](https://dzone.com/articles/how-to-use-map-filter-collect-of-stream-in-java-8)
- [Java API on Collectors](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)
