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

2. Does it make sense to write *getter* and *setter* methods for a `public` member variable? What about `private` variables?

3. What are some benefits of making member variables `private`?

4. If class A extends class B, which is the super class and which is the sub class? Which would you call parent, and which would you call child?

5. What does it mean for a class to *inherit* methods and/or variables from its parent class?

6. Consider the following code, where class Refrigerator extends class Appliance, and `getTemperature()` is a method in Refrigertor, but NOT in Appliance:

  ```
  Appliance myAppliance = new Refrigerator();
  double temperature = myAppliance.getTemperature();
  ```

Why will this call to `getTemperature()` cause an error? How will *casting* help solve this issue?

7. In a normal class (also called a *concrete* class), do you need to *implement* all of the methods, or can your simply *declare* some? What about in an `abstract` class?

8. What about an `interface`? Can you implement any methods in an interface? Can you declare methods in an interface?

9. Can you create an instance of an `abstract` class? Also, look up the Java keyword `final` and see if you can explain why a class CANNOT be both `abstract` and `final`.

10. What happens when a method *overrides* another method? If a parent and child class have methods with the same name, when you call that method on an instance of the child class, which implementation of the method will be executed?

11. What is the relationship between `List`, `LinkedList`, and `ArrayList`? Why do we call a method *polymorphic* if it takes an input of type `List` rather than an input of type `LinkedList` or `Arraylist`, and why is that useful?

### Deliverable

This file, with your answers added.

---

## Additional Resources

Refer to the Classes and Objects lesson, the Subclasses lesson, and the Abstract Classes and Interfaces lesson.

Feel free to Google these concepts as well. There are plenty of Java tutorial websites and StackOverflow posts that can help you. But be sure to write up your answers in your own words - copying and pasting some text does NOT help you actually learn (and is, in fact, cheating)!
