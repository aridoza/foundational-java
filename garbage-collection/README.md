| Title       | Type   | Duration | Author        |
| ----------- | ------ | -------- |   ----------  |
| The Heap and The Garbage Collector | Lesson | 1:00     |  Victor Grazi |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) The Heap and The Garbage Collector

## LEARNING OBJECTIVES

*After this lesson, you will be able to:*

- Describe what the garbage collector is and how it works.
- Specify the heap size when you launch a program.
- Differentiate between the heap and stack.
- Resolve an OutofMemory Error.

## LESSON GUIDE

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 5 min  | Opening  | Discuss lesson objectives |
| 10 min  | Lecture  | Introducing the Garbage Collector |
| 10 min  |Lecture   | Heap Size |
| 5 min  | Demo | Force an OutofMemory Error|
| 15 min | Indepenedent | Resolve an OutofMemory Error|
| 5 min  | Conclusion  | Review / Recap |

## Opening (5 mins)

We have already seen quite a bit about how Java allocates data, objects, arrays, collections, etc.
But we've glossed over a few subtle points – where does Java put all of this data? We can assume that memory is allocated as needed, but where does that memory come from? how does Java manage that memory? How does memory get reclaimed when the data is no longer required?

Enter: The Garbage Collector... promise it's not as ominous as it sounds.

## The Garbage Collector (10 mins) 

Before Java entered the picture, earlier languages like C and C++ required the programmer to _allocate_ memory as it was needed, and then _deallocate_ it once it was no longer needed.

Java introduced the concept of a _Garbage Collector_ which relinquished programmers from the duties of basic memory allocation/deallocation at all. 

The technology behind Garbage Collection has greatly evolved in the last 20 something years, and there are large companies that make a career out of optimizing garbage collection. 

We won't get into the precise details of all of these, but the common theme is that the JVM allocates an area of memory called _the heap_, where it stores all objects.
 
When the heap starts to fill up, Java runs a background process (the _Garbage Collector_) that looks at every object on the heap, traces its references, and references to those references, etc. transitively, to determine whether they are still referenced directly or indirectly by any live thread at all. If they are not, they are eligible for collection. 

The Garbage Collector will mark those for collection, and then in a sweep process will remove that memory and perform a compaction so that the memory once again becomes available.

> Share the program below with students. Give them a few minutes to answer the two questions below then reveal the answers. 

Consider the following program:
```java
for (int i = 0; i < 100; i++) {
    String message = "This is message " + i;
    System.out.println(message);
}
```

<details>
	<summary>What happens to the `message` string object taht was created during the loop?</summary>
	At the end of each loop iteration, the `message` String object that was created during that loop is no longer reachable. It was not assigned, it has no references, and there is no way to ever get it back.
</details>

<details>
	<summary>Based on this information, is it eligible for collection?</summary>
Yes, it is eligible for garbage collection. 
</details>


## Heap Size (10 mins) 

You can specify the heap size when you launch your program using launch flags:

```sbtshell
-Xms allocates the initial (and minimum) heap size.
-Xmx allocates the maximum heap size. 
```
For example the following will allocate an initial memory of 10GB and will increase that to 100GB:

```java
java -Xms10g -Xmx100g MyCode
```

If your -Xmx is greater than your -Xms setting, then Java will start with an initial heap size equal to your -Xms setting, then will allocate more heap as needed until the -Xmx value is reached. 

If you don't specify a heap size, Java will allocate a default heap for you, based on your machines physical memory. However, it is a good practice to determine your maximum expectedd heap size by observing the program during execution using tools like Linux's _ps_ or _top_.


Note that when your program executes, even if there is not a single object allocated, there is still some memory used by the program itself, to keep track of the call stack of every thread running.

For example if method a calls method b calls method c, Java must remember that when method c exits, return to the spot in method b that called it, and when that returns, return to method a, etc. 

This memory is called _the stack_ and is distinct from the heap. 

The stack also holds any method-local data that will be swept off once the method returns. The stack works like a fifo queue; it is allocated and data is assigned, then methods call other methods, allocating more stack space. Once a method returns, the stack is swept in a first-in-first-out manner, so the next frame in the stack (representing the calling method) becomes the top of the stack, and so on.



## Instructor Led Demo (5 mins) 

Does this mean that Java can never run out of memory? Let's see what happens when we run this program. 

> Instructor: you can poll the class what they think will happen as you're setting up to run the program below. 


```java
public class OOMError {
    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        for(int i = 0; ;i++) {
            Runtime runtime = Runtime.getRuntime();
            System.out.printf("Iteration: %d total memory: %d  free memory: %d%n", i, runtime.totalMemory(), runtime.freeMemory());
            list.add(new String[10_000_000]);
        }
    }
}

```

> Check: Can some one explain what happened? 

In this program we continuously create a large array and store it in a List. we display the total and available memory on each iteration. 

Eventually since every object is referenced by our List, nothing is eligible for garbage collection, and we exhaust the heap. Java runtime throws up its hands with an OutOfMemoryError.

So it is possible to run out of memory... What do we do when that happens?

SME NEED: the answer to that question ^^

## Independent Practice: Resolve the OutofMemory Error (15 mins) 

SME NEED: code that will get students set up with an outofmemory error (could also use the code from the demo) and an explanation a to how to resolve it.

Activity depenedent on how complicated the resolution is... might have to convert to lecture if needed. 

## Conclusion (5 mins) 

- How does the garabage collecter determine what is elligible for collection? 
- How do you set the heap size for a program? 
- What happens if you don't set one? 
- How does the stack compare to the heap?
- Describe how to resolve an OutofMemory Error.
