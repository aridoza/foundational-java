# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) My First Java

| Title | Type | Time | Creator |
| ----- | ---- | -- | ----- |
| My First Java | Lesson | 0:55 | Ryan Fleharty |

### LEARNING OBJECTIVES

*After this lesson, you will be able to:*
- Create and run Java files.
- Write the `main` Java method.

---

## Opening (5 mins)

Before we dive into the nitty-gritty of Java functions and data types, let's write some code and make some stuff happen.

The first two things you'll need to do in any Java program is create a `class` and create the `main` method. So let's see how to do that!


## Code Along: My First Java: Howdy Pardner (20 mins)

We'll complete this activity using a text editor and the command line.

Let's start with the all-time classic function, Howdy Pardner. To begin, let's create a file called `HowdyPardner.java` and save it. All Java files must be defined as a class, so we can begin with a class definition. This class definition must match the name of the file! So we'll call ours `HowdyPardner`:

```java
  public class HowdyPardner{
  }
```

<!-- Instructor Note: Consider writing this + the main method signature on the board, so you can underline and point to things (modifiers, parameters) as you go through the following.-->

Then, all Java programs require a `main` method representing the entry point of the program. This method will automatically be invoked when we run our Java file. The following function must be placed INSIDE the class definition: 

```java
  public static void main(String[] args) {
  }
```

What's going on here? 

#### `public`

First, the keyword `public` declares this method as being available anywhere. On the other hand, a `private` method is only available to other members of the same class.  More reference [here](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html).

#### `static`

Next, the `static` keyword indicates that this method belongs to the class itself. The opposite of a `static` method would be an instance method, where the method belongs to the objects the class creates. To run an instance method, you would have to create a new instance of the class with the `new` keyword, and use it to run that method.

#### `void`

`void` indicates the return value of the function, which in our case will be nothing at all. Functions in Java require you to describe what type the output will be in their definition, to enable future type-checking.

#### `main`

Finally, we name our function - in this case, the `main` function is absolutely required in Java programs, and must be named `main`.

#### `String[] args`

Inside the parentheses, you will notice the function takes in one parameter: `String[] args`. String indicates the type, the array brackets tell the function to accept a list of Strings, and the parameter is named `args` by convention. This array represents any command line arguments you pass when running the function, and for our purposes it will be empty.

*phew*, all that for Howdy Pardner!? YES. And we haven't even gotten to the one line of code that will actually DO the thing we want- print "Howdy Pardner" to the console. How do we do that? At this point, I trust you to figure out that line for yourself through research (i.e. Google). Take a minute to do that now.

<details>

	<summary>And the answer is...</summary>

```java
	public class HowdyPardner {
		public static void main(String[] args) {
			System.out.println("Howdy, Pardner!");
  		}
 	}
```

</details>


A quick note on comments: in Java, we have two options for writing comments:

```java
//I'm a single line comment. Just lil ol' me!
/*
	I'm a multi-line comment.
	There are several of us.
	And we all get along fabulously!
*/
```

Jump back into `HowdyPardner.java` and comment your code to explain what the `class` definition means and what the `main` method does.


## Code Along: Getting Java to Run (10 mins)

So, now that we have our function ready to roll, how can we get it to run? With Java, we have two steps to complete:
- Step 1: Compile the code
- Step 2: Run the code

> Check: Can someone shout out a definition of compiling and why we do it? (Hint: we already covered this!)

#### Step 1: Compile

First, we have to <i>compile</i> the code into machine code for the Java Virtual Machine to run. 

To accomplish this, we must run `javac <file-name>`, which in this case should be HowdyPardner. Always expect a compilation error or two (or twelve), with a handy message pointing you to the exact line and nature of your problem. 

This contrasts with what we did in JavaScript, where a file could be running in our server, and until we hit a broken line of code, everything would be fine. Then, suddenly, we would get a breaking error. 

JavaScript let us get away with errors, while Java will enforce its rules before allowing us to run the code. After all, wouldn't it be better for a car to tell us "your brakes are out" and refuse to start, rather than letting us drive around without working brakes?

> Fun fact: What we just described is the difference between *statically typed* languages (Java) and *dynamically typed* languages (JavaScript). We won't go into too much detail here, but now you have two more computer science terms to add to your toolbelt. Hooray!

#### Step 2: Run

When the file is successfully compiled, you should notice a new file with a .class extension has been created- this is our compiled class. Now we can actually run the code with the command `java HowdyPardner`. (That's right - we can't run a file until it's compiled. A file with a `.java` extension won't run, but a compiled file - with the `.class` extension - will run.)

Guess what: when we start using the Eclipse IDE, we won't have to do any of this! The application will automatically compile our code when we save the file. And you can run the file by clicking one little button. Thanks, abstraction!

## Variable Declarations (5 mins)

To start writing our own functions, we will have to declare variables at some point. 

Creating a variable in Java requires us to define what the data type of that variable will be by providing that type before the name of the variable. 

Examples include:

```java
int theAnswerToEverything = 42;
String bestLanguage = "Java";
double priceOfSteak = 6.99;
```

We'll learn more about all of Java's different data types soon, but here are the ones we see here:
- `int`: short for integer; a whole number
- `String`: any combination of letters and words. The word String is a class, so always begins with a capital S
- `double`: a number with a decimal

Notice anything about how we write variables? That's right - it's camel case!

Let's contrast that with how we defined the `HelloWorld` class. In Java, it's conventional to for a class name to have all upper case letters (even the first word).

#### Independent Practice (10 mins)

With a partner, take 7 minutes to write these variables and print them to the console:
- How many licks it takes to get to the center of a Tootsie pop
- Your home city and state
- The price of an ice cream cone from Mr Softee

We'll have a few people demo what they did when they're done.

## Conclusion (5 mins)

- Can someone explain the difference between compiling and running a Java program?
- Without looking at your notes, can you explain the different parts of the `main` method?

