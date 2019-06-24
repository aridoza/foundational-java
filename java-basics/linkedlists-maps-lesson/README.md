---
title: Linked Lists and Maps
type: lesson
duration: "1:30"
creator:
    name: Drew Mahrt
    city: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Linked Lists and Maps

### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
- Create and manipulate Java LinkedLists
- Compare array and linked lists
- Convert a list into an array (and vice-versa)
- Describe and implement a Hash Map

---

### LESSON GUIDE

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-mins)  | Discuss lesson objectives |
| 10 min  | [Introduction](#introduction-lists10-mins)  |  List |
| 5 min  | [Demo](#demo-lists-5-mins)  | Lists |
| 10 min  | [Independent Practice](#independent-practice-lists-10-mins)  | Lists |
| 10 min  | [Introduction](#introduction-maps10-mins)  |  Map |
| 10 min  | [Demo](#demo-maps-10-mins)  | Maps |
| 10 min  | [Guided Practice](#guided-practice-maps-10-mins)  | Maps |
| 15 min  | [Independent Practice](#independent-practice-organizing-information-15-minutes)  | Organizing Information |
| 5 min  | [Conclusion](#conclusion-5-mins)  | Review / Recap |

<a name="opening"></a>

## Opening (5 mins)

One of the most commonly used libraries used in Java is the Collections class. We have already used Arrays and ArrayLists. Today we will be covering in more detail the following popular collections: Lists and Maps. We will be learning about and comparing some of these today to understand their strengths and weaknesses, and how you should decide which to use. Once you have a better understanding of how to organize the information in your apps, you will be able to write much cleaner and more efficient code.

> Check: As a quick review, what are some of the differences between Arrays and ArrayLists?

***

<a name="introduction"></a>

## Introduction: Lists (10 mins)

First we will cover Lists, one of the most popular collection types. We know the basics of lists. They hold collections of data, and provide a large set of helper functions that make manipulating the data much easier. You can search, sort, add and remove with ease. One of the major drawbacks of Lists are that they contain a larger overhead than a simple array (more memory, slightly slower due to all the work behind the scenes).

The two most commonly used types of lists are the ArrayList and the LinkedList. We already met ArrayLists; they're backed by an array to hold the data. As we know, one major advantage over arrays is that you don't need to manually create larger arrays if your data set grows. Keep in mind that the ArrayList is taking care of this for you, so the operation isn't free. Therefore ArrayLists aren't the best choice for rapidly growing or shrinking sets of data.

<!--Instructor note: Draw out nodes on the board to illustrate a LinkedList-->

A LinkedList is backed by a collection of objects linked together by pointers to each memory location. While we won't be going over how these are implemented, it is important to know that each element in the list is stored in empty spots in memory, so there is no need to worry about the changing size of the data set. Data is simply inserted and removed like links in a chain. Accessing a specific element in a linked list is generally slower than an array, because they aren't stored in sequential memory.

<details>

  <summary>Check: What are some examples of a use case for ArrayList and LinkedList?</summary>

  - ArrayList: Contacts List
  - LinkedList: To Do Lists

</details> 

***

<a name="demo"></a>

## Demo: Lists (5 mins)

Let's look at how to define lists!

We're going to begin with reviewing how to define an ArrayList. Let's create an ArrayList called cookieFlavors.

<!-- Instructor note: Have students shout out / walk you through how to do this.-->

```java
  public static void main(String[] args) {
    // initialize ArrayList
      ArrayList<String> cookieFlavors = new ArrayList<>();
    // add items
      cookieFlavors.add("chocolate chip");
      cookieFlavors.add("oatmeal raisin");
  }
```

Fun fact: you can actually convert between arrays and lists very easily!

``` java
String[] cookieFlavors = new String[]{"chocolate chip", "oatmeal raisin"};
List<String> stringArrayList = Arrays.asList(cookieFlavors);
```

The process for creating a LinkedList and an ArrayList is the same, just with the class name changed.

```java
  public static void main(String[] args) {
    // initialize LinkedList
      LinkedList<String> iceCreamFlavors = new LinkedList<>();
    // add items
      iceCreamFlavors.add("Oreo");
      iceCreamFlavors.add("Cherry Garcia");
  }
```

***

<a name="guided-practice"></a>

## Independent Practice: Lists (10 mins)

1) Create a LinkedList containing ints 1-10. 
2) Afterwards, test to see if the values 5-15 exist in the list, and remove the elements that exist (so remove 5-10.) 
3) Finally, print the size of the list. 

***

<a name="introduction"></a>

## Introduction: Maps (10 mins)

The next Collections type we will cover is the Map. A map is similar to a list, but each entry contains two parts: A key and a value. The key is unique in the map, think of it like the index in an array. Each key maps to a certain value, but there can be duplicate values in a map. 

One of the most popular implementations of a map is called the HashMap. A HashMap is a map, as described above, where the data is stored in an array. The index where the data is stored is generated by a hash function. Basically, you give the HashMap the Object you want to use as a key, and it returns an integer to use as the index. When you try to retrieve the value from the map, it uses the same function to generate the same index. Strings are often used as the keys for a map.

> Check: Come up with an example for using a Map.

***

### Collision (OPTIONAL 10 minutes)

The hash function isn't flawless; what happens if it gives us the same index for two keys? This is called a **collision**.

> Check: how might this be resolved?

Java handles this by using a structure like a LinkedList in place of the actual Objects in the HashMap. If a collision occurs, the object is added onto the end of that list.

***

<a name="demo"></a>

## Demo: Maps (10 mins)

Let's try implementing a HashMap. Pretend we have a class called Student which contains the students name, grades, etc. Each student has a unique student id we will use as the key.

``` java
HashMap<String,Student> studentMap = new HashMap<String,Student>();

studentMap.put("195abc",mabelStudent);
studentMap.put("542ijk",kareemStudent);
...
Student retrievedStudent1 = studentMap.get("195abc"); //mabelStudent
Student retrievedStudent2 = studentMap.get("111"); //null

studentMap.remove("542ijk")

```

As you can see, a Map isn't that different from a List. You can add and retrieve objects. One major difference is that you have no control over the underlying data structure. There is no way for you to sort or control the order of the elements in the Map.

> Check: Write the code for removing an object through the key. You will probably have to look it up in the documentation.

***

<a name="guided-practice"></a>

## Guided Practice: Maps (10 mins)

Let's create a HashMap that represents a dictionary. The key will be the word, and the value will be the definition. We must add the following value to the map, retrieve it, and print it to the console.

For example:
- "apple":"A fruit from a tree"
- "lake":"A large body of water"

***

<a name="ind-practice"></a>

## Independent Practice: Collections (15 minutes)

- Create a LinkedList of Strings with the values: "One","Two","Three","Four"
- Create a HashMap with the keys {"One","Two","Three","Four"} taken from the list, and the Integer values 1,2,3,4 taken from the array
 - Print out the HashMap size after adding the above items to it.

***

<a name="conclusion"></a>

## Conclusion (5 mins)

We've covered the major components of the Collections class and how to use them. From sorting to searching, collections make organizing your data in the app very fast and easy. Arrays, lists, and maps will become integral parts of your apps as you continue development, and working with them will become second nature.

***

### ADDITIONAL RESOURCES

- [Oracle: Array](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Oracle: List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)
- [Oracle: HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
