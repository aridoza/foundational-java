# Multi-threading and Java Concurrency

After this lesson, students will:

* Understand the fundamentals of concurrency in Java
* Be able to create and launch a thread using Thread or by subclassing Runnable interface
* Understand how to deal with thread contention
* Estimate how many threads to use for an application
* Understand basic concurrency components introduced in Java 5

**Topics:**

  - Why use concurrency?
  - How to create a Thread
  - The Runnable interface
  - How many threads are correct?
  - The synchronized keyword
    - Re-entrant Native locks  
  - Signalling threads using wait/notify - synchronization
  - Concurrency components:
    - Executors class
        - ExecutorService interface
        - Fixed Thread Pool
        - Cached Thread Pool
        - Scheduled Executor
    - Execute vs. Submit
    - Atomics components - AtomicInteger
    - ReadWriteLock

<!-- COMMENT (Brandi): I removed "Callable interface" because it wasn't implemented in the lesson. Was this critical? -->

**Activities:**

- Instructor-led code-along: Start ten threads in a loop
- Instructor-led code-along: Portfolio pricing method
- Student exercise: Create 2 Threads to Track Free Memory, Total Memory, and Time.
- Sizing: 5 (biggest)

## Fundamentals of Concurrency

Until now, we have covered basic programs and program flow. We have seen how you can control the flow of a program using things like `if` statements and `for` loops. However all of the programs we have seen so far have been _synchronous_. One thread running the program serially (consecutively) from beginning to end.

However today's computers have powers far beyond what we have seen or used thus far. Commodity computers have multiple CPUs, dozens or hundreds of cores, and even within a single core, Java spins threads to perform parallel processes.

## Why Use Concurrency?

Why would we ever need to have multiple threads performing concurrent work?

Consider a web application with dozens (or millions!) of concurrent users. We would not expect each user to wait in line until the previous user is done; rather we 
want to handle these requests _concurrently_.

Or let's say our application requires a lot of processing such as database queries, file reads and writes, and URL connection handling, where there's lots of IO. Do we 
want each outgoing request to wait for the other to return before the next one starts? Wouldn't it be better to have all of our requests process _concurrently_?

Or consider an application that needs additional input for its processing. A naive approach would be to save all of the state of the process, terminate the process, get the additional data, and then restart the original process. That approach requires a lot of housekeeping overhead, keeping track of things, especially if there are many such things going on. Wouldn't it be better if the process could spin a concurrent process to get the required data, without stopping itself, and then continuing when the data is available?

All of these reasons and more explain why the designers of Java made the decision to include concurrency in the core JDK, making it perhaps the first language to do so.
  
## How to Create a Thread

Threads are really easy to create, but with great simplicity comes great responsibility! We will discuss some of the common pitfalls in a little while. For now let's jump straight into creating and starting our very first thread.

There are two popular ways to create a thread, and you will see both heavily used:

* Override the Thread class, implementing the `run` method
* Implement the Runnable interface, and pass it to a Thread

Let's use both of these approaches to start a thread that writes the current time to System output every 2 seconds

## Override the Thread Class

The `Thread` class has a method called `public void run()` that is called implicitly when you _start_ your thread. 

In this approach, we will create a new class that _extends_ `Thread` and _overrides_ the `run()` method. Then we will call our class's `start()` method, which will start the thread and implicitly call `run()`. 
 
 > Reminder: Be sure to get this clear... you _implement_ `run()` but you _call_ `start()`! Tricky! 

 Let's walk-through an example:

**TimeLogger: Extending the Thread Class**
 
```java
import java.time.LocalTime;

public class TimeLogger extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(LocalTime.now());
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        TimeLogger timeLogger = new TimeLogger();
        timeLogger.start();
        System.out.println("Thread was started");
    }
}
```

There's a lot going on here, so let's talk through it.

First we imported the `LocalTime` class, which is a convenient class for capturing time information. 

Next we implemented our `TimeLogger` class, which _extends_ `Thread`.

Now comes the meat. We overrode the `Thread` class's `run()` method. The first thing the run method does is to declare a _try-catch_ block. We surround code that we think may throw an exception (or even expect to throw an exception) with the try portion, and make some code to gracefully handle the exception we anticipated in the catch portion. More on try-catch in a moment!

`Thread.run` normally does its job and then exits, terminating the Thread. However in our case, we don't want it to exit, we want it to keep printing, so we use a _while_ loop to continually execute our output statement. 

Next we create a new `LocalTime` object, which refers to the current time (`now()`) at the time of instantiation.

Since all of that is happening in a _while_ loop, it will continue to loop forever without pause. But the requirement was to display the time every 2 seconds, so we must sleep for 2 seconds between loop iterations. To do that we call the _Thread.sleep()_ method, supplying the number of milliseconds to sleep, in this case 2 seconds is 2000 ms, so we call `Thread.sleep(2000)`. 

Finally we define the `main()` method, which launches our program. It _starts_ our new thread by calling the `start()` method, (which implicitly calls the `run()` method, in a new thread.)

> Note: We get the implementation of the `start()` method from the `Thread` class which we're extending. That's why we don't have to write it ourselves!

### What About That Try-Catch Block?

Coming back to the try-catch block, notice that `Thread.sleep()` throws an `InterruptedException`. That exception is thrown when the thread's `interrupt()` method is called (usually by frameworks or application servers), to initiate a smooth shutdown of the threads. Since `InterruptedException` is a checked exception (meaning we called it out in the catch), it _must_ be caught. One side-effect of catching an `InterruptedException`, is that the thread's _interrupt_ flag is reset, meaning that it is no longer interrupted. To propagate the interrupt, we must set the interrupt flag once again, which is why we call `Thread.currentThread().interrupt()`.

Notice that we enclosed the while loop _inside_ the try-catch. A common mistake even advanced programmers make is to do the opposite, and they enclose the try-catch _inside the while loop_. Why is that wrong?

Well think about it. If the try catch would be inside the while, then imagine what would happen if someone calls the interrupt method. In that case, the exception would trap the interrupt, set the interrupt flag, and then loop again! The program would never end, even after an interrupt! So we fix that by including the while _inside_ the try catch. Now if an interrupt occurs, the while loop exits, and the catch block takes over, sets the interrupt flag, and exits, returning control to the caller.

This is a _very_ common idiom in Java concurrency: execute some activity in a loop, sleep, and catch the `InterruptedException` outside the loop. Now you know!

Let's execute that program (`Ctrl-Shift-F10` in IntelliJ.) Notice that our `while(true)` statement will never exit, so the only way to exit this program is to _kill_ it (`Ctrl-F2` or `Command-F2` in IntelliJ), or pull the plug!

The output looks like this:

```text
Thread was started
11:57:16.913
11:57:18.963
11:57:20.964
11:57:22.964
```

**Do you see anything unusual there?**

Notice that in our _main_ method, the first thing we did was to start our thread, and then secondly, we printed out "Thread was started". However in the output, we can see that "Thread was started" was logged first, even though it was declared last!

**Why did that happen?**

Keep in mind that everything in Java runs in a thread. Even if you are creating an innocent little "Hello, World" application, Java implictly spins up a thread called the _main thread_ and executes the program in that thread.

Once the main thread called our Thread `start()` method, it launched a new thread, that runs in its own time. Then our main thread resumed, which printed out the "Thread was started" message. Meanwhile back at the ranch, our new Thread was preparing itself, then it got into action and began its business of printing the current time.

## The Runnable Interface

We have seen one way to create a thread by extending the `Thread` class and overriding its `run()` method. The second approach is to recognize that the `Thread` class has a constructor that accepts a _Runnable_ instance. Runnable is an interface with one method - `public void run()`. Using this approach, you construct a new Thread instance by passing a Runnable instance to the constructor, then you call your Thread's _start_ method, which will call your Runnable in a new Thread.

> Tip: A "Runnable" instance is any object whose class _implements_ the `Runnable` interface.

**Here is an example program:**

<details>
<summary>Time: Runnable version</summary>


```java
public static void main(String[] args) {
    class TimeRunnable implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(LocalTime.now());
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e);
            }
        }

    }

    TimeRunnable timeRunnable = new TimeRunnable();
    Thread thread = new Thread(timeRunnable);
    thread.start();
    System.out.println("Thread was started");
}
```
</details>
 

We create a class that implements `Runnable`. We construct a new instance, supplying a `run()` method, and pass that to the `Thread` constructor. Then we start the thread. Hitting `Ctrl-Shift-F10` to execute that, produces the same result as the first approach.

This approach is roughly similar to the previous approach. **So why use one over the other?**

There are several advantages to the `Runnable` approach, one being that it can be slightly less overhead, and perhaps even more importantly, that there are other ways to launch `Runnable` instances using frameworks such as Java's built in _Executors_ framework, which we will see soon.

## Using Anonymous Inner Classes

There is another common idiom you should be aware of for creating threads using the `Runnable` interface, and that is using _anonymous inner classes_. We won't be looking at those in much detail, but you will see the syntax in your travels, and you should be aware of it. The idea behind anonymous inner classes is that we should not need to assign a name to a class that we are only ever going to use once, in a very limited context. For such cases, Java allows you to create _anonymous inner classes_, which are declared and used in-line, without having to name them.

> Aside: You may have seen anonymous functions if you've used a language like JavaScript (no relation to Java). The motivation for doing so here is exactly the same!

In our second thread example, we created a new class called `TimeRunnable`, and then instantiated that and passed in the instance to the `Thread` class. 

Contrast the following to the previous `TimeRunnable` version:

<details>
<summary>TimeLogger - implementing Runnable</summary>

```java
public static void main(String[] args) {
    Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(LocalTime.now());
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e);
            }
        }
    };

    Thread thread = new Thread(timeRunnable);
    thread.start();
    System.out.println("Thread was started");
}
```
</details>

The syntax looks a bit tricky at first glance. Basically the first line of the `main()` method is declaring a new `Runnable` instance, but we are calling an anonymous constructor (`new Runnable()`) that is formed by implementing the `Runnable.run()` method.

To highlight the difference, compare the two versions. You can copy and paste the first version into IntelliJ, then copy the second version into you clipboard, right click and choose "Compare to Clipboard":

![](resources/compare-to-clipboard1.png)
![](resources/compare-to-clipboard2.png)

Study the two lines that are different and compare the syntax.

> Note: Another popular variation of the `Runnable` syntax is to use Lambda expressions, and we will learn about those when we get to the lesson on Lambdas and Streams!

## Student Activity: Report Free Memory, Total Memory, and Time.

We want to capture some metrics in our logs, to ensure that our application is properly executing and that there is enough memory.

**Your Task:**

* Create an application that spins two extra threads.  
* The first thread logs the system time every minute. 
* The second thread logs the total memory and free memory every 15 seconds.

CHALLENGE: Try not to use any duplicated code!

> Hint: In order to get the current time, call `LocalTime.now()`. To get the free memory, call `Runtime.freeMemory()`. To get the total memory, call `Runtime.totalMemory()`. 

<details>
<summary>Solution: Memory and Time Threads</summary>

<!-- COMMENT (Brandi): Error. This code prints the same string every time since the string message is calculated just once for each call to spinThread. You need to use a lambda expression here. -->

```java
public class FreeMemoryAndTime {
    public static void main(String[] args) {
        spinThread("Free memory: " + Runtime.getRuntime().freeMemory() + " Total memory: " + Runtime.getRuntime().totalMemory(), 60_000);
        spinThread("Current time: " + LocalTime.now(), 15_000);
    }

    private static void spinThread(String message, long delay) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(message);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        thread.start();
    }
}

```


**What are those underscores?**

Underscores in numeric literals are ignored in Java and used for readability. In the code below, the `delay` is a time in miliseconds, which is difficult to read. Putting the underscore in lets us easily identify that the sleep timer of the threads waits for 60 and 15 seconds respectively on each iteration.

</details>


## Race Condition

Now it is entirely possible that our time printing thread could have started before our main thread got around to printing the "Thread was started" message, in which case "Thread was started" would have printed as the second message instead of the first. Each thread operates independently, and so the intercolation is unpredictable. In plain English, this basically means we can't know the order they will happen ahead of time. This is known as a **race condition**, and sometimes that makes testing threaded code very difficult! A race condition essentially means that different threads execute independently, and so they can appear to randomly execute their steps in different orders.

This can have some interesting side effects, when trying to assign and access a shared variable from different threads.

## Instructor-led code-along: Race Conditions

<!-- Note to instructor, see the class RaceCondition -->

Let's set up two threads, which each change the value of a shared variable, and then inspects the variable to see if it is the value as set.

Let's set up two threads to set and then check the value. Here is the first. The second is similar


```java
private long someSharedVariable;

Thread thread1 = new Thread(new Runnable() {
    @Override
    public void run() {
        while (true) {
            someSharedVariable = 0;
            if (someSharedVariable != 0) {
                System.out.println("huh? Expected " + 0 + " but got " + someSharedVariable + "!");
            }
        }
    }
});

Thread thread2 = new Thread(new Runnable() {
    @Override
    public void run() {
        while (true) {
            someSharedVariable = 1;
        }
    }
});
```

The output might look something like this:

```text
huh? Expected 0 but got 1!
huh? Expected 0 but got 0!
huh? Expected 0 but got 1!
huh? Expected 0 but got 0!
huh? Expected 0 but got 0!
huh? Expected 0 but got 1!
huh? Expected 0 but got 1!
huh? Expected 0 but got 0!
```

**What's going on?**

*Up is down? Left is right? 0 is 1?!*

Whoa! Don't worry, there is actually a logical explanation for this behavior! You have to realize that thread1 is continually setting `someSharedVariable` to 0 and thread2, presumably operating at the same time is trying to set it to 1. At any given time we don't know who touched the shared variable last! So it's less of a logical contradiction so much as two siblings who are both fighting over who gets to play with a toy, but it's pretty clear even from this samll example that threading and concurrency can lead to some trippy behavior!

## The Synchronized Keyword

When you want to make absolutely sure that two threads cannot execute at the same time, you can _lock_ those calls. The low level way to do that is using the _synchronized_ keyword. That was in fact the only way, until Java 5, and it is very common. The other way is using the `java.util.concurrent` package, introduced in Java 5. We'll introduce that later.

Let's take a look at that _synchronized_ keyword.

When you declare methods as `synchronized`, then that ensures that _only one thread at a time_ can be executing any of the synchronized methods on any given object. The reason this works, is that every object instance has built in to it what is called an "intrinsic lock". When a thread enters a synchronized method, it automatically grabs that lock. If any other threads try to access a synchronized method while one thread already has the lock, then the other threads must _block_ until the current thread relinquishes the lock by exiting the synchronized block. When a thread is blocked, there is absolutely no way for it to move at all, until the lock is relinquished or the program ends. If many threads are waiting for the lock on an object, there is no guaranty that they will acquire the locks fairly, and it is possible that any given thread will have to wait indefinitely, depending on the volume of threads. The bottom line is be careful how you synchronize, and try to visualize all of the execution paths. 

**Seems a little unfair doesn't it?**

![](https://res.cloudinary.com/briezh/image/upload/v1561421042/DMV-cartoon_x2twcy.jpg)

<!-- COMMENT (Brandi): I don't know who to credit the comic to, and honestly just replace it with something else light-hearted, but this content is DENSE. Having visual aids, examples, or humor will go a long way toward breaking up the monotony. -->

Have you ever been in a grocery line trying to buy one item behind someone buying 100 items? Or have you been waiting in a customer service line while whoever is in front of you had a complex problem? You were left waiting indefinitely, and if the person in front of you felt like taking more time you'd have no choice but to just wait and wait! It turns out, we don't like our Java threads to wait on hold either! Later on in this lesson, we will learn about the _ReentrantLock_, that allows you to lock in a fair way.

One more important note about the intrinsic lock.

Consider the following example. Let's say you have two methods, both synchronized:

```text
public synchronized void myFirstMethod() {...}
public synchronized void myOtherMethod() {...}
``` 

Now if some thread #1 comes along and calls `myFirstMethod`, and thread #2 comes along and calls `myOtherMethod` while thread #1 is holding the lock, then thread #2 will block, as we said. However, let's say myFirstMethod calls `myOtherMethod`, and they are both synchronized. Then even though thread #2 will block when it calls myOtherMethod, nonetheless, thread #1 can still call myOtherMethod without blocking, since it already has the lock, so the synchronized keyword in that case has no effect.

<details>
<summary>Intrinsic Lock is Reentrant</summary>

```java
public synchronized void myFirstMethod() {
    // Other contents
    myOtherMethod();
    // More contents
}
public synchronized void myOtherMethod() {
    // thread #1 can enter myOtherMethod even though it is in the synchronized method myFirstMethod
}
```
</details>

One final note on the _synchronized_ syntax. When you add _synchronized_ to a method, you are in effect saying, "lock on this object". However you can choose to lock on different objects, using the related syntax:
`synchronized(someObject)`

When we create an object for the sake of using its lock, we call that object a "_mutex_". Be sure that when you are using an object as mutex, that variable is not going to change its value, because the lock belongs to the value not the variable. Generally you want to declare those mutex variables to be _final_ to prevent any reassignment. 

Let's modify our class above to use synchronized and see how that works:

<details>
<summary>Synchronized</summary>

<!-- COMMENT (Brandi): I feel like MUTEX needs more explanation here and may be a stumbling point for students. Why is it capitalized? What does it do? Why do we use it with synchronized as opposed to the variable we're trying to protect? These are all questions we can anticipate. -->

```java
private final Object MUTEX = new Object();

Thread thread1 = new Thread(new Runnable() {
    @Override
    public void run() {
        synchronized (MUTEX) {
            while (true) {
                someSharedVariable = 0;
                if (someSharedVariable != 0) {
                    System.out.println("huh? Expected " + 0 + " but got " + 0 + "!");
                }
            }
        }
    }
});
```
</details>

Thankfully, when we synchronize, our updates and accesses are guaranteed to occur atomically, in the same thread, and so we see there is none of the surprised output like we saw in the initial version.

Note that using the synchronized method approach, if you have different instances of that class, all bets are off, and it is entirely permissible for different threads to access those methods on different object instances. If you want to lock a method across _all_ object instances of that class, then you can make that method synchronized. There are a few variations, but in this course we will not look further into that approach.
 
## Signalling Threads Using wait/notify/notifyAll

We learned about grabbing an intrinsic lock on an object.

There is a useful idiom we need to learn, that will help us in situations where some Thread 1 must _wait_ for another Thread 2 to complete, and then continue. In such cases we can use Java's built-in _wait-notify_ mechanism. Methods `wait` and `notify` are both from the `Object` class, so every Java class inherits those, and they should never be overridden. 

### Wait

If a thread owns the intrinsic lock on a mutex, and it wants to wait for some condition to be true before resuming, then it can call the `wait` method on the mutex, which will send the calling thread into a _waiting_ state. There are actually two flavors of `wait`. The first takes no arguments, and will wait forever (until notified, as we will see shortly.) The second takes a long argument, which represents the number of milliseconds to wait. If the time lapses, our thread exits the wait state, and is now runnable.(Passing in a value of 0 is equivalent to the no-parameter version, and will wait forever.)

We said earlier that when a thread grabs an intrinsic lock (by entering a synchronized block) then no other thread can enter that synchronized block (or any block that is synchronized on that object.)

We need to refine that statement slightly. When a thread is in the wait state, it _temporarily forfeits the lock_ it is waiting on, and the lock becomes available for another thread to take. 

So if a thread is in the timed-wait state and the time lapses, we said our thread exits the wait state, and is now runnable. Again we need to refine that slightly, because if another thread is already holding the lock then when our thread exits the wait state, then the thread enters the _blocked state_, until the lock becomes available again.

![](resources/synchronized.gif)

### notify/notifyAll

Now let's say our thread is moving merrily along, until it comes to a point where it needs to wait for some data to be available from another thread. This is a very common concurrency idiom, and Java provides the `notify` and `notifyAll` keywords to solve it.

It's easier to see it in an example. Let's say we have two threads, one that is reading file data (the _reader_ thread, and another that is formatting and displaying that data (the _writer_ thread).

Now there is no reason for the writer to write anything, until the reader has read something. So the reader must have a way to signal to the writer that data is available. This is where wait/notify comes in handy.

Here is the program. Let's study what it is doing:

<details>
<summary>Wait-Notify</summary>

```java
package com.generalassembly.concurrency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReaderWriterExample {
    private Object mutex = new Object();
    private volatile int index = 0;
    String[] files = {
        "Concurrency/resources/flatland.txt",
        "Concurrency/resources/war-and-peace.txt",
        "Concurrency/resources/sherlock-holmes.txt",
    };

    String[] values = new String[3]; // allocate 3 slots, but for now, leave them null
    public void launch() {
        System.out.println(new File(".").getAbsolutePath());
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                for(index = 0; index < files.length; index++) {
                    try {
                        byte[] strings = Files.readAllBytes(Paths.get(files[index]));
                        String string = new String(strings);
                        values[index] = string;
                        synchronized (mutex) {
                            mutex.notify();
                        }
                        Thread.sleep(2000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.exit(0);
            }
        });
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        synchronized (mutex) {
                            mutex.wait();
                            System.out.println(values[index]);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        writer.start();
        reader.start();
    }

    public static void main(String[] args) {
        new ReaderWriterExample().launch();
    }
}
``` 
</details>

In this example, a reader thread reads a file, then stores it in an array, storing the index to the array in a shared variable called `index`. Then it sleeps for two seconds. 

Note that after the reader thread has read the file, it stores the result in our array, and then it _notifies_ the writer thread to do its job.

The call to `notify` immediately wakes up the writer thread, which then grabs the newly stored value, writes it to standard out, and then goes back to the wait state until it is notified to wake up again.

In our example, the call to `notify` wakes up the waiting thread. This works great because we only created one waiting thread. But what happens if you have many waiting threads? A call to notify will wake up one of the waiting threads at random, which is often what you want. However there are times you want all waiting threads to wake. In that case you would call `notifyAll`, which will notify every waiting thread to wake up. (The observant student will notice that all threads are in the same synchronized block, so how can they all wake at the same time? The answer is they can't. Notify will change all waiting threads from the waiting state to the blocked state, waking up one thread at random. As each thread relinquishes the lock, another thread will be selected at random to become runnable, until every thread has had a chance.)

Incidentally, notice the keyword `volatile`, which is used before the declaration of the index variable.

This is a deep concept. The JVM will generally make a local copy of variables that are used by each thread, and the thread has the right to assume that the value will not be changed by any other thread.

If one thread modifies that variable, due to a JVM optimization, Java makes no guarantees that that the change will be seen by another thread, unless we mark the variable `volatile`, which tells the JVM to read the value of the variable on every access, instead of using the thread's local copy. The reason this is done is an optimization because accessing memory by threads across CPUs and cores can be a relatively slow operation.

## Concurrency Components

Until Java 5 arrived on the scene, that was pretty much the extent of the concurrency support. You basically were given the low level functionality, but building things like thread pools (to dispatch pools of threads), or semaphores (like locks except with more than one permit), were left to the programmer.

Java 5 changed all that with the introduction of the `java.util.concurrent` package, which provided a rich set of components for handling many important concurrency design patterns. We will go through the important ones now.

## Executors Class

So far we have seen how to create threads; but threads use resources, and it would be dangerous to have programs spin arbitrary numbers of threads. To control this, there is a concept of a _thread pool_. This is a component that allocates threads from a fixed pool, and once the pool is depleted, requests for more threads block, until threads are returned to the pool. 

In Java thread pools belong to the category of ExecutorServices, and are created using a factory class called _Executors_, which contains many static methods for creating different flavors of ExecutorService. Looking at the API for ExecutorService, there are methods for invoking, shutting down, and checking status.  

![](resources/executorservice.png)

We will concentrate on construction, execute, and submit.

### Fixed Thread Pool

To construct a fixed thread pool, call `Executors.newFixedThreadPool(pool-size)`, passing in the number of threads to pool. For example:

```java
private final ExecutorService threadPool = Executors.newFixedThreadPool(5);
```

(We made it private because we want all control to be in this class, and final so that we don't lose reference to it.)

That returns a new ExecutorService, which in this example we assigned to a variable called _threadPool_.

To use that thread pool, we call the `ExecutorService.execute(Runnable)` method, providing a Runnable instance. This is usually done by supplying an anonymous inner class or Lambda expression. Here is how it looks:

<details>
<summary>Executor Service</summary>

```java
ExecutorService threadPool = Executors.newFixedThreadPool(3);
threadPool.execute(new Runnable() {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(" This is thread 0" + " iteration " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
});

```
</details>

We are creating a new `Runnable`, providing a `run()` method that iterates five times, printing out the thread number and the iteration.

Let's see what happens if we start 10 threads like that. Since we hate copy and paste code, let's take a moment to do a bit of refactoring.

<details>
<summary>ExecutorService in action</summary>

```java
public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(3);
    threadPool.execute(getRunnable(" This is thread 0"));
    threadPool.execute(getRunnable(" This is thread 1"));
    threadPool.execute(getRunnable(" This is thread 2"));
    threadPool.execute(getRunnable(" This is thread 3"));
    threadPool.execute(getRunnable(" This is thread 4"));
    threadPool.execute(getRunnable(" This is thread 5"));
    threadPool.execute(getRunnable(" This is thread 6"));
    threadPool.execute(getRunnable(" This is thread 7"));
    threadPool.execute(getRunnable(" This is thread 8"));
    threadPool.execute(getRunnable(" This is thread 9"));

}

private static Runnable getRunnable(String message) {
    return new Runnable() {
        @Override
        public void run() {
            for(int i = 0; i < 5; i++) {
                System.out.println(message + " iteration " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    };
}
```

<!-- COMMENT (Brandi): Can we put the example's output in here as well for folks who may simply be reading through the lesson?-->
</details>

Now, the pool only has three threads, but we are calling it 10 times. Looking at the output, we see that the first three threads run until complete, whence the next three run, etc, until the end. 

## How Many Threads Should I Use?

How large should you make your thread pool? If each thread pinned the CPU, then you would generally want no more than one thread per CPU. So the idea is to look at CPU utilization for one thread, and divide that number into the number of CPUs. For example, if we have 4 cores, and the utilization from one thread is 20% per core, then the number of threads for 100% utilization would be 4/.2 = 20. If you need to exceed that, then it's probably time to start thinking about upgrading hardware. But don't make rash decisions until you test things, because Java is clever about context switching and swapping, so it will still work albeit marginally slower.

## Cached Executor

We will briefly mention the cached executor. This is kind of an unlimited thread pool. You would only use this if you know you have a limited number of short lived threads, so there is no reason to limit it using a fixed thread pool. We will use this in some of our examples for simplicity, but in general there is probably more reason to use fixed thread pools.

The cached execute is created by calling

```java
ExecutorService executor = Executors.newCachedThreadPool()
```

<!-- COMMENT (Brandi): Can we add a more concrete example of a situation we'd want to use this? -->

## Futures

A _Future_ in Java is a kind of promise, that data _will_ be available. So until it has that data, our thread waits and will block if you try to access it.

To get the result of a Future, call its `get()` method, which blocks until there is something _to get_!

When you call `ExecutorService.submit()` method, it returns a `Future`.
 
### Scheduled Executor

An important flavor of ExecutorService is the _scheduled executor_. This calls its job repeatedly at fixed intervals. For example, let's say we created a job that checks for files on an FTP server, and when files are there, it processes them. Let's say our requirement is to check every 1 minute.

<details>
<summary>Scheduled Executor</summary>

```java
private void checkFtpServer(long period) {
    ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
    scheduledExecutor.scheduleAtFixedRate(checkAndProcessFile(), 0, period, TimeUnit.SECONDS);
}

```
</details>

In this example, we are creating a new scheduled thread pool, which is a kind of `ScheduledExecutorService`, and then scheduling our job at a fixed rate of `period` seconds, with an initial delay of 0. That will check for files and process them every `period` seconds, which is a good alternative to the sleep approach we have been using until now.

## Atomics Components - AtomicInteger

Imagine you are creating a hit counter for a website. The naive implementation would say something like (in pseudocode):

```java
Line 1: value = getHitCounter();
Line 2: value = value + 1;
Line 3: setHitCounter(value);
```
Now that looks all well and good, except, what happens if two threads call this code using is an inauspicious inter-collation of events.

Let's say the hit counter is currently at 1000, when the two threads attack. Now follow me closely:

```text
Thread 1 calls Line 1, gets value of 1000
Thread 2 calls Line 1, gets value of 1000 (since Thread 1 has not set the value yet!)
Thread 1 calls Line 2, increments its counter value to 1001 
Thread 2 calls Line 2, increments its counter value to 1001 
Thread 1 sets the hit counter to 1001
Thread 2 sets the hit counter to 1001
```

What just happened?? We had two threads, and we only incremented the hit counter by one!!

Now one solution to this would be to combine Lines 1, 2, and 3 into a single call, and synchronize it. 

But Java provides those semantics inherently, in its AtomicInteger class.

Let's see an example:

<details>
<summary>AtomicInteger Example Code</summary>

```java
package com.generalassembly.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerLesson {
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final AtomicInteger hitCounter = new AtomicInteger(0);

    private void hit() {
        int value = hitCounter.incrementAndGet();
        System.out.println(value);
    }

    private void spinThread() {
        executor.execute(() -> {
            for (int i = 0; i < 100; i++) {
                hit();
            }
        });
    }

    public static void main(String[] args) {
        AtomicIntegerLesson lesson = new AtomicIntegerLesson();
        lesson.launch(lesson);
    }

    private void launch(AtomicIntegerLesson atomicIntegerLesson) {
        for(int i = 0; i < 100; i++) {
            atomicIntegerLesson.spinThread();
        }
    }
}
```

<!-- COMMENT (Brandi): Can we add more explanations here? For example, definition of the word atomic, explanation of the lambda/arrow -> syntax. -->
</details>

In this example, the hit counter starts at 0. We are launching 100 threads, having each one hit the thread counter 100 times. If all goes well, the hit counter should reach 10,000. If there is even a single race condition, we will never see 10,000.

Running that application yields the log output:

```text
1
6
8
9
5
11
3
...
9996
9997
9998
9999
10000

Process finished with exit code 0
```
The good news is we reached 10000!

Don't be thrown by the fact that some of the numbers appear out of sequence; that's just the way the output was ordered by the thrashing threads. If you study the output carefully, you will see there is exactly one of each number from 1 to 10,000. 

## ReadWriteLock

There are many more components in the `java.util.concurrent` package, each implementing some valuable concurrency design pattern. We will look at one more, but it pays to study the documentation to see the full treasury.

 The last concurrency component we will look at is the `ReadWriteLock`, which solves a common problem.
 
 Let's say you have many readers of a certain set of data. There are also writers that change the data, and the changes must be atomic, in the sense that we want to ensure that readers can't read the data until all of it is changed. What would be an example of that?
 
 Well, a very common example would be a stock portfolio owned by a hedge fund. There's money on the line, so we've got to get this right!
 
Let's say we have the following:
 
 |Ticker|# of Shares|Price|Total|
 |---|---|---|---|
 |FB	|10,000	|$176.00 	|$1,760,000.00  |
 |ABC	|5,000	|$98 	|$490,000.00    |
 |MSFT	|700	|$131.00 	|$91,700.00     |
 |	|	|Total 	|$2,341,700.00      |

Now the fund constantly trades shares, and prices constantly change. Let's say the fund updates the portfolio shares and prices every minute. 
  
Let's say they just sold 500 shares of FB at $176, and used the money to buy 1000 shares of ABC, an equivalent value. (Assume the price of each share did not change at all, only the number of shares.)
  
 So in reality, the value of the portfolio hs not changed at all; $98,000 of FB was sold, and the same amount of ABC was bought. Zero net change in portfolio value.
 
 But imagine that a reader came in to calculate the portfolio value just while the writer was updating.
 
 The writer sold the 176,000 of FB, and the reader comes in. The reader is a fast thread, so it reads FB (down 176,000), ABC (unchanged, because the writer did not get to it yet), and MSFT (which was not changed at all) So in that scenario, the reader will see a portfolio that is $176,000 lower than the actual value. Hmm... what should we do?
 
 Now, we might try to synchronize, which should cure the problem of data consistency, but by doing so, the writers may never get a chance to write, since there might *always* be readers holding the lock. Our hedge fund manager is not on board.
 
 The solution for both data consistency and a happy boss in this case is to use a `ReadWriteLock`.
 
 The way this works is each reader thread grabs a read lock. These do not block each other at all.
 
 When a writer gets a write lock, the writer blocks until there are no more readers! And no _new_ readers can get in, as long as a writer is waiting for a write lock.
 
 Here is an animation to illustrate that
 
 (The reader threads are shown with arrow heads, and the writers have trapezoid heads. Also, the state is color coded - green is running, and white is waiting.)
 
 ![](resources/read-write-lock.gif)

Notice in the animations, that as long as there are no writers, readers come and go freely.

As soon as a writer comes along, new readers and writers must wait.

Once the last writer does its job, all of the waiting readers are free once again to perform their computations.

To create a new `ReadWriteLock`, call exactly that:

```java
ReadWriteLock rwl = new ReadWriteLock();
```

> Usage for reads: To grab a read lock, call `rwl.readLock().lock()`, and to relinquish the read lock, call `rwl.readLock().unlock()`.

> Usage for writes: To grab a write lock, call `rwl.writeLock().lock()`, and to relinquish the write lock, call `rwl.writeLock().unlock()`.

Let's look at an example, first without the read write lock, then again with.

Study the difference between the two versions.

<details>
<summary>ReadWriteLock</summary>

```java
package com.generalassembly.concurrency;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Holding class contains the ticker, shares, and prices of a stock in a portfolio
 */
class Holding {
    private String ticker;

    public int getShares() {
        return shares;
    }

    private int shares;

    public void setShares(int shares) {
        this.shares = shares;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public Holding(String ticker, int shares, double price) {
        this.ticker = ticker;
        this.shares = shares;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

public class ReadWriteLockLesson {
    private final List portfolio = new ArrayList();
    ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        new ReadWriteLockLesson().launch();
    }

    // create our read write lock
    ReadWriteLock rwl = new ReentrantReadWriteLock();

    private void launch() {
        createPortfolio();
        for (int i = 0; i < 10; i++) {
            // create 10 reader threads, that continue without pause
            createReaderThread();
        }
        createWriterThread();
    }

    private void createReaderThread() {
        executor.execute(() -> read());
    }

    private Map<Double, Integer> values = new ConcurrentHashMap<>();

    private void read() {
        while (true) {
//            rwl.readLock().lock();
            double value = 0;
            for (int i = 0; i < portfolio.size(); i++) {
                Holding holding = (Holding) portfolio.get(i);
                double holdingValue = holding.getShares() * holding.getPrice();
                value += holdingValue;
            }
            synchronized (ReadWriteLockLesson.class) {
                int size = values.size();
                values.put(value, 0);
                if (values.size() != size) {
                    // only save the value if it is changed. If the program works, there should only ever be one value
                    System.out.println(values);
                }
            }
//            rwl.readLock().unlock();
        }
    }

    private void createWriterThread() {
        executor.execute(() -> write());
    }

    // for the fun, we will first sell FB and buy ABC, then we will buy FB and sell ABC
    volatile int plusMinus = 1;

    private void write() {
        while (true) {
//            rwl.writeLock().lock();
            Holding fb = (Holding) portfolio.get(0);
            fb.setShares(fb.getShares() - plusMinus * 500);
            Holding abc = (Holding) portfolio.get(1);
            abc.setShares(abc.getShares() + plusMinus * 1000);
            plusMinus *= -1;
//            rwl.writeLock().unlock();
            try {
                // sleep briefly to give readers a chance to read
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Create a one time portfolio
     */
    private void createPortfolio() {
        portfolio.add(new Holding("FB", 10_000, 176.00));
        portfolio.add(new Holding("ABC", 5_000, 88.00));
        portfolio.add(new Holding("MSFT", 700, 131.00));
    }
}

```
</details>

That produces output:

```text
{2291700.0=0}
{2379700.0=0, 2291700.0=0}
{2379700.0=0, 2203700.0=0, 2291700.0=0}
```

So we see the portfolio has produced three different values, depending on the inter-collation of the calls.

Now, uncomment the `ReadWriteLock` logic, and try again:

```text
{2291700.0=0}
```

Doing that, we see there is a single value, as we had hoped. 

## Summary

<!-- COMMENT (Brandi): Summary should be a little better. For now I just reiterated the lesson objectives, but maybe we include a brief how-to on how to know which tool to use when. A quiz maybe? -->

Wow, we learned a lot in this lesson! To recap, here's what we hope you can discuss:

  - Why use concurrency?
  - How to create a Thread: 2 Different Ways!
    - Extending Thread
    - Implement the Runnable interface
  - Estimate how many threads to use
  - Usage of the synchronized keyword
  - Locking and associated thread states
  - Signalling threads using wait/notify - synchronization
  - Concurrency components:
    - Executors and ThreadPools
    - Execute vs. Submit
    - Atomics components - AtomicInteger
    - ReadWriteLock
