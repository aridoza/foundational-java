| Title       | Type   | Duration | Author        |
| ----------- | ------ | -------- |   ----------  |
| The Heap and The Garbage Collector | Lesson | 1:00     |  Victor Grazi |

* What is the Heap vs Stack?  
* Where are objects stored? (allocate memory, free memory)  
* What is the garbage collector and when does it free objects from memory?  
* What is a memory leak?  
* OutOfMemoryError  

### Understand  
* Students need to understand what is garbage collection and how Java implements it. Essentially they should understand OutofMemory error and how to resolve it  

### Activity - Instructor lead
Generate an OutOfMemoryError


## Java Memory
We have already seen quite a bit about how Java allocates data, objects, arrays, collections, etc.
One subtle point we glossed over is, where does Java put all of this data - we can assume that memory is allocated as needed, but where does that memory come from, how does Java manage that memory, and how does memory get reclaimed when the data is no longer required?

Before Java entered the picture, earlier languages like C and C++ required the programmer to _allocate_ memory as it was needed, and then _deallocate_ it once it was no longer needed.

Java introduced the concept of a _Garbage Collector_ which obviated the need for the programmer to manage basic memory allocation/deallocation at all. 

The technology behind Garbage Collection has greatly evolved in the last 20 something years, and there are large companies that make a career out of optimizing garbage collection. We won't get into the precise details of all of these, but the common theme is that the JVM allocates an area of memory called _the heap_, where it stores all objects.
 
When the heap starts to fill up, Java runs a background process (the _Garbage Collector_) that looks at every object on the heap, traces its references, and references to those references, etc. transitively, to determine whether they are still referenced directly or indirectly by any live thread at all. If they are not, they are eligible for collection. The Garbage Collector will mark those for collection, and then in a sweep process will remove that memory and perform a compaction so that the memory once again becomes available.

For example, consider the following program:
```java
for (int i = 0; i < 100; i++) {
    String message = "This is message " + i;
    System.out.println(message);
}
```
At the end of each loop iteration, the `message` String object that was created during that loop is no longer reachable. It was not assigned, it has no references, and there is no way to ever get it back. Therefore it is eligible for garbage collection. 

You can specify the heap size when you launch your program using launch flags:
```sbtshell
-Xms allocates the initial (and minimum) heap size.
-Xmx allocates the maximum heap size. 
```
For example the following will allocate an initial memory of 10GB and will increase that to 100GB:

```java
java -Xms10g -Xmx100g MyCode
```

If your -Xmx is greater than your -Xms setting, then Java will start with an initial heap size equal to your -Xms setting, then will allocate more heap as needed until the -Xmx value is reached. If you try to exceed you would get an OutOfMemoryError

Note that when your program executes, even if there is not a single object allocated, there is still some memory used by the program itself, to keep track of the call stack of every thread running. For example if method a calls method b calls method c, Java must remember that when method c exits, return to the spot in method b that called it, and when that returns, return to method a, etc. This memory is called _the stack_ and is distinct from the heap. The stack also holds any method-local data that will be swept off once the method returns. The stack work like a fifo queue; it is allocated and data is assigned, then methods call other methods, allocating more stack space. Once a method returns, the stack is swept in a first in first out manner, and the next frame in the stack (representing the calling method) becomes the top of the stack, and so on.

## Memory leaks
Does this mean that Java can never run out of memory? That would be a happy dream, but the truth is, it can!

For example, let's say you have a collection that keeps getting larger and larger. Eventually that could run out of memory!

Let's see an example of that

<details>
<summary>Activity - Instructor lead - OutOfMemoryError</summary>

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
</details>

In this example, we continuously create a large array and store it in a List, we display the total and available memory on each iteration. 

Eventually we exhaust the heap, and since every object is referenced by our List, nothing is eligible for garbage collection, and the Java runtime throws up its hands with an OutOfMemoryError.
  
## Default heap
If your -Xmx is greater than your -Xms setting, then Java will start with an initial heap size equal to your -Xms setting, then will allocate more heap as needed until the -Xmx value is reached. If you try to exceed that your program will throw an OutOfMemoryError.

If you don't specify a heap size, Java will allocate the heap for you, based on your machines physical memory. It is a good practice to determine your maximum expectedd heap size by observing the program during execution using tools like Linux's _ps_ or _top_.
