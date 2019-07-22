---
title: OOP Review
type: Homework
duration: "1:00"
creator:
    name: Charlie Drews
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) OOP Review

## Requirements

Respond to these questions based on what we've learned (and maybe what we haven't covered yet!).

---

## Questions

1. What is the difference between *member variables* (also called *instance variables*) and *class variables* (w/ keyword `static`)? Which can be accessed without creating an instance of the class?

  - Each instance of a class has its own copy of the member variables. There is only **one** copy of the class variables, and they can be accessed without creating an instance of the class, e.g. `Integer.MAX_VALUE`

2. Does it make sense to write  *getter* and *setter* methods for a `public` member variable? What about `private` variables?

  - Public variables can be accessed and modified directly, so there is no need to have getter and setter methods. Private variables CANNOT be accessed or modified directly; they are only visible from within the class. 

3. What are some benefits of making member variables `private`?

  - You can make sure another developer doesn't give your member variable an invalid value by putting logic in your setter method that checks the parameter before assigning its value to the member variable.
  - Also, if your program crashes due to a private variable having a null or unexpected value, you know the bug must be in that class file. With public variables, on the other hand, the bug could reside anywhere in your program, since a public variable is visible and modifiable to code outside its clas.

4. If class A extends class B, which is the super class and which is the sub class? Which would you call parent, and which would you call child?

  - B is the super class and the parent class. A is the sub class and the child class. The child/sub extends and inherits from the parent/super.
 
5. What does it mean for a class to *inherit* methods and/or variables from its parent class?

  - If A extends B, then A inherits all of B's variables and methods, and may have additional variables and methods of its own. You can call any of the class B methods on an instance of class A.

6. Consider the following code, where class Refrigerator extends class Appliance, and `getTemperature()` is a method in Refrigertor, but NOT in Appliance:

  ```
  Appliance myAppliance = new Refrigerator();
  double temperature = myAppliance.getTemperature();
  ```

Why will this call to `getTemperature()` cause an error? How will *casting* help solve this issue?

  - Even though we assigned a Refrigerator object to the variable `myAppliance`, the compiler still considers `myAppliance` to be of type Appliance, because that's how it was declared. Therefore, when we try to call `getTemperature()` on `myAppliance` the compiler says "variables of type Appliance don't have a getTemperature() method; I'm going to call this an error."
  - We can cast `myAppliance` from Appliance to Refrigerator using `(Refrigerator)myAppliance`. Then the compiler will be happy to allow you to call `getTemperature()` on `myAppliance`. Also, this cast is allowed because we know the object assigned to `myAppliance` matches the definition of the Refrigerator class.

7. In a normal class (also called a *concrete* class), do you need to *implement* all of the methods, or can your simply *declare* some? What about in an `abstract` class?

  - In a concrete class, you must implement ALL methods (i.e. write out both the method signature and the full body of the method)
  - In an abstract class, you do not have to implement all the methods. You have the option to just declare methods (i.e. write out the method signature, but NOT the method body). If another class *extends* your abstract class, that subclass must implement all the methods that were not implemented in the abstract parent class.

8. What about an `interface`? Can you implement any methods in an interface? Can you declare methods in an interface?

  - You cannot implement any methods in an interface. You may only declare methods in an interface. If a class *implements* an interface, that class must implement all the methods declared in the interface. If multiple classes implement the same interface, they can have different implementations of the methods.

9. Can you create an instance of an `abstract` class? Also, look up the Java keyword `final` and see if you can explain why a class CANNOT be both `abstract` and `final`.

  - No, you cannot create an instance of an abstract class. This is because some of the abstract class's methods might not be implemented. You must first create a class that *extends* the abstract class, then create an instance of that subclass.
  - If a class is given the `final` modifier, then you are not allowed to make any subclasses of it. Since an abstract class cannot be instantiated directly, and must first be extended, if you made it `final` and couldn't extend it then the abstract class would not be of any use.

10. What happens when a method *overrides* another method? If a parent and child class have methods with the same name, when you call that method on an instance of the child class, which implementation of the method will be executed?

  - Say class B has a method `goFaster()` and class A extends class B. If class A defines a new method also named `goFaster()` then this new method *hides* the method of the same name from class B. If you call `goFaster()` on an instance of class A, the overridden version of `goFaster()` defined in class A will be executed, rather than the version defined in class B

11. What is the relationship between `List`, `LinkedList`, and `ArrayList`? Why do we call a method *polymorphic* if it takes an input of type `List` rather than an input of type `LinkedList` or `Arraylist`, and why is that useful?

  - List is an interface. LinkedList and ArrayList are both concrete classes that implement the List interface.
  - If a method requires an input of type LinkedList, then you cannot supply it with an input value of type ArrayList. You'd have to write another version of the method that takes an input of type ArrayList. That means repeating yourself, which is no good!
  - But if the method requires an input of type List instead, then you are free to pass that method an input value of type LinkedList or ArrayList (or any other class that implements List). This means you don't have to write multiple versions of the method. This is the benefit of polymorphism.


