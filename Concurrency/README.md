# Multi-threading/Java Concurrency

Students will: 
* Understand the fundamentals of concurrency in Java
* Be able to create and launch a thread using Thread or by subclassing Runnable interface
* Understand how to deal with thread contention
* Estimate how many threads to use for an application
* Understand basic concurrency components introduced in Java 5

- Topics:
  - Why concurrency
  - How to create a Thread
  - The Runnable interface
  - How many threads are correct?
  - synchronized keyword
    - Native locks are reentrant 
  - Signalling threads using wait/notify - synchronization
  - Concurrency components:
    - Executors class
        - ExecutorService interface
        - Fixed Thread Pool
        - Cached Thread Pool
        - Scheduled Executor
    - Callable interface
    - execute vs. submit
    - Atomics components - AtomicInteger
    - ReentrantLock component
    - ReadWriteLock

- Activities:
   - Instructor lead - Start ten threads in a loop. 
   Make each write an integer to the console. See how the integers are not printed in order
   - Instructor lead - Portfolio pricing method
   - Student exercise
   Students will be able to 
      - Activity: 
- Sizing: 5 (biggest)

### Fundamentals of Concurrency
Until now, we have covered basic programs and program flow. We have seen how you can control the flow
of a program using things like `if` statements and `for` loops. However all of the programs we have 
seen so far have been synchronous. One thread running the program serially from beginning to end.

However nowadays computers have powers far beyond what we have seen thus far. Today's commodity computers have multiple CPU's
dozens or hundreds of cores, and even within a single core, Java spins threads to perform 
parallel processes.

# Why concurrency?
Why would we ever need to have multiple threads performing concurrent work?

Consider a web application with dozens (or millions!) of concurrent users. 
We would not expect each user to wait in line until the previous user is done; rather we 
want to handle these requests _concurrently_.

Or let's say our application requires a lot of processing such as database queries, file reads and writes, and URL connection handling, where there's lots of IO. Do we 
want each outgoing request to wait for the other to return before the next one starts? Wouldn\'t it be better
to have all of our requests process _concurrently_?

Or consider an application that needs additional input for its processing. A naive approach would be to save all of the state of the process, terminate the process, get the additional data, and then restart the original process. That approach requires a lot of housekeeping overhead, keeping track of things, especially if there are many such things going on. Wouldn\'t it be better if the process could spin a concurrent process to get the required data, without stopping itself, and then continuing when the data is available?

  All of these reasons and more explain why the designers of Java made the decision to include concurrency in the core JDK, making it perhaps the first language to do so.
  
## How to create a Thread
Threads a really easy to create. But with great simplicity comes great responsibility, and we will discuss some of the traps in a little while. For now let's create and start a thread.

There are two popular ways to create a thread, and you will see both heavily used:
* Override the Thread class, implementing the `run` method
* Implement the Runnable interface, and pass it to a Thread

Let's use both of these approaches to start a thread that writes the current time to System output every 5 seconds

## Override the Thread class
The _Thread_ class has a method called `public void run()` that is called implicitly when you _start_ your thread. 
In this approach, we will create a new class that extends Thread.
 and override the `run` method. Then we will call our class's `start` method, which will start the Thread and implicitly call 
 `run`. 
 
 Be sure to get this clear... you _implement run()_ but you _call start()_! Let's see an example:
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
There's  lot going on here, so let's talk through it.
First we imported the LocalTime class, a convenient class for capturing time information. 

Next we implemented our TimeLogger class, which _extends Thread_.

Now comes the meat. We override the `Thread` class's `run` method. 
The first thing the run method does is to declare a _try catch_ block. Let's come back to that until after we review the rest of the code.

`Thread.run` normally does its job and then exits, terminating the Thread. However in our case, we don't want it to exit, we want it to keep printing, so we use a _while_ loop to continually execute our output statement. 

Next we create a new LocalTime object, which refers to the current time (`now()`) at the time of instantiation.

Since all of that is happening in a while loop, it will continue to loop forever without pause. But the requirement was to display the time every 5 seconds, so we must sleep for 5 seconds between loop iterations. To do that we call the _Thread.sleep()_ method, supplying the number of milliseconds to sleep, in this case 2 seconds is 2000 ms, so we call `Thread.sleep(2000)`. 

Finally we define the _main_ method, which launches our program. It _starts_ our new Thread by calling the `start()` method, (which implicitly calls the _run_ method, in a new Thread.)

Coming back to the try-catch block, notice that `Thread.sleep()` is declared to throw an _InterruptedException_. An InterruptedException is thrown when the thread's `interrupt()` method is called. This is usually done by frameworks or application servers, to initiate a smooth shutdown of the threads. Since InterruptedException is a checked exception, it must be caught. One side-effect of catching an InterruptedException, is that the thread's _interrupt_ flag is reset, meaning that it is no longer interrupted. To propagate the interrupt, we must set the interrupt flag once again, which is why we call `Thread.currentThread().interrupt()`.

Notice that we enclosed the while loop _inside_ the try catch. A common mistake even advanced programmers make is to do the opposite, and they enclose the try catch _inside the while loop_. Why is that wrong?

Well think about it. If the try catch would be inside the while, then imagine what would happen if someone calls the interrupt method. In that case, the exception would trap the interrupt, set the interrupt flag, and then loop again! The program would never end, even after an interrupt! So we fix that by including the while _inside_ the try catch. Now if an interrupt occurs, the while loop exits, and the catch block takes over, sets the interrupt flag, and exits, returning control to the caller.

This is a _very_ common idiom in Java concurrency - execute some activity in a loop, sleep, and catch the InterruptedException outside the loop.

Let's execute that program (Ctrl-Shift-F10 in IntelliJ.) Notice that our `while(true)` statement will never exit, so the only way to exit this program is to _kill_ it (Ctrl F2 or Command F2 in IntelliJ), or pull the plug!

The output looks like this:

```text
Thread was started
11:57:16.913
11:57:18.963
11:57:20.964
11:57:22.964
```
Do you see anything unusual there?

Notice that in our _main_ method, the first thing we did was to start our thread, and then secondly, we printed out "Thread was started". However in the output, we can see that "Thread was started" was logged first, even though it was declared last!

Why did that happen? Well, once we called our Thread _start_ method, we launched a new Thread, that runs in its own time. Then we resumed our main thread, which printed out the "Thread was started" message, and finally, our new Thread got into action and began its business of printing the current time.

## The Runnable interface
That's how you create a Thread by overriding the Thread class. Perhaps the more common approach is to recognize that the Thread class has a constructor that accepts a _Runnable_ instance. Runnable is an interface with one method - `public void run()`. Using this approach, you construct a new Thread instance by passing a Runnable instance to the constructor, then you call your Thread's _start_ method, which will call your Runnable in a new Thread.

Here is the program:
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
This approach is roughly similar to the previous approach. 
We create a class that implements Runnable. We construct a new instance, supplying a run method, and pass that to the Thread constructor. Then we start the thread. Hitting Ctrl-Shift-F10 to execute that, produces the same result as the first approach.

There are several advantages to the Runnable approach, one being that it can be slightly less overhead, and perhaps more important, that there are other ways to launch Runnable instances using frameworks such as Java's built in _Executors_ framework, which we will see soon.

## Using anonymous inner classes
There is another common idiom you should be aware of for creating threads using the Runnable interface, and that is using _Anonymous inner classes_. We won't be looking at those in detail, but you will see the syntax in your travels, and you should be aware of it.

In our second thread example, we created a new class called TimeRunnable, and then instantiated that and passed in the instance to the Thread class. There is some boiled plate code there - we should not need to assign a name to a class that we are only ever going to use once, in a very limited context? 

For such cases, Java allows you to create _Anonymous inner classes_, which are declared and used in-line, without having to name them. Contrast the following to the previous TimeRunnable version:
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
The syntax looks a bit tricky at first glance. Basically the first line of the main method is declaring a new Runnable instance. But we are calling an anonymous constructor `new Runnable()` that is formed by implementing the `Runnable.run()` method.

To highlight the difference, compare the two versions. You can copy and paste the first version into IntelliJ, then copy the second version into you clipboard, right click and choose "Compare to Clipboard":

![](resources/compare-to-clipboard1.png)
![](resources/compare-to-clipboard2.png)

Just study the two lines that are different, and be aware of that syntax.

## Race condition
Now it is entirely possible that the thread could have started before our main thread got around to printing the "Thread was started" message, in which case "Thread was started" would have printed as the second message instead of the first. This is known as a race condition, and sometimes that makes testing threaded code very difficult! A race condition essentially means that different threads execute independently, and so they can appear to randomly execute their steps in different orders.
## synchronized keyword
## Native locks are reentrant 
## Signalling threads using wait/notify - synchronization
## How many threads are correct?
### Concurrency traps - contention, non-atomic, volatile
## Concurrency components:
## Executors class
### ExecutorService interface
### Fixed Thread Pool
### Cached Thread Pool
### Scheduled Executor
## Callable interface
## execute vs. submit
## Atomics components - AtomicInteger
## ReentrantLock component
## ReadWriteLock

  
  
