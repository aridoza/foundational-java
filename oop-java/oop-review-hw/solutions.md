---
title: OOP Review: Answers
type: Homework Answers
duration: "1:00"
creator:
    name: Charlie Drews
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) OOP Review: Answers

## Questions

1. What's the difference between **member variables** (also called **instance variables**) and **class variables** (with the `static` keyword)? Which can be accessed without creating an instance of the class?

   - Each instance of a class has its own copy of the member variables. There's only **one** copy of the class variables, and they can be accessed without creating an instance of the class (e.g., `Integer.MAX_VALUE`).

2. Does it make sense to write **getter** and **setter** methods for a `public` member variable? What about `private` variables?

   - `public` variables can be accessed and modified directly, so there's no need to have getter and setter methods. `private` variables **cannot** be accessed or modified directly; they're only visible from within the class.

3. What are some benefits of making member variables `private`?

   - Another developer won't be able to assign your member variable an invalid value by putting logic in your setter method to check the parameter before assigning its value to the member variable.
   - Also, if your program crashes due to a `private` variable having a null or unexpected value, you know the bug must be in that class file. With `public` variables, on the other hand, the bug could reside anywhere in your program, because a `public` variable is visible and modifiable to code outside of its class.

4. If Class A extends Class B, which is the superclass and which is the subclass? Which would you call the parent, and which would you call the child?

   - Class B is the superclass and the parent class. Class A is the subclass and the child class. The child class or subclass extends and inherits from the parent class or superclass.
 
5. What does it mean for a class to **inherit** methods or variables (or both) from its parent class?

   - If Class A extends Class B, then Class A inherits all of Class B's variables and methods and may have additional variables and methods of its own. You can call any of the Class B methods on an instance of Class A.

6. Consider the following code, where the `Refrigerator` class extends the `Appliance` class, and `getTemperature()` is a method in `Refrigerator`, but **not** in `Appliance`:

    ```
    Appliance myAppliance = new Refrigerator();
    double temperature = myAppliance.getTemperature();
    ```

   Why will this call to `getTemperature()` cause an error? How will **casting** help solve this issue?

   - Even though we assigned a `Refrigerator` object to the `myAppliance` variable, the compiler still considers `myAppliance` to be of type `Appliance` because that's how it was declared. Therefore, when we try to call `getTemperature()` on `myAppliance`, the compiler says, "Variables of type `Appliance` don't have a `getTemperature()` method. I'm going to call this an error."
   - We can cast `myAppliance` from `Appliance` to `Refrigerator` using `(Refrigerator)myAppliance`. Then, the compiler will allow you to call `getTemperature()` on `myAppliance`. Also, this cast is allowed because we know the object assigned to `myAppliance` matches the definition of the `Refrigerator` class.

7. In a normal class (also called a **concrete** class), do you need to **implement** all the methods, or can you simply **declare** some? What about in an `abstract` class?

   - In a concrete class, you must implement **all** methods (i.e., write out both the method signature and the full body of the method).
   - In an `abstract` class, you don't have to implement all methods; you have the option to just declare methods (i.e., write out the method signature but **not** the method body). If another class **extends** your `abstract` class, that subclass must implement all of the methods that were not implemented in the `abstract` parent class.

8. What about an `interface`? Can you implement any methods in an `interface`? Can you declare methods in an `interface`?

   - You cannot implement any methods in an `interface`, you can only declare them. If a class **implements** an `interface`, that class must implement all of the methods declared in the `interface`. If multiple classes implement the same `interface`, they can have different implementations of those methods.

9. Can you create an instance of an `abstract` class? Also, look up the Java keyword `final` and see if you can explain why a class **cannot** be both `abstract` and `final`.

   - No, you cannot create an instance of an `abstract` class. This is because some of the `abstract` class' methods might not be implemented. You must first create a class that **extends** the `abstract` class, then create an instance of that subclass.
   - If a class is given the `final` modifier, then you're not allowed to make any subclasses from it. Because an `abstract` class can't be instantiated directly and must first be extended, if you made it `final` and couldn't extend it, then the `abstract` class wouldn't be of any use.

10. What happens when a method **overrides** another method? If parent and child classes have methods with the same name, when you call that method on an instance of the child class, which implementation of the method will be executed?

    - Say Class B has a method `goFaster()` and Class A extends Class B. If Class A defines a new method that's also named `goFaster()`, then this new method **hides** the method of the same name from Class B. If you call `goFaster()` on an instance of Class A, the overridden version of `goFaster()` defined in Class A will be executed, rather than the version defined in Class B.

11. What's the relationship between `List`, `LinkedList`, and `ArrayList`? Why do we call a method **polymorphic** if it takes an input of type `List` rather than an input of type `LinkedList` or `Arraylist`? Why is that useful?

    - `List` is an interface. `LinkedList` and `ArrayList` are both concrete classes that implement the `List` interface.
    - If a method requires an input of type `LinkedList`, then you can't supply it with an input value of type `ArrayList`. You'd have to write another version of the method that takes an input of type `ArrayList`. That means repeating yourself, which is no good.
    - But if the method requires an input of type `List` instead, then you're free to pass that method an input value of type `LinkedList` or `ArrayList` (or any other class that implements `List`). This means you don't have to write multiple versions of the method. This is the benefit of polymorphism.
