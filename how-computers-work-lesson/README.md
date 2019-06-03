| Title       | Type   | Duration | Author        |
| ----------- | ------ | -------- |   ----------  |
| Computers and the Way Java Works | Lesson | 1:00     |  Victor Grazi |

(Note to instructor: Let's begin with a thought-provoking discussion:)

### Objectives 
After this lesson students will understand what a computer is, what some of the common accessories are, what a computer program is, and why Java was created.
We will also understand about how Java evolves, and why we would choose Java from a crowded field of languages.

### What is a computer?

How would you define a computer? (Collect a few minutes worth of answers)
Then suggest: A computer is just a device that processes instructions - an instruction processor; 
affectionately known as a CPU or "Central Processing Unit".

When you open an application on your computer, for example a game on your phone, the device is actually processing a series of instructions called a "computer program" that instructs the device what to display and how to respond to your input.

Now all of this cannot happen in isolation. We need a way to enter a program, and that's why we have keyboards and mice. We need to display information and that's why we have monitors and printers. We need a place to store these instructions, and any files for that matter, and that is why we have disks.

We also need a location to execute the instructions and store data, and that is why our computers have memory.

We need a way to read and write to disk, and to talk to other computers in our organization or across the world wide web, and that is why we have networks.

And ultimately, every computer must have an operating system, which is just a fancy name for a computer program that manages all of these accessories, allocating time for processing, managing disk accesses, coordinating multiple threads to process concurrently, and all of the other millions of operations that occur every single second your computer is functioning.

<img src="https://cdn-images-1.medium.com/max/1600/1*rk1o0WQWtR1tEGcsEMIpEQ.png"/>

### What is a computer programming language?
Now, what we call instructions and what a computer calls instructions are two very different things. We would like to think of instructions as a natural language set of commands that we can feed to the computer, just like we might tell our friend. But unfortunately, as we have heard, computer data is nothing more than a sequence of zeros and ones, and by extension that is what computer instructions are as well; sequences of zeros and ones. These instructions comprise the "Machine Code" that is understood by your particular operating system. Depending on the operating system and hardware you are using, be it Windows, Macintosh, Android, etc, a compiler must convert the computer program to the machine code understood by that particular operating system. The good news is, that is why we have computer programming languages!

### What is a compiler?
Computer languages consist of natural language commands that mimic closely the way we speak and think. Once a computer program is written, it is handed off to a compiler, which is a program that converts computer programs into computer machine code instructions, the sequences of ones and zeros that instruct the computer what to do. 

Languages that are compiled include C, C++, and Go/GoLang.
Compilers produce the fastest code you can get without actually writing the machine code. However depending on the language and the size of the program they take some time to compile. Additionally, they need to be compiled for each target platform, so if you want to run the same program in Windows and Linux, you generally have to compile it for each platform.   

### What is an interpreter?
On the other side of the spectrum are the interpreted languages, such as JavaScript, Ruby, and Perl. These do not generally compile down to machine code, but rather, they execute within an interpreter "shell", and program that "interprets" the natural language programs and executes the corresponding instructions.

Interpreted languages are generally going to execute slower than compiled languages, but there is no compilation step, so you can enter code and see it evaluated in real-time, as you type it. Besides the slower execution speed, interpreted languages tend to be "dynamically typed", which makes it difficult for tooling such as your IDE to catch errors in the code. We will see more about this later.

 [//]: # "todo: Discuss statically and dynamically typed languages"

### JVM Languages
There is a happy medium between compiled and interpreted languages, and that is the class of bytecode compiled languages, such as Java, C#, and Scala. 

These languages are compiled into an intermediate form called "byecode", which is very similar to the machine code of most platforms. The bytecode executes in an interpreter called a JVM (Java Virtual Machine) in the world of Java and its derivatives, or CLR (Common Language Runtime) in the .Net world. Such languages are "write once, run anywhere" because they do not generally need to be compiled for each platform.

In this case of Java, there is also a JIT (Just in time) compiler, than inspects the code as it executes, and based on those inspections, makes assumptions that allow it to compile segments of the code directly to machine code, making it blazing fast.
 
### What is an application?
An application then, in any of these worlds, is a computer program that contains the instruct set to produce a desired result, such as a game, or a word processor, or a trading system.

### Example
Let's look at a simple example. It is a time-honored tradition to start learning a programming language by building a program called "Hello, World!". This is a very useful first step in configuring your environment, that ensures that your IDE is installed correctly, your compiler is connected, and all of your required dependencies are available. Once you can get your Hello, World program running, the fun begins!

In Java we would write a Hello, World program as follows:
In a folder called src\com\generalassembly\oop\intro we create a file called HelloWorld.java, and enter the following code.
(The .java extension indicates to the compiler that this file houses Java language code)
```java
package com.generalassembly.oop.intro;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compiling that produces a file called HelloWorld.class. The .class extension indicates to the JVM (the Java runtime interpreter) that this file contains bytecode instructions, ready to execute.

### Why Java 
We can take a peak under the covers at the class file, by opening it up in an editor. Unfortunately that won't be very readable. We can make it slightly more comprehensible by using the javap command, which translates the machines zeros and ones into human readable instructions.

```bash
$ javap -c C:\dev\foundational-java\target\production\foundational-java\com\generalassembly\oop\intro\HelloWorld.class
Compiled from "HelloWorld.java"
public class com.generalassembly.oop.intro.HelloWorld {
  public com.generalassembly.oop.intro.HelloWorld();
    Code:
       0: aload_0
       1: invokespecial #1    // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2    // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3    // String Hello, World
       5: invokevirtual #4    // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
```

As you can see this does not look anything like our original .java file, although you can see some vague connection by referring to the comments (after the // on each line). Fortunately, this is not something you will need to look at very often, but at least you can see how it works.

### Why Java?
As Java approaches its 25th birthday, we should understand why it is the most popular language for server side code development.

1. Java is platform independent. Where .Net requires you to use Windows (and pay for Microsoft Windows licenses, Java can run anywhere, in particular on low cost Linux commodity hardware, which is extremely common in today's enterprises.

2. Maturity and ecosystem. If you need tools, they're present, free, and mature with huge developer support in the open source industry. If you're looking to write something using Java, chances are you'll find libraries to help you with what you're doing.

3. Memory management. The Java Virtual Machine has a built-in garbage collector. This is a facility that runs (mostly in the background) to locate objects that no longer have anything referring to them, and removes them from the heap. Languages like C and C++ require you to write explicit code to free up unused memory. Java silently performs this housekeeping, allowing you as a developer to worry about coding your business logic and not get bogged down in the vagaries of memory management. 

3. Backwards compatibility. Java goes to great lengths to make sure everything is backwards compatible (every time a new version comes out, you can be confident that the old version of your code will still work). This is important when you're building a long term enterprise application.

### A word on background
Java was originally designed as an embedded language, intended to control devices like refrigerators and clocks. Since it was platform independent, it took off more as a service-side development environment, for building client/ server tools). Years later, Java found its way back to its original roots and became an embedded language, popular in IOT (Internet of Things) devices. 

The language was originally branded "Oak", but someone realized that name was already taken, and so while contemplating alternate options in a cafe over a cup of hot Java coffee, someone came up with the inspiration for the name we have all come to know and love!

Today there is a new Java version release every 6 months, with a long-term support (LTS) release every 3 versions. Java 11, released in September 2018, is the latest LTS release at the time of this writing.

However Java 8 is still the most popular version in the enterprise, and so we will be targeting Java 8 in this course.  
