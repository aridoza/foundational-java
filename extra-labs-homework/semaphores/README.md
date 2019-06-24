---
title: Concurrency in Java and Semaphores
type: Homework
duration: "2:00"
creator:
    name: Syed Salahuddin
    city: NYC
---


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Semaphores

> ***Note:*** _This can be a pair programming activity or done independently._

## Exercise

Concurrency in Java is handled by threads, the paradigm itself is called `Multithreading`.

> Multithreading as a widespread programming and execution model allows multiple threads to exist within the context of a single process. These threads share the process' resources but are able to execute independently. The threaded programming model provides developers with a useful abstraction of concurrent execution.

Multithreading can get pretty complex, pretty quickly. Your program will be fighting
for limited resources. We need `Semaphores` to help us manage that complexity

>Think of semaphores as bouncers at a nightclub. There are a dedicated number of people that are allowed in the club at once. If the club is full no one is allowed to enter, but as soon as one person leaves another person might enter.

#### Requirements

- Create a simple chat application using threads
- Watch the MIT lecture on Semaphore


**Bonus:**
- Create networked game after you've completed your chat application.
- Use Processing to add visuals
- Use a Semaphore in your chat

#### Deliverable

Using the following resources as a guide create a chat application:
 - http://math.hws.edu/javanotes/c12/s5.html

![Example Image](http://math.hws.edu/javanotes/c12/hub-and-client-threads.png)

Once you have submitted your hw please watch this video on `Semaphores`:
- https://www.youtube.com/watch?v=TVkQ1VeRKt4
