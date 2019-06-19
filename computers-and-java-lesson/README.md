| Title       | Type   | Duration | Author        |
| ----------- | ------ | -------- |   ----------  |
| Computers and Java | Lesson | 0:30   |  Victor Grazi |

### Learning Objectives 
- Explain what a computer is and what some of the common accessories are.
- Differentiate between compiled, interpreted, and JVM languages.
- Tell all your friends what makes Java great.

### What is a computer?

**Group Activity**

Before we get into Java, let's start with the very very basics. How would you define a computer? 

Take a moment to jot down a one-sentence definition of a computer. Then, get into a group of 3-4 people. Have everyone share their own definitions, and then create one shared definition of a computer based on everyone's answers. Be prepared to share it out with the class.

**In (kinda) technical terms...**

A computer is just a device that processes instructions - an instruction processor, affectionately known as a CPU or "Central Processing Unit".

When you open an application on your computer, for example a game on your phone, the device is actually processing a series of instructions called a "computer program" that instructs the device what to display and how to respond to your input.

Now all of this cannot happen in isolation. We need a way to enter a program, and that's why we have keyboards and mice. We need to display information and that's why we have monitors and printers. We need a place to store these instructions, and any files for that matter, and that is why we have disks.

We also need a location to execute the instructions and store data, and that is why our computers have memory.

We need a way to read and write to disk, and to talk to other computers in our organization or across the world wide web, and that is why we have networks.

And ultimately, every computer must have an operating system, which is just a fancy name for a computer program that manages all of these accessories, allocating time for processing, managing disk accesses, coordinating multiple threads to process concurrently, and all of the other millions of operations that occur every single second your computer is functioning.

<img src="https://cdn-images-1.medium.com/max/1600/1*rk1o0WQWtR1tEGcsEMIpEQ.png"/>

### Okay, so what is a computer programming language?

Now, what *we* call instructions and what a *computer* calls instructions are two very different things. We would like to think of instructions as a natural language set of commands that we can feed to the computer, just like we might tell our friend. 

But unfortunately, as we have heard, computer data is nothing more than a sequence of zeros and ones, and by extension that is what computer instructions are as well; sequences of zeros and ones. These instructions comprise the "Machine Code" that is understood by your particular operating system. Depending on the operating system and hardware you are using, be it Windows, Macintosh, Android, etc, a compiler must convert the computer program to the machine code understood by that particular operating system. The good news is, that is why we have computer programming languages!

![](/computers-and-java-lesson/0s-and-1s.jpeg)
*Yeah, it's basically just the Matrix in there.*

### What is a compiler?

Computer languages consist of natural language commands that mimic closely the way we speak and think. Once a computer program is written, it is handed off to a **compiler**, which is a program that converts computer programs into computer machine code instructions, the sequences of ones and zeros that instruct the computer what to do. 

Languages that are compiled include C, C++, and Go/GoLang.

Compilers produce the fastest code you can get without actually writing the machine code. However, depending on the language and the size of the program, they take some time to compile. Additionally, they need to be compiled for each target platform, so if you want to run the same program in Windows and Linux, you generally have to compile it for each platform.   

### What is an interpreter?

On the other side of the spectrum are the interpreted languages, such as JavaScript, Ruby, and Python. These do not generally compile down to machine code, but rather, they execute within an interpreter "shell", and program that "interprets" the natural language programs and executes the corresponding instructions.

Interpreted languages are generally going to execute slower than compiled languages, but there is no compilation step, so you can enter code and see it evaluated in real-time, as you type it. Besides the slower execution speed, interpreted languages tend to be "dynamically typed", which makes it difficult for tooling such as your IDE to catch errors in the code. 

### JVM Languages

There is a happy medium between compiled and interpreted languages, and that is the class of bytecode compiled languages, such as C#, Scala, and your soon-to-be favorite language, Java. 

These languages are compiled into an intermediate form called "byecode", which is very similar to the machine code of most platforms. The bytecode executes in an interpreter called a JVM (Java Virtual Machine) in the world of Java. Such languages are "write once, run anywhere" because they do not generally need to be compiled for each platform.

In this case of Java, there is also a JIT (just in time) compiler, than inspects the code as it executes, and based on those inspections, makes assumptions that allow it to compile segments of the code directly to machine code, making it blazing fast.

### What's the big deal about Java?

Java was originally designed as an embedded language, intended to control devices like refrigerators and clocks. Since it was platform independent, it took off more as a service-side development environment, for building client/ server tools. Years later, Java found its way back to its original roots and became an embedded language, popular in IOT (Internet of Things) devices. 

As Java approaches its 25th birthday, we should understand why it is the most popular language for server side code development.

1. Java is **platform independent**. Where .Net requires you to use Windows (and pay for Microsoft Windows licenses), Java can run anywhere. The term "write once, run anywhere" was made up for Java.

2. **Maturity and ecosystem**. If you need tools, they're present, free, and mature with huge developer support in the open source industry. If you're looking to write something using Java, chances are you'll find libraries to help you with what you're doing.

3. **Memory management**. The Java Virtual Machine has a built-in garbage collector. This is a facility that runs (mostly in the background) to locate objects that no longer have anything referring to them, and removes them from the heap. Java silently performs this housekeeping, allowing you as a developer to worry about coding your business logic and not get bogged down in the vagaries of memory management. 

4. **Backwards compatibility**. Java goes to great lengths to make sure everything is backwards compatible (every time a new version comes out, you can be confident that the old version of your code will still work). This is important when you're building a long term enterprise application.

### A word on background

The language was originally branded "Oak", but someone realized that name was already taken, and so while contemplating alternate options in a cafe over a cup of hot Java coffee, someone came up with the inspiration for the name we have all come to know and love!

> And remember: Just like "fire" is not the same thing as "firefly", Java is not JavaScript! The two languages have basically nothing in common other than the first four letters in their names.

## Check for Understanding

- Explain how the different parts of a computer fit together.
- What's the difference between compiled, interpreted, and JVM languages?
- Why do we love Java? 

