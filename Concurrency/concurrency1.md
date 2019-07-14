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
  - Reentrant Native locks
    - _Note: in computing the correct form is "reentrant"locks, without a hyphen_  
  - Signalling threads using wait/notify - synchronization
  - Concurrency components:
    - Executors class
        - ExecutorService interface
        - Fixed Thread Pool
        - Cached Thread Pool
        - Scheduled Executor
    - ExecutorService.execute vs. ExecutorService.submit
    - Atomics components - AtomicInteger
    - ReadWriteLock

**Activities:**

- Instructor-led code-along: Start ten threads in a loop
- Instructor-led code-along: Portfolio pricing method
- Student exercise: Create 2 Threads to Track Free Memory, Total Memory, and Time.
- Sizing: 5 (biggest)

## Fundamentals of Concurrency

Until now, we have covered basic programs and program flow. We have seen how you can control the flow of a program using things like `if` statements and `for` loops. However all of the programs we have seen so far have been _synchronous_. One thread running the program step-by-step serially (consecutively) from beginning to end.

However today's computers have powers far beyond what we have seen or used thus far. Commodity computers have multiple CPUs, dozens or hundreds of cores, and even within a single core, Java spins threads to perform parallel processes.

## Why Use Concurrency?

Why would we ever need to have multiple threads performing concurrent work?

Consider a web application with dozens (or millions!) of concurrent users. We would not expect each user to wait in line until the previous user is done; rather we 
want to handle these requests _concurrently_.

Or let's say our application requires a lot of processing such as database queries, file reads and writes, and calling other services over a network, where there's lots of I/O. Do we 
want each outgoing request to wait for the other to return before the next one starts? Wouldn't it be better to have all of our requests process _concurrently_?

For these reasons, the designers of Java made the decision to include concurrency in the core JDK, making it perhaps the first language to do so.
  
## How to Create a Thread

Threads are really easy to create, but with great simplicity comes great responsibility! We will discuss some of the common pitfalls in a little while. For now let's jump straight into creating and starting our very first thread.

There are two popular ways to create a thread, and you will see both heavily used:

* Override the Thread class, implementing the `run` method
* Implement the Runnable interface, implementing its `run` method, and pass that Runnable to a Thread

Let's use both of these approaches to start a thread that is tasked with the job of writing the current time to System output every 2 seconds

## Override the Thread Class

In this approach, we will create a new class that _extends_ `Thread` and _overrides_ that thread's `run()` method. 

The `Thread` class has a method called `public void run()` that is called implicitly when you _start_ your thread. 

One we overrride `Thread.run()` we will call our class's `start()` method, which will start the thread and implicitly call `run()`. 
 
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

Now comes the meat. We overrode the `Thread` class's `run()` method. The first thing the run method does is to declare a _try-catch_ block. 

We surround code that can throw an exception with the try portion, and make some code to gracefully handle the exception in the catch portion. More on try-catch in a moment!

`Thread.run` normally does its job and then exits, terminating the Thread. However in our case, we don't want it to just print once and exit; rather we want it to keep printing, so we use a _while_ loop to continually execute our output statement. 

Next we create a new `LocalTime` object, which refers to the current time (`now()`) at the time of instantiation.

Since all of that is happening in a _while_ loop, it will continue to loop forever without pause. But the requirement was to display the time every 2 seconds, so we must sleep for 2 seconds between loop iterations. To do that we call the _Thread.sleep()_ method, supplying the number of milliseconds to sleep, in this case 2 seconds is 2000 ms, so we call `Thread.sleep(2000)`. 

Finally we define the `main()` method, which launches our program. It _starts_ our new thread by calling the `start()` method, (which implicitly calls the `run()` method, in a new thread.)

> Note: We get the implementation of the `start()` method from the `Thread` class which we're extending. That's why we don't have to write it ourselves!

### What About That Try-Catch Block?

Coming back to the try-catch block, notice that `Thread.sleep()` throws an `InterruptedException`. That exception is thrown when the thread's `interrupt()` method is called (usually by frameworks or application servers), to initiate a smooth shutdown of the threads. Since `InterruptedException` is a checked exception, by definition, you _must_ catch it. One side-effect of catching an `InterruptedException`, is that the thread's _interrupt_ flag is reset, meaning that it is no longer interrupted, which means that the container shutdown will be aborted. To propagate the interrupt back to the calling thread (so that the application server or container can continue its clean shutdown), we must set the interrupt flag once again, which is why we call `Thread.currentThread().interrupt()`. It's not critical to understand this; just remember to follow the recipe: whenever you catch an InterruptedException, call `Thread.currentThread().interrupt()` before exiting the catch block.

Notice that we enclosed the while loop _inside_ the try-catch. A common mistake even advanced programmers make is to do the opposite, and they enclose the try-catch _inside the while loop_. Why is that wrong?

Well think about it. If the try catch would be inside the while, then imagine what would happen if someone calls the interrupt method. In that case, the exception would trap the interrupt, set the interrupt flag, and then loop again! The program would never end, even after an interrupt! So we fix that by including the while _inside_ the try catch. Now if an interrupt occurs, the while loop exits, and the catch block takes over, sets the interrupt flag, and exits, returning control to the caller.

This is a _very_ common idiom in Java concurrency: execute some activity in a loop, sleep, and catch the `InterruptedException` outside the loop. Now you know!

Let's execute that program (Using the keyboard shortcut _Ctrl-Shift-F10_ <!-- I recommend that we reserve the backticks for code, not keyboard shortcuts --> in IntelliJ.) Notice that our `while(true)` statement will never exit, so the only way to exit this program is to _kill_ it (_Ctrl-F2_ or _Command-F2_ in IntelliJ), or pull the plug!

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

Once the main thread calls our Thread `start()` method, it launches a new thread, that runs in its own time. Then our main thread resumes, printing out the "Thread was started" message. Meanwhile back at the ranch, our new Thread was preparing itself, then it got into action and began its business of printing the current time.

## The Runnable Interface

We have seen one way to create a thread by extending the `Thread` class and overriding its `run()` method. The second approach is to recognize that the `Thread` class has a constructor that accepts a _Runnable_ instance. Runnable is an interface with one method - `public void run()`. Using this approach, you construct a new Thread instance by passing a Runnable instance to the constructor, then you call your Thread's _start_ method, which will call your Runnable in a new Thread.

> Tip: A "Runnable" instance is an instance of a class that _implements_ the `Runnable` interface.

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

There is another common idiom you should be aware of for creating threads using the `Runnable` interface, and that is using _anonymous inner classes_. We won't be looking at this approach in much detail, but you will see the syntax in your travels, and you should be aware of it. The idea behind anonymous inner classes is that we should not need to assign a name to a class that we are only ever going to use once, in a very limited context. For such cases, Java allows you to create _anonymous inner classes_, which are declared and used in-line, without having to name them.

> Aside: You may have seen anonymous functions if you've used a language like JavaScript (no relation to Java). The motivation for doing so here is exactly the same!

In our second thread example, we created a new class called `TimeRunnable`, and then instantiated that and passed in the instance to the `Thread` class. 

Contrast the following, which uses an anonymous inner class, to the previous `TimeRunnable` version:

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
* The first thread logs the system time every six seconds. 
* The second thread logs the total memory and free memory every 1.5 seconds.

CHALLENGE: Most seasoned developer are allergic to copy and paste code, so try not to use any duplicated code! 

> Hint: In order to get the current time, call `LocalTime.now()`. To get the free memory, call `Runtime.freeMemory()`. To get the total memory, call `Runtime.totalMemory()`. 

<details>
<summary>Solution: Memory and Time Threads</summary>

<!-- COMMENT (Brandi): Error. This code prints the same string every time since the string message is calculated just once for each call to spinThread. You need to use a lambda expression here. -->
<!-- Ah, so sorry, you are most correct! Corrected. I assume they didn't get to Lambdas yet, so using classes -->
```java
public class FreeMemoryAndTime {
    public static void main(String[] args) {
        spinThread(new MemoryPrinter(), 6_000);
        spinThread(new TimePrinter(), 1_500);
    }

    interface Printer {
        void printMessage();
    }
    static class TimePrinter implements Printer {

        @Override
        public void printMessage() {
            System.out.println("Current time: " + LocalTime.now());
        }
    }
    static class MemoryPrinter implements Printer {

        @Override
        public void printMessage() {
            System.out.println("Free memory: " + Runtime.getRuntime().freeMemory() + " Total memory: " + Runtime.getRuntime().totalMemory());
        }
    }
    private static void spinThread(Printer printer, long delay) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    printer.printMessage();
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

Now it is entirely possible that our time-printing thread could have started before our main thread got around to printing the "Thread was started" message, in which case "Thread was started" would have printed as the second message instead of the first. Each thread operates independently, and so the intercolation is unpredictable. In plain English, this basically means we can't know the order they will happen ahead of time. This is known as a **race condition**, and sometimes that makes testing threaded code very difficult! A race condition essentially means that different threads execute independently, and so they can appear to randomly execute their steps in different orders.

This can have some interesting side effects, when trying to assign and access a shared variable from different threads.

![](resources/race-condition.jpg)
<!-- https://image.slidesharecdn.com/intro2concurrency-180306214554/95/brief-introduction-to-concurrent-programming-15-638.jpg -->

## Instructor-led code-along: Race Conditions

<!-- Note to instructor, see the class RaceCondition -->

Let's set up two threads, which each change the value of a shared variable, and then inspects the variable to see if it is the value as set.

Let's set up two threads to set and then check the value. Here is the first. The second is similar


<!-- code in com.generalassembly.concurrency.RaceCondition -->
```java
public class RaceCondition {
    private long someSharedVariable;
    private void launch() {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    someSharedVariable = 0;
                        if (someSharedVariable != 0) {
                            System.out.println("huh? Expected " + 0 + " but got " + 0 + "!");
                        }
                    }
                }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    someSharedVariable = -1;
                        if (someSharedVariable != -1) {
                            System.out.println("huh? Expected " + -1 + " but got " + -1 + "!");
                        }
                    }
                }
        });
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        new RaceCondition().launch();
    }
}
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

Whoa! Don't worry, there is actually a logical explanation for this behavior! You have to realize that thread1 is continually setting `someSharedVariable` to 0 and thread2, operating at the same time, is trying to set it to -1. At any given time we don't know who touched the shared variable last! So it's less of a logical contradiction so much as two siblings who are both fighting over who gets to play with a toy, but it's pretty clear even from this small example that threading and concurrency can lead to some trippy behavior!
<!-- leave synchronized as lower case. That is the name of the keyword, upper case won't compile -->
## The `synchronized` Keyword

When you want to make absolutely sure that two threads cannot execute at the same time, you can _lock_ those calls. The low level way to do that is using the _synchronized_ keyword. That was in fact the only way, until Java 5, and it is very common. The other way is using the `java.util.concurrent` package, introduced in Java 5. We'll introduce that later.

Let's take a look at that _synchronized_ keyword.

When you declare methods as `synchronized`, then that ensures that _only one thread at a time_ can be executing any of the synchronized methods on any given object. The reason this works, is that every object instance has built in to it what is called an "intrinsic lock". When a thread enters a synchronized method, it automatically grabs that lock. If any other threads try to access a synchronized method while one thread already has the lock, then the other threads must _block_ until the current thread relinquishes the lock by exiting the synchronized block. When a thread is blocked, there is absolutely no way for it to move at all, until the lock is relinquished or the program ends. If many threads are waiting for the lock on an object, there is no guaranty that they will acquire the locks fairly, and it is possible that any given thread will have to wait indefinitely, depending on the volume of threads. The bottom line is be careful how you synchronize, and try to visualize all of the execution paths. 

**Seems a little unfair doesn't it?**

![](https://res.cloudinary.com/briezh/image/upload/v1561421042/DMV-cartoon_x2twcy.jpg)

<!-- COMMENT (Brandi): I don't know who to credit the comic to, and honestly just replace it with something else light-hearted, but this content is DENSE. Having visual aids, examples, or humor will go a long way toward breaking up the monotony. -->
<!-- it's cute - we need it! I added another one above, for race conditions -->

Have you ever been in a grocery line trying to buy one item behind someone buying 100 items? Or have you been waiting in a customer service line while whoever is in front of you had a complex problem? You were left waiting indefinitely, and if the person in front of you felt like taking more time you'd have no choice but to just wait and wait! It turns out, we don't like our Java threads to wait on hold either! Java also has a _ReentrantLock_, that allows you to lock in a fair way. For more information, see https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html.

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

When we create an object solely for the purpose of using its lock, we call such an object a "_mutex_". Be sure that when you are using an object as a mutex, that variable is not going to change its value, because the lock belongs to the value not the variable. Generally you want to declare those mutex variables to be _final_ to prevent any reassignment, and capitalize the entire variable name, to indicate that is it final, as we mentioned in our "Data Types and Variables" lesson. 

Let's modify our class above to use synchronized and see how that works:

<details>
<summary>synchronized</summary>

<!-- COMMENT (Brandi): I feel like MUTEX needs more explanation here and may be a stumbling point for students. Why is it capitalized? What does it do? Why do we use it with synchronized as opposed to the variable we're trying to protect? These are all questions we can anticipate. -->
<!-- (Victor) I have added some more clarity, and I added the discussion about all caps for final variables in the Data Types lesson -->

<!-- code in com.generalassembly.concurrency.SynchronizedRaceCondition-->
```java
    private long someSharedVariable;
    private final Object MUTEX = new Object();
    private void launch() {

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

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MUTEX) {
                    while (true) {
                        someSharedVariable = -1;
                        if (someSharedVariable != -1) {
                            System.out.println("huh? Expected " + -1 + " but got " + -1 + "!");
                        }
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }

```
</details>

In this code, we declare a _mutex_ object. Note that the variable name `MUTEX` is capitalized to indicate that it is final.

This is a common practice - we declare a mutex variable, then all code that accesses the shared variable synchronizes on the mutex, ensuring that those accesses cannot run concurrently, preventing the race condition.

Thankfully, when we synchronize, our updates and accesses are guaranteed to occur atomically, in the same thread, and so we see there is none of the surprised output like we saw in the initial version.

> Atomic is a common word in computer science. It refers to a set of two or more events that all must either happen or not happen. A common example would be a credit card transaction... a user goes on line, orders a book, and her credit card is denied. Or, her credit card is debited, but the book is out of stock.    
 Now you might ask - why not just check that the book is in stock and that there is enough available credit, before executing the transaction? But when you think about it, that won't help, because what happens if just after you check, the last copy of the book gets sold, or the last dollar of credit gets used up? For these cases we need to ensure that the events are _atomic_ - they happen all or nothing.

The two events - ordering the book and debiting the credit card, must occur atomically - you don't ever want a situation where the merchandise gets shipped, but the credit card was rejected, or arguably worse - where the credit card was billed but the order was never shipped. Atomic transactions ensure that in a multi-step transaction such as this, either all of the steps of the transaction complete, or none of them do, (i.e. they all "roll-back").

Note that using the synchronized method approach, if you have different instances of that class, it is entirely permissible for different threads to access those methods on _different_ object instances. If you want to lock a method across _all_ object instances of that class, then you can make that mutex static. There are a few variations, but in this course we will not look further into that approach.
 
## Signalling Threads Using wait/notify/notifyAll

We learned about grabbing an intrinsic lock on an object.

There is a useful idiom we need to learn, that will help us in situations where some Thread 1 must _wait_ for another Thread 2 to complete, and then continue. In such cases we can use Java's built-in _wait-notify_ mechanism. Methods `wait` and `notify` are both from the `Object` class, so every Java class inherits those, and they should never be overridden. 

### Object.wait()

If a thread owns the intrinsic lock on a mutex, and it wants to wait for some condition to be true before resuming, then it can call the `wait` method on the mutex, which will send the calling thread into a _waiting_ state. There are actually two flavors of `wait`. The first takes no arguments, and will wait forever (until notified, as we will see shortly.) The second takes a long argument, which represents the number of milliseconds to wait. If the time lapses, our thread exits the wait state, and is now runnable.(Passing in a value of 0 is equivalent to the no-parameter version, and will wait forever.)

We said earlier that when a thread grabs an intrinsic lock (by entering a synchronized block) then no other thread can enter that synchronized block (or any block that is synchronized on that object.)

We need to refine that statement slightly. When a thread is in the wait state, it _temporarily forfeits the lock_ it is waiting on, and the lock becomes available for another thread to take. 

So if a thread is in the timed-wait state and the time lapses, we said our thread exits the wait state, and is now runnable. Again we need to refine that slightly, because if another thread is already holding the lock then when our thread exits the wait state, then the thread enters the _blocked state_, until the lock becomes available again.

![](resources/synchronized.gif)

### notify/notifyAll

Now let's say our thread is moving merrily along, until it comes to a point where it needs to wait for some data to be available from another thread. This is a very common concurrency idiom, and Java provides the `notify` and `notifyAll` keywords to solve it.

It's easier to see it in an example. Let's say we have two threads, one that is reading file data (the _reader_ thread, and another that is formatting and displaying that data (the _writer_ thread).

Now there is no reason for the writer to write anything, until the reader has read something. So the reader must have a way to signal to the writer that data is available. This is where wait/notify comes in handy.

Copy and paste the following code into a single Java source file, named _ReaderWriterExample_ in package com.generalassembly.concurrency. 

This program will start two threads. The first will read three popular books in turn, and then notify the second thread to start processing. The second thread will wake and display the contents of each:

<details>
<summary>Wait-Notify</summary>

```java
package com.generalassembly.concurrency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReaderWriterExample {
    private final Object MUTEX = new Object();
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
                        synchronized (MUTEX) {
                            MUTEX.notify();
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
                        synchronized (MUTEX) {
                            MUTEX.wait();
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

In our example, the call to `notify` wakes up the waiting thread. This works great because we only created one waiting thread. But what happens if you have many waiting threads? A call to notify will wake up one of the waiting threads at random, which is often what you want. However there are times when you want all waiting threads to wake up, for example where you have many threads, each of which performs some function, where order does not matter. For example, perhaps we have one thread that enriches new data, another thread that captures the new data in a ledger, and another thread that notifies a librarian that new data has arrived. In that case you would call `notifyAll`, which will notify each and every waiting thread to wake up. 

(The observant student will notice that all of these threads are waiting in the same synchronized block, so how can they all wake at the same time, given what we said that only one running thread may ever hold a synchronized lock? The answer is they can't. `notifyAll` will transition all waiting threads from the waiting state to the blocked state, and then wake up one thread at a time, at random. As each thread relinquishes the lock, another thread will be selected by the thread scheduler at random to become runnable, until every thread has had a chance to wake up.)

Incidentally, notice the keyword `volatile`, which is used before the declaration of the index variable.

This is a deep concept. The JVM will generally make a local copy of variables that are used by each thread, and the thread has the right to assume that the value will not be changed by any other thread.

If one thread modifies that variable, due to a JVM optimization, Java makes no guarantees that that the change will be seen by another thread, unless we mark the variable `volatile`, which tells the JVM to read the value of the variable on every access, instead of using the thread's local copy. The reason this is done is an optimization because accessing memory by threads across CPUs and cores can be a relatively slow operation.
