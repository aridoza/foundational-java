| Title | Type | Duration | Creator |
| --- | -- | -- | --- |
| Objects and Classes | Lesson | 1:25 | Victor Grazi, NYC |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Objects and Classes

### LEARNING OBJECTIVES

*After this lesson, you will be able to:*
- Instantiate and use an object from a class.
- Add fields and methods to a class.
- Use constructors.

### LESSON GUIDE

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 5 min  | Opening  | Discuss lesson objectives | 
| 15 min | Guided Practice | Class creation |
| 20 min | Guided Practice | Class features | 
| 15 min | Independent Practice | Calculator class |
| 20 min | Guided / Independent Practice | Constructors |
| 10 min  | Conclusion  | Review / Recap |


* Demonstrate Ability to:
    * Write a Class
    * Write a Constructor (including ones that accept arguments)
    * Create an Instance with the "new" Keyword
    * Access a Field or Method from an Instance 

<!-- 
NOTE TO INSTRUCTOR: Before you can execute Java code you must install a JDK (latest build of JDK 1.8).
Then you must assign that JDK to your IntelliJ Project The first time you try to execute the code, IntelliJ will prompt you for a JDK. Just point to the root directory of the JDK installation, on my machine that is: 
![](resources/jdk-setup.png)
 -->

## Opening (5 mins)

<!-- 
This is a good reference. Please don't delete
Reference [this lesson](https://git.generalassemb.ly/ed-product-library/programming-fundamentals-in-java/tree/master/baseline-materials/classes-lesson) or [this lab](https://git.generalassemb.ly/ed-product-library/programming-fundamentals-in-java/tree/master/baseline-materials/classes-pair-programming-lab) for ideas.
I added an assignment below, the Calculator class
-->

In OOP a class is a kind of template for creating _objects_. An object is an _instance_ of a class, meaning it contains all of the data fields and functionality defined in its class. However, each object can contain its own set of values for those fields. For example, a class might define a _name_ field of type _String_, but individual _instances_ of that class might have unique values assigned to that name field such as "Jeremy", "Maria", "Carlotta", etc. 

> Tip: Instantiation is a large word, but it comes from the word "instance", which is a little easier to understand. Instantiation is the practice of creating instances (aka objects) from classes!

**Think of it as follows:**

We know 'HumanKind' is a classification of creature that thinks, has senses, a body, etc. But we don't think of a specific person as a 'HumanKind'; HumanKind is the class; Vanna White is an _instance_ of HumanKind. Vanna has a name, a birthdate, brown eyes, two ears, legs, etc. 

## Guided Practice: Step-By-Step Class Creation (15 mins)

Let's make this more concrete. Let's create a class called HumanKind. For now, we will just create a shell of the class, and we can add to it as we go. 

### Step 1

First, create the file to contain the public _HumanKind_ class. 

> Check: Who can tell me the name of that file? It should be `HumanKind.java`.

We must create it in a directory that mimics the package name, i.e. src/com/generaassembly/oop/intro/HumanKind.java:

```java
package com.generalassembly.oop.intro;

public class HumanKind {
    
}
```

### Step 2

Next, let's add a _main_ method so we can execute our code (remember a main method defines the entry point of a program):

> Tip: If you hate typing, IntelliJ has a convenient shortcut for defining a main method... just type _psvm_ and hit the tab key; that stands for "public static void main". That is an example of an IntelliJ _live template_. Getting familiar with these will make you a speed programmer!

```java
package com.generalassembly.oop.intro;

public class HumanKind {
    public static void main(String... args) {
        
    }
}
```

Now we have defined our HumanKind class! But classes are not much good until they are _instantiated_.

### Step 3

To create an instance of the HumanKind class, you need to call the HumanKind _constructor_. This is done using the _new_ keyword. Let's add that instance creation to our _main_ method, then print out our instance, as follows:

```java
package com.generalassembly.oop.intro;

public class HumanKind {
    public static void main(String... args) {
        HumanKind vannaWhite = new HumanKind();    
        System.out.println(vannaWhite);
    }
}
```

Voila! We have a new instance of a HumanKind!

**Let's analyze that:**

- `HumanKind vannaWhite` means declare a variable of type 'HumanKind' and name that variable vannaWhite. The `=` sign means assign everything on the right of the equal sign, to the variable on the left.

> Tip: Remember the Java naming convention - camel case variable names, classes start with a capital, variables start with a lower case letter.

- `new HumanKind()` calls the 'constructor', which is the method of the HumanKind with the same name as the class. Notice that even though we didn't write a constructor method called `HumanKind`, Java provides one by default. A constructor is called to _construct_ (create) a new instance of the HumanKind class (i.e. using the HumanKind class as a template). This process is known as instantiation; we are 'instantiating' a new object, i.e. creating a new instance, of the HumanKind class.

So in plain English, this statement is saying, "declare a new variable named "vannaWhite" of type "HumanKind", and assign it a new instance of the class HumanKind". 

```java
HumanKind vannaWhite = new HumanKind();
```

<!-- todo: move this to the lesson on visibility, and be careful to move the instantiation outside the main method and into the class, or else it is a compile error to declare a visibility.
In this example we omitted the visibility, implying default visibility, which as we said means that it is only visible to classes in the same package. More commonly we will make instance variables private, by prefacing the declaration with the word _private_, as follows:
```java
private HumanKind vannaWhite = new HumanKind(); // this is only visible in this class file
```
(We could also assign public visibility to make this instance available to all objects outside this class, or protected to make it available to subclasses. Move on subclasses later.)
(Don't be confused with my earlier statement, when I said that top level classes can only be public or default. That is true about the class declarations themselves, i.e. when we say ```public class SomeClass``` or ```class SomeClass```. But variables referring to those classes can be any visibility.
Just to be clear, it is a compile error to try to declare the following as a top level class:
```java
private class InvalidClass{}
```
but it is perfectly valid to say
```java
private MyValidClass someVariable;
```
--> 

### Step 4 

Finally, to run our program in IntelliJ, notice the little green arrow in the margin, to the left of our _main_ method. 
![](resources/execute-main.png)

Give that arrow a left-click, then click on "Run HumanKind.main"

That will compile and execute our code, and produce output something like:

![](resources/execute-main-output.png)

## Guided Practice: Class Features (20 mins)

Now that we've looked at an example of a class, let's break down the different pieces and take a look at what exactly they're doing.

So far we have seen how to define the shell of the class. But the value starts to be realized when we add _members_ to the class. Members of a class include...
- Fields
- Methods
- Inner classes

We will look at fields and methods now.

<!-- Instructor note: If you're feeling like this topic is a bit of review, try searching Google for information about Inner classes which are a little more advanced!-->

### Fields

Fields are the variables associated with every instance of this class. What are some fields our `HumanKind` class might have? Take a few minutes to create three fields for the `HumanKind` class.

<details>
  <summary>Possible solution</summary>

```java
public class HumanKind {
}
```

</details>


#### Instance vs. class variables

Most fields are called _instance_ variables, because they are contained within an instance of the object. Two different object instances of the same class type might have completely different values for their instance variables. 

You will frequently hear the fields of an object referred to as its _state_. State essentially describes this object, and defines what distinguishes this object from any other object of the same class.

For example, if our HumanKind class has an _age_ field, then `vannaWhite.age` might be 62, where `patSajak.age` might be 72.

> (Now, how neat is that syntax: `vannaWhite.age` means "the age field of the object referred to by the vannaWhite variable". Nice, huh?)

Not all fields are instance variables. If a field is declared _static_, then they are called _class_ variables. A class variable shares its value among all instances of that class. Additionally, you do not need to have access to an instance in order to access a class variable. If the value of a class varibale changes for one instance, it changes for all instances. We will see examples of this shortly, and we will see why this is useful.

<!---Another fancy name for an instance variables that you will encounter is a "property". A property is basically an instance field with a getter and setter. A read only property only has a getter but no setter.
**Important note on properties**
There is one rule you want to remember starting from today - you will almost always want to make fields in a class private, and then provide _accessor_ ("getter") and _mutator_ ("setter") methods for those fields. This is called encapsulation - it means that you never expose the structure of a class to anything outside the class; anyone accessing the class has to go through your exposed API (Application Program Interface). These are the methods that you expose to external classes by assigning a visibility like _public_. -->

### Methods

Methods provide the functionality to the objects instantiated from this class. We'd call a method when we want our object to do something or change something. 

What methods might we add to the `HumanKind` class? 

<details>
  <summary>Possible solution</summary>

For example, we might have a `setAddress()` method that alters the `address` field's value.

```java
public class HumanKind {
}
```

</details>

<!--Here is an example of a method declaration on a `MathHelpers` class:
```java
public class MathHelpers() {
    // Other stuff... 
    public double sum(double first, double second) {
    }
}
```-->

Let's study that structure!

1) As with any class member, we start the method declaration with the *visibility*, in this case public.
2) Next, we declare the *return type*. In this case, we are... 

<!--Some methods return nothing at all, say for example, a print method, or a method that writes to a database and does not need to return anything. There are many cases of such methods. To express a method that does not return anything, use _void_ as the return type.
```java
public void writeToDatabase(DBConnection conn, String data) {
}
```
This defines a method called _writeToDatabase_ that takes two input parameters, and returns nothing.-->

3) Finally, we enclose the arguments in parentheses - those represent the values that one must supply when they call the method.

We refer to the method name and arguments collectively as the _method signature_.

To call a method, specify the method name, followed by parentheses, and inside the parentheses enclose the values represented by the arguments in the method declaration. If there is a return value, you can (and generally should), assign that return value to a variable, or use it in an expression. Otherwise it is lost.

How would we call our `setAddress` method above?

<details>
  <summary>Possible solution</summary>
  
```java

```

</details>


In IntelliJ, if you don't remember the names of the parameters for a particular method, type the method where you want to use it, put your cursor between the parentheses and hit Ctrl-p. This will show a neat display of the parameters and types.

![](resources/ctrl-p.png)

#### Instance vs. static methods

**Instance methods**

Like fields, methods can be static or not. Non-static methods are known as _instance methods_. Static methods are also known as _class methods_. 

The implementation of an instance method has access to every field and method declared in the class, no matter what the visibility. It can also create its own variables, but these are lost once the method returns.

<!--Let's try implementing our sum method:
```java
public double sum(double first, double second) {
    double result = first + second;
    return result;
}
```
Methods that return void can have (but do not require) a return statement, whereas non-void methods _must_ have at least one return statement.
![](resources/debugger.png)-->

**Static methods**

Most of the methods and fields we have defined so far have been _instance_ methods; methods that require an instance to invoke.

However Java also provides _static_ (aka _class_) methods, that don't require an instance, they just execute on a class. These methods get the word _static_ before the return type.

Some common examples of static methods are the methods in the Math class. 

```java
System.out.println(a + "**" + b + "=" + (Math.pow(4, 2))); 
// displays 4**2=16.0 (Java has no exponent operator, so we invent ** just for display purposes)
``` 

If a method does not access any of the instance fields or methods from the class that it is defined in, consider making it static.

## Independent Practice - Calculator (10 mins)

We have covered a lot of ground, so let's do an exercise!

Let's create a class called Calculator, and add the sum method. Then add a main method to perform the following sums and print out the result:

```java
10 + 20
3.14 + 2.718
1.414 + 3.14
```

<details>
  <summary>Solution</summary>
    
```java
package com.generalassembly.oop.intro;
public class Calculator {
    public static double sum(double first, double second) {
        double sum = first + second;
        return sum;
    }
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(sum(10 , 20));
        System.out.println(sum(3.14 , 2.718));
        System.out.println(sum(1.414,3.14));
    }
}
```

</details>  

<!--### Accessing Instance Variables 
A common idiom you are sure to encounter is when a method parameter has the same name as an instance variable.
For example, let's say you have the following class:
```java
class Person {
    private float age;
    public void setAge(float age) {        
    }
}
```
Now there are two different variables with the same name _age_; the instance variable `private float age` and the method argument float age.
Let's say you want to assign the value of the method variable to the class variable.
If you say:
```java
    private float age;
    public void setAge(float age) {
        age = age;   
    }
```
that is called a "no-op" - nothing changes at all - it just assigns the value of the method's _age_ variable to itself.
What you really want is:
```java
    private float age;
    public void setAge(float age) {
        this.age = age;   
    }
```
When you prepend a variable with the word _this._ you are making it eminently clear that you are referring to the instance variable and not the method variable. Some developers will always refer to instance variables using _this._ (even when it is optional), just because it makes it clear that you are referring to a class variable.
> Tip: Using best practices will make you look like a pro. Make a habit of always using "this" for accessing instance variables!-->

### Constructors (20 mins)

Very often, it will be convenient to pass some data into a new class after construction.

Let's imagine that we want to add some fields, and some getters and setters:

```java
public class HumanKind {
    private int ID;
    private String name;
    private String address;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
```

Now, since all members of our class will have an ID, name, and address, we might do the following for each member of HumanKind:

```java
HumanKind vannaWhite = new HumanKind();
vannaWhite.setID(123);
vannaWhite.setName("White, Vanna");
vannaWhite.setAddress("123 Main St, Burbank, CA");

HumanKind patSajak = new HumanKind();
patSajak.setID(456);
patSajak.setName("Sajak, Pat");
patSajak.setAddress("456 Elm St, New York, NY");
```

That's a lot of lines to repeat for each operation. If that feels unnecessarily repetitive, you're right, there's a better way to do this. 

### Constructors with Arguments

Remember the constructor method we discussed earlier? The one with the same name as the class itself that gets called whenever a new instance is created? We can pass arguments to that to seriously speed up our process.

Here are some basic rules for writing our own constructors (as opposed to just using the empty ones Java provides by default):
1. A constructor must exactly match the class name it is contained in.
2. A constructor has no return type.
3. A constructor may not be static.

#### Independent Practice: HumanKind class

Add a constructor to our `HumanKind` class that accepts three arguments for initial values of ID (int), name (String), and address (String).

<details>
  <summary>Solution</summary>
  
```java
package com.generalassembly.oo.intro;

public class HumanKind {
    private int ID;
    private String name;
    private String address;

    public HumanKind(int ID, String name, String address) {
        this.ID = ID;
        this.name = name;
        this.address = address;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
``` 

</details>

So given the constructor, we can now save a few lines of code (and make it clearer along the way) by entering:

```java
HumanKind vannaWhite = new HumanKind(123, "White, Vanna", "123 Main St, Burbank, CA");
HumanKind patSajak = new HumanKind(456, "Sajak, Pat", "123 Main St, Burbank, CA");
```

### Default Constructor

If we do not define a constructor for a class, Java supplies one implicitly. We saw that in our initial implementation of the `HumanKind` class. 

Now that we've defined our own constructor, Java _will no longer_ supply a default constructor, so we're good to go from here.

> Tip: If you're not doing anything in the constructor, it's totally fine–and a good practice–to just use the default that Java provides!

<!--### Multiple Constructors
A class may have multiple constructors, as long as each has a signature that is distinct from the other constructors in the class. This is an example of *method overloading* which we mentioned in the previous lesson as one of the many cool features of object-oriented programming.-->

## Conclusion (5 mins)

There's a lot to know about creating classes and objects, isn't there?! 

With a partner, discuss the following prompts:

* Explain the difference between:
    * Classes and Objects (Instances)
    * Class Variables and Instance Variables
    * Static and Non-Static Methods
    
* Define:
    * Constructors
    * Instances
    * Properties
    
