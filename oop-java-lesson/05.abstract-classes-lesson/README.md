# Abstract classes

Students will be able write an abstract classes and understand when to use one. 

- Topics:
   - Abstract classes
- Activity: Monitors
- Sizing: 1 (smallest)

There are times when you want to implement _some_ behavior in a class, but you want to leave it to the developer to implement some other behavior.

For example, in our earlier example of the `Shape` class, we subtly provided a default implementation for the `getCircumference()` and `getArea()` methods.

```java
public class Shape {
    public double getCircumference() {
        return 0;
    }

    public double getArea() {
        return 0;
    }
}

```

In that example, both returned 0.

Now suppose a different developer implemented a subclass, such as `Circle`, and they were not clear that the initial developer of the `Shape` class intended those two methods to be overridden to provide an implementation. Then they would leave those unimplemented, and we would suddenly find circles with circumferences and areas equal to zero! Whoops!

To prevent this sort of communication breakdown, the developer of the `Shape` class can mark the class as an **abstract class**. Abstract classes allow us to define _abstract_ methods, that _force_ the developer to define an implementation. If they don't, the Java compiler will throw an error.

An abstract method can _only_ be defined in an abstract class. To define an abstract class, put _abstract_ before the word class:

```java
public abstract class Shape {
    // Contents
}
``` 

With that done, we can now define some abstract methods. These get a method signature and return type, but don't get an implementation. The return type must be preceded by the word _abstract_, as in the following example:

```java

public abstract class Shape {
        public abstract double getCircumference();

        public abstract double getArea();
}

```

By seeing this, the developer and the Java compiler both understand that there must be an implementation of a method called `getCircumference()` in a subclass of `Shape`, and that it must return a double. 

**What if we forget to implement a method marked as abstract?**

Let's say we wanted to create a `Circle` subclass, but we forgot to implement the `getArea()` method

![](resources/failed-to-implement-abstract.png)

You can see that IntelliJ warns us with a big red underline and a fat dialog, that our class is doomed to fail the compiler!

Once we implement the missing method, and everything is back to normal:

![](resources/implement-abstract.png)

A common use of abstract classes, is to implement a _framework_, or _Template Method_ design pattern. This pattern allows the framework to define the larger behavior, leaving the particulars to the framework developer.

### Exercise - Monitor (Code along) 

Suppose we want to create a framework that does some (undefined) processing, and writes the results to a file. This is a common operation in monitoring applications, where input could be coming from a database, files, event logs, devices, pretty much anything.

> Note: We design the abstract class as purposely very generic so that subclasses can handle potentially any of these cases.

We wish to process that information by writing individual "Monitor" subclasses, that calls the process method and writes the result to the file system.

Let's write a base abstract class called `AbstractMonitor`, with one abstract method called `process()` that returns a String, and one non-abstract method called `startMonitoring(String fileName)`. 

Subclasses benefit from the `AbstractMonitor` class in that they don't have to rewrite the `startMonitoring(String filename)` method, they just have to worry about their own particular functionality for the `process` method.

For example, let's write a subclass called `MemoryMonitor` that writes the current time and CPU utilization. 

```java
public abstract class AbstractMonitor {
    public abstract String process();
    public void startMonitoring(String fileName) throws IOException, InterruptedException {
        while(true) {
            String value = process();

            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
            file.seek(file.length());
            file.write(value.getBytes());
            file.writeChar('\n');
            Thread.sleep(1000);
        }
    }
}

public class MemoryMonitor extends AbstractMonitor {

    @Override
    public String process() {
        return new Date() + ":" + Runtime.getRuntime().freeMemory();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new MemoryMonitor().startMonitoring("test.txt");
    }
}

```

<!-- NOTE TO INSTRUCTOR: Execute this method, then open the test.txt file in IntelliJ and watch it refresh every second. If it does not refresh on its own, keep hitting Ctrl-Alt-Y every second or so and it will tail the file. -->

## Summary

Both intefaces and abstract classes are used for abstraction. They're used in similar ways, but as you will see in the next lesson, _interfaces_ are most useful when you don't have any shared code between the subclasses, unlike our Monitors example in which all Monitors were able to share the `startMonitoring(String filename)` method.
