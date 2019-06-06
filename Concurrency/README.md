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
want each outgoing request to wait for the other to return before the next one starts? Wouldn't it be better
to have all of our requests process _concurrently_?

Or consider an application that needs additional input for its processing. A naive approach would be to save all of the state of the process, terminate the process, get the additional data, and then restart the original process. That approach requires a lot of housekeeping overhead, keeping track of things, especially if there are many such things going on. Wouldn't it be better if the process could spin a concurrent process to get the required data, without stopping itself, and then continuing when the data is available?

  All of these reasons and more explain why the designers of Java made the decision to include concurrency in the core JDK, making it perhaps the first language to do so.
  
## How to create a Thread
Threads a really easy to create. But with great simplicity comes great responsibility, and we will discuss some of the traps in a little while. For now let's see how to create a thread.


## The Runnable interface
## How many threads are correct?
## synchronized keyword
## Native locks are reentrant 
## Signalling threads using wait/notify - synchronization
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

  
  
