| Title | Type | Duration | Creator |
| --- | -- | -- | --- |
| Subclasses | Lesson | 1:25 | Victor Grazi, NYC |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Subclasses

### Learning Objectives

At the end of this lesson, students will be able to:
- Create a subclass.
- Use a subclass to augment the functionality of the base class.

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 5 min  | Opening  | Learning Objectives | 
| 15 min | Guided Practice | Superclasses and Subclasses |
| 15 min | Guided Practice | Upcasting and Downcasting |
| 40 min | Independent Practice | Shapes |
| 10 min  | Conclusion  | Review/Recap |

## Opening (5 min)

In our use of language and observations from the world, we naturally categorize objects of similar types. For example, we understand what a cat is and that a lion is a member of the cat family. And, although we have no confusion between a cat and a lion, we know that lions share qualities with cats.

![](resources/cat-lion.png)

<!-- Taken from https://odditymall.com/includes/content/thumb1366252920.png. -->

We can say that `Lion` is a subclass of `Cat` and will inherit basic properties and functionality from its **superclass** (its parent class). 

> **Note**: You may also hear subclasses and superclasses referred to as "parent" and "child" classes, which may help visualize what is meant by "inheritance."

-----

## Guided Practice: Superclasses and Subclasses (15 min)

Let's create a `Cat` class: 

```java
public class Cat {
    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasWhiskers() {
        return true;
    }

    protected String favoriteSnack() {
        return "Mice";
    }

    public int getLives() {
        return 9;
    }
}
```

We create a subclass of `Cat` by using the `extends` keyword:

````java
class Lion extends Cat {
    @Override
    public int getLives() {
        return 1;
    }
}
````

In this case, we say `Lion` `extends` `Cat`, or `Lion` is a **subclass** of `Cat`. And we say `Cat` is a **superclass** of `Lion`. 

The `Lion` class inherits members from its superclass, so the calls below are legal. Even though they're not in the `Lion` class, they're inherited from the `Cat` class:

```java
Lion lion = new Lion();
lion.hasWhiskers();     
lion.favoriteSnack();  
lion.getLives();       
```

> **Think**: What will each of the calls above return?

<details>
<summary>Answers</summary>

* `hasWhiskers()` returns `true` (inherited from the `Cat` class).
* `favoriteSnack()` returns `"Mice"` (inherited from the `Cat` class).
* `getLives()` returns `1`. Why? A cat may have nine lives, but a `Lion` doesn't, because its `getLives()` method was **overridden**.

</details>

A subclass will inherit every field and method it can see, i.e., every `public` member, `protected` member, and default member (provided they're in the same package).

`private` members can't be inherited, except by classes defined in the **same file**.
 
### Best Practice: `@Override`

For the `Lion` subclass, notice how we put `@Override` above the `getLives()` method:

````java
class Lion extends Cat {
    @Override
    public int getLives() {
        return 1;
    }
}
````

It's best practice to annotate an overridden method by placing the `@Override` annotation above it. That tells the compiler to ensure it's overriding a method from the base class.

Although this isn't required, it's a good defensive practice. In the future, if the base method happens to get renamed along the way, the code will fail to compile (because it's no longer overriding anything). Therefore, this will help with early detection of some subtle bugs that would result from calling an overridden method, which is actually a new method.

An overridden method can be of the same visibility as the method it's overriding, or it can be more public. However, it can't be more private.

Here are a few quick-hit facts about annotations:
* They start with the `@` symbol.
* They don't affect code functionality.
* They do affect the compiler's actions.
* They're not required, but it's best practice to use them.

> For more details about annotations, read through [this article](https://www.geeksforgeeks.org/annotations-in-java/).

### Restricting Inheritance

There are times when you want to ensure that no subclasses can be created from your class. In such cases, you can declare your class to be `final`. If you try to create a subclass from a `final` class, the compiler will warn you with a message such as:

```java
Error: java: cannot inherit from final class
``` 

Sometimes, you might want your class to be inherited from, but perhaps you don't want the subclass to change a particular method or field in your class. If needed, particular members can be marked `final`.

A subclass can override any method visible to it — except if the method is `final` or `static` — by defining the method using the exact same name and method signature as the base method it's overriding.

-----

## Guided Practice: Upcasting and Downcasting (15 min)

There's a subtle feature going on here that's leveraged in just about every Java library you'll use: You can declare a variable to be of a certain type and assign it any subtype.

For example:

````java
Cat cat = new Lion();
````

> **Knowledge Check**: If you call `cat.getLives()` after declaring it like we did above, what do you expect will be returned: the nine lives of the `Cat` or the one life of the `Lion`?

<details>
<summary>The answer may be surprising.</summary>

The rule to remember is that, when you call a method on a variable, you're really calling the method on the instance contained in that variable. So, in our case, because the `cat` variable contains a `Lion` instance, the `getLives()` method will return the one life of the `Lion` and not the nine of the parent `Cat`. This is generally a good thing, because library vendors can now define subclasses, and they'll function as the vendor intends.

In plain English, maybe you want a collection (like an `Array` or `ArrayList`) of cats, but you want to be able to put lions, cats, and anything else in the cat family — say tigers or leopards — in that same collection. That's known as **upcasting**.

Did the answer surprise you?

</details>

### Downcasting

Downcasting works as well, as long as you're casting to the correct object type.

So, we can say `Cat cat = new Lion()`, which — as we saw above — works fine because a lion is a cat. But we can't say `Lion lion = new Cat()` because, in general, cats are not lions.

However, given the assignment `Cat cat = new Lion()`, we know the `cat` variable contains a `Lion` instance; therefore, we should be able to assign it:

````java
Cat cat = new Lion();
Lion lion = cat;
````

However, that fails to compile, because the compiler has no way to be sure that, by the time the `cat` is assigned, it still contains a `Lion` instance. All lions are cats, but not all cats are lions.

To make that assignment, you must explicitly downcast it using the **cast** operator, as follows:

```java
Lion lion = (Lion)cat;
```

By preceding the `cat` variable with `(Lion)`, you're **casting** the `cat` instance to a `Lion` class, which is your way of signing a pact with the compiler guaranteeing that the variable will contain a `Lion` instance.

And if you break that pact, like below...

```java
public static void main(String[] args) {
    Cat cat = new Cat();
    Lion lion = (Lion)cat;
}
```
...You'll get a `ClassCastException` from the compiler.

----

## Independent Practice: Shapes (40 min)

Let's get into a little more depth with an example about `Shapes`.

### Part 1 (5 min)

Let's start by coding out this first part together. Say we have a superclass called `Shape`, as follows:

```java
package com.generalassembly.oop;

public class Shape {
    public double getCircumference() {
        return 0;
    }

    public double getArea() {
        return 0;
    }
}
```

Our `Shape` class has methods `getCircumference()` and `getArea()` that, depending on the shape, will calculate the circumference and area of the shape.

As we define some subclasses to inherit from `Shape`, remember that each of these shapes has a slightly different function to determine its area or circumference.

Let's start by defining a subclass of `Shape` called `Triangle` that takes three sides in its constructor:

```java
package com.generalassembly.oop;

public class Triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
}
```

### Part 2 (15 min)

Your assignment is to implement the `getCircumference()` and `getArea()` methods.

The circumference (or perimeter) is defined as the length of the outline of the shape. So, for a triangle, it's the sum of the sides, and for a circle, it's 2πr, where r is the radius and π is `Math.PI`.

The area is the square footage covered by the shape. For a triangle, we can use Heron's formula:

 ```
 √(p(p−a)*(p−b)*(p−c))    
 ```

In this case, `p = (a+b+c)/2`. To take a square root, use the `Math.sqrt()` method. 

When you're done, check your solution against the answer below.
 
<details>
<summary>Solution</summary>

```java 
package com.generalassembly.oop;

public class Triangle extends Shape{
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double getCircumference() {
        return side1 + side2 + side3;
    }

    @Override
    public double getArea() {
        double circumference = getCircumference();
        double p = circumference/2;
        double squared = p * (p-side1) * (p-side2) * (p-side3);
        double area = Math.sqrt(squared);
        return area;
    }
}
```

</details>

#### Part 2: Bonus

Do the same for `Circle` and `Rectangle`. Remember: These should all extend the `Shape` class.

<details>
<summary>Solution</summary>

```java
package com.generalassembly.oop;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

package com.generalassembly.oop;

public class Rectangle extends Shape {
    private int length;
    private int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getCircumference() {
        return 2*length + 2*width;
    }

    @Override
    public double getArea() {
        return length * width;
    }
}
```

</details>

### Part 3 (5 min)

Now let's say we want to create a `Square` shape. We remember from geometry class that a square is a special kind of rectangle in which all sides are equal.

Let's create a `Square` class that extends `Rectangle` and, instead of overriding the `getCircumference()` and `getArea()` methods, just provide a constructor that will pass in the same value for all sides into the `Rectangle` constructor.

> **Knowledge Check**: How do you think we should implement this? Shout out some possible solutions.

<details>
<summary>Solution</summary>

```java
public class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }
}
```

</details>

Now this `Square` will automatically calculate its circumference and area.

### Part 4 (15 min)

Finally, let's test our code by calculating the circumference and area for each of the following four shapes:

* A triangle with sides 3, 4, 5.
* A rectangle with length = 4 and height = 5.
* A circle with radius = 4.
* A square with side = 4.

Assign each one of these shapes to a variable of the appropriate type, then pass that shape to a method with signature: 

```java
private static String getCircumferenceAndArea(Shape shape)
```

That will return a `String` such as `ShapeType circumference, area = x, y`, with the correct values for each shape.

**Sample expected output:**

```java
Triangle circumference, area = 12.0, 6.0
Rectangle circumference, area = 18.0, 20.0
Circle circumference, area = 25.132741228718345, 50.26548245743669
Square circumference, area = 16.0, 16.0
```

When you're ready, check your answer with the solution below.

<details>
<summary>Need a hint?</summary>

The important thing to note here is that the `getCircumferanceAndArea()` method accepts a `Shape` argument and, even though our shapes are `Triangle`, `Rectangle`, `Circle`, and `Square`, the method still accepts them, as they extend `Shape`. Anything that extends a class **is** that class, so a `Rectangle` is a `Shape`.
</details>

<details>
<summary>Solution</summary>

```java
package com.generalassembly.oop;

public class ShapeCalc {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(3, 4, 5);
        Rectangle rectangle = new Rectangle(4, 5);
        Circle circle = new Circle(4);
        Square square = new Square(4);

        System.out.println("Triangle " + getCircumferenceAndArea(triangle));
        System.out.println("Rectangle " + getCircumferenceAndArea(rectangle));
        System.out.println("Circle " + getCircumferenceAndArea(circle));
        System.out.println("Square " + getCircumferenceAndArea(square));
    }

    private static String getCircumferenceAndArea(Shape shape) {
        String rval = "circumference, area = " + shape.getCircumference() + ", " + shape.getArea();
        return rval;
    }
}
```

</details>

----

## Conclusion (10 min)

That was an intense lesson! Let's recap.

### Tying Back to the Four Pillars

Combining subclasses and visibility, we can hide the details of functionality inside a class and just expose an API (application programming interface). Now that we've got inheritance in our tool belt, the uses of encapsulation (which includes things like the access level), abstraction, and polymorphism become a lot more salient.

#### Remember Polymorphism?

Polymorphism may sound scary, and it will keep cropping up. But the concept is easier to understand than the word suggests.

Now that we have a specific example of polymorphism, let's revisit the topic. We see that we can create various subclasses of the same class and implement specific functionality for each.

In our first example, we had `Cat` and `Lion`, and both had a `getLives()` method. However, their implementation was different despite sharing the same name.

In our second example, we had five different shapes — `Shape`, `Triangle`, `Circle`, `Rectangle`, and `Square` — all with different implementations of their `getCircumference()` and `getArea()` functionality. Yet they all worked just fine, and we didn't need to alter how we used the instance of each shape.

### Summary

Inheritance is here for our convenience and to keep our code DRY (that stands for "Don't Repeat Yourself"). It might seem like an intimidating concept, but once you master it, it can be a powerful tool. You'll find many examples of inheritance and shared properties and functionality in real life, which is exactly what our programming languages are meant to emulate and model.
