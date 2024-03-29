

|                     Title                    |  Type  | Duration |  Creator |
|:-------------------------------------------:|:------:|:--------:|:--------:|
| Functional Programming: Stream Processing and Collectors | Lesson |   1:35   | Kyle Dye |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Functional Programming: Stream Processing and Collectors

### Learning Objectives

At the end of this lesson, students will be able to:
* Define intermediate and terminal stream operations.
* Use different collectors to create the end result.
* Properly order intermediate stream operations.

---

### Lesson Guide

| Timing |         Type         |                                           Topic                                          |
|:------:|:--------------------:|:----------------------------------------------------------------------------------------:|
|  5 min |        Opening       |                                 Recap of Streams and Pipelines                     |
| 10 min |     Demo     |                         Different Ways to Create Streams                        |
| 15 min |         Demo         |   Intermediate Stream Operations   |
| 15 min |         Demo         |                      Terminal Stream Operations                     |
| 15 min |         Demo         |      Comparison-Based Stream Operations      |
| 15 min |         Demo         |      More Collectors     |
| 15 min | Independent Practice | Complete a Program Using Streams, Pipelines, and Collecting |
|  5 min |      Conclusion      |                                       Review/Recap                                       |

## Opening: Recap of Streams and Pipelines (5 min)

To recap, in the previous lesson, we learned that:
- A **stream** is a sequence of elements that carries values from a source through a pipeline via lambda expressions.
- A **pipeline** is a sequence of one or more stream operations, such as filtering, mapping, and aggregation.
- **Intermediate operations** produce other streams, while terminal operations produce resulting values.

In this lesson, we'll go into more detail about creating streams, different intermediate and terminal stream operations, and collectors.

The first demo will show you the different ways of creating a stream.

----

## Demo: Different Ways to Create Streams (10 min)

In our lessons thus far, we've only seen how to create a stream from a collection. For example:

    List<String> stringList = Arrays.asList("hello", "world");
    Stream stringStream = stringList.stream();
    stringStream.forEach(value -> System.out.println(value));

But, you don't need to start with a collection to create a stream. You can create one from an array:

    Integer[] intArray = new Integer[]{1, 2, 3, 4};
    Stream intArrayStream = Stream.of(intArray);
    intArrayStream.forEach(value -> System.out.println(value));

`Stream.of` is a quick way to create a stream from your custom objects:

    Stream objectStream = Stream.of(100, 200, 300);
    objectStream.forEach(value -> System.out.println(value));

You can also create streams using `StreamBuilder`, which is usually more efficient than the previous examples because it doesn't need to use an array or `ArrayList` as a buffer when adding elements to the stream.

```java
Stream.Builder<String> stringStreamBuilder = Stream.builder();

stringStreamBuilder.accept("I really love ");
stringStreamBuilder.accept("streams and ");
stringStreamBuilder.accept("lambda expressions!!!");

Stream<String> stringBuilderStream = stringStreamBuilder.build();
stringBuilderStream.forEach(value -> System.out.println(value));
```

The output is:

```text
I really love 
streams and 
lambda expressions!!! 
```

In the previous lesson, we learned that stream operations are used to modify streams to produce a result. In the following sections, we'll discuss several types of stream operations: intermediate, terminal, and comparison.

- **Intermediate operations** are used for filtering, manipulating, and aggregating stream data.
- **Terminal operations** are used to derive a result from a stream.
- **Comparison operations** are intermediate or terminal operations that compare items in a stream.

In the following sections and demos, we'll see when and why you'd use each type.

-----

## Demo: Intermediate Stream Operations (15 min)

We've learned that intermediate stream operations take a stream as input, modify the stream, and output a resulting stream.

The key takeaways about intermediate stream operations from this section should be: 
- Intermediate stream operations quite surprisingly don't produce an end result. They use lazy evaluation, which means that stream processing doesn't activate until a **terminal stream operation** is encountered.
- **The order of your stream operations matter.** Ordering them incorrectly could have a negative impact on performance.

In this demo, we'll first look at some commonly used intermediate stream operations. After that, we'll see that no processing happens until a terminal operation occurs. Finally, we'll look at what happens when you don't order your intermediate operations correctly.

### Commonly Used Intermediate Stream Operations

#### `filter()`

`filter()` selects elements based on a condition you provide:
    
    List<Integer> numberList = Arrays.asList(1, 2, 50, 100);
    
    // filter(): Get numbers greater than 50.
    List<Integer> filteredNumberList =
            numberList.stream()
            .filter(number -> number > 50)
            .collect(Collectors.toList());
    
    filteredNumberList.forEach(number -> System.out.println(number));
    
The output is:

`100`

For the next demo examples, assume we have the following `Person` class:

    public class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person {" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


#### `map()`

`map()` is used to replace each incoming object from the source stream with a different object in the output stream, where the new object is formed by applying a lambda expression to the incoming object:

    List<Person> personList =
            Arrays.asList(
                    new Person("Tom", 30),
                    new Person("Jane", 45),
                    new Person("Jeff", 70));

    // map(): Given the Person list, create a new list of their ages.
    List<Integer> ageList =
            personList.stream()
            .map(person -> person.getAge())
            .collect(Collectors.toList());
    
    ageList.forEach(age -> System.out.println(age));


The output is: 

`30`  
`45`  
`70`  

#### `flatmap()`

`flatmap()` is used to combine (or "flatten") multiple incoming streams into a single stream.

The following example will flatten a list of `Person` lists (i.e., `List<List<Person>>`) into just a `Person` list (i.e., `List<Person>`):

    List<Person> personList1 =
            Arrays.asList(
                    new Person("Tom", 30),
                    new Person("Jeff", 70));

    List<Person> personList2 =
            Arrays.asList(
                    new Person("Jane", 45),
                    new Person("Alice", 38));
    
    // Create a list of lists, containing both of our Person lists above.
    List<List<Person>> listOfPersonLists = Arrays.asList(personList1, personList2);

    // flatMap(): Flatten the list of person lists.
    List<Person> flatPersonList =
            listOfPersonLists.stream()
                .flatMap(Collection::stream) // Convert each list into a stream, then flatten them all into a single stream.
                .collect(Collectors.toList());
    
    flatPersonList.forEach(person -> System.out.println(person));

The output is:  

`Person {name='Tom', age=30}`  
`Person {name='Jeff', age=70}`  
`Person {name='Jane', age=45}`  
`Person {name='Alice', age=38}`  

> The `Collection::stream` line basically says, "Take the list streams from both lists and merge them."

#### `sorted()`

`sorted()` is used to sort elements in a stream based on a given comparison.

The following example will sort the `Person` list alphabetically:

    // sorted: sort the person list alphabetically
    List<Person> sortedList =
            personList.stream()
            .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
            .collect(Collectors.toList());
    
    sortedList.forEach(person -> System.out.println(person));

The output is:  

`Person {name='Jane', age=45}`  
`Person {name='Jeff', age=70}`  
`Person {name='Tom', age=30}`  

### Stream Processing Doesn't Start Until We Have a Terminal Stream Operation  

We mentioned earlier that stream processing doesn't begin until a terminal stream operation is encountered, thanks to lazy evaluation. Here's an example:

    personList.stream()
        .filter(person -> {
            System.out.println("Person is: " + person);
            return person.getAge() > 30;
        });

If we tried to run the code above, there would be no output to the console.

Now, let's add a terminal operation and see what happens:

    personList.stream()
        .filter(person -> {
            System.out.println("Person is: " + person);
            return person.getAge() > 30;
        })
        .collect(Collectors.toList());


The output is:  

`Person is: Person {name='Jane', age=45}`  
`Person is: Person {name='Jeff', age=70}`  

### Why Ordering Matters With Intermediate Operations   

The last example for this demo will show you how ordering your intermediate operations improperly can cause unnecessary processing.

Take a look at the following example:

    List<Person> largePersonList =
        Arrays.asList(
                new Person("Tom", 30),
                new Person("John", 29),
                new Person("Jenny", 20),
                new Person("Mark", 35),
                new Person("Chris", 37),
                new Person("Paige", 31),
                new Person("Helen", 60),
                new Person("Erin", 50),
                new Person("Zach", 10),
                new Person("Jane", 45),
                new Person("Jeff", 70));

    List<Person> sortedAndFilteredList =
        largePersonList.stream()
            .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
            .filter(person -> person.getAge() > 31)
            .filter(person -> person.getName().startsWith("J"))
            .collect(Collectors.toList());

    sortedAndFilteredList.forEach(person -> System.out.println(person));

The output is:  

`Person {name='Jane', age=45}`  
`Person {name='Jeff', age=70}`  

If you notice, we did the sorting first, followed by the filtering. The `sorted()` operation has to look through all 11 `Person` objects. Then, the `filter()` operation has to again look through all 11 `Person` objects. The last `filter()` operation has to look through six `Person` objects due to the previous age filter. That's 28 total iterations, including a relatively large sorting of 11 items.

> **Knowledge Check**: What if we reordered the intermediate stream operations in the pipeline by putting the filters before the sort? Which filter should be first?

I would say to pick the filter that produces the least number of results. So, which filter would that be?

The age `filter()` returns six results, whereas the name `filter()` returns four. Let's redo the example and see how many total iterations it will be after reordering:

    List<Person> filteredAndSortedList =
        largePersonList.stream()
            .filter(person -> person.getName().startsWith("J"))
            .filter(person -> person.getAge() > 31)
            .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
            .collect(Collectors.toList());

    filteredAndSortedList.forEach(person -> System.out.println(person));


The output is:  

`Person {name='Jane', age=45}`  
`Person {name='Jeff', age=70}`  

The first name `filter()` takes 11 iterations to filter the stream down to four. The age `filter()` takes four iterations to filter the stream down to two `Person` objects. Lastly, you have to sort those two `Person` objects in the stream. That's a total of 17 iterations, including a small sort of just two items. We've cut out 11 unnecessary iterations and changed a long sort into a short one, just by reordering the pipeline. The moral of the story: Filter first.

-----

## Terminal Stream Operations (15 min)

As we mentioned in the last functional programming module, terminal operations such as `forEach()` mark the stream as consumed, after which it can no longer be used.  

Some commonly used terminal stream operations are:  
- `findFirst()`: Returns the first entry in a stream.
- `collect()`: Repackages the elements from the stream into another data structure such as a `List` or `Map`.
- `toArray()`: Converts the stream to an array.

We'll discuss several other useful terminal operations in the "Comparison-Based Stream Operations" section.

### Demo: Terminal Stream Operations  

For this short demo, we'll show examples of using `findFirst()`, `collect()`, and `toArray()`. We'll also use the same `Person` class in the previous demo:

    public class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person {" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

### `findFirst()`

Find the first person over the age of 35:

    Person personOverThirtyFive =
        largePersonList.stream()
            .filter(person -> person.getAge() > 35)
            .findFirst()
            .orElse(null);

    System.out.println(personOverThirtyFive);

The output is:  

`Person {name='Chris', age=37}`

**Note**: Some stream operations such as `min()`, `max()`, and `findFirst()` return an instance of the `Optional` class. This will be covered in detail in another module, but for now, `Optional` is a data structure that must contain exactly one value or no value. If no value, the `Optional.orElse()` method returns the supplied value — in this case, `null`. If one, `orElse` returns that value.

So, in the example, if no result is found, it will return `null`. However, we did find a result.

### `collect()`

Find persons whose names start with the letter `"J"`.

    largePersonList.stream()
        .filter(person -> person.getName().startsWith("J"))
        .collect(Collectors.toList())
    .forEach(person -> System.out.println(person));
    
Notice that, because `collect(Collectors.toList())` is a terminal operation that produces a list, we can immediately call another terminal operation (e.g., `forEach()`) on that list to print the elements of the resulting list.

The output is:  

`Person {name='John', age=29}`  
`Person {name='Jenny', age=20}`  
`Person {name='Jane', age=45}`  
`Person {name='Jeff', age=70}`

### `toArray()`

Create an array from `largePersonList`:

    Person[] personArray =
        personList.toArray(new Person[personList.size()]);

    System.out.println(personArray[0]);
    System.out.println(personArray[1]);
    System.out.println(personArray[2]);
    System.out.println(personArray[3]);
    System.out.println(personArray[4]);
    System.out.println(personArray[5]);
    System.out.println(personArray[6]);
    System.out.println(personArray[7]);
    System.out.println(personArray[8]);
    System.out.println(personArray[9]);
    System.out.println(personArray[10]);

The output is:  

`Person {name='Tom', age=30}`  
`Person{name='John', age=29}`  
`Person{name='Jenny', age=20}`  
`Person{name='Mark', age=35}`  
`Person{name='Chris', age=37}`  
`Person{name='Paige', age=31}`  
`Person{name='Helen', age=60}`  
`Person{name='Erin', age=50}`  
`Person{name='Zach', age=10}`  
`Person{name='Jane', age=45}`  
`Person{name='Jeff', age=70}`

`toArray()` is a convenient method on every collection that returns the elements of the collection as an array.

-----

## Comparison-Based Stream Operations (15 min)

In this section, we'll discuss stream operations that can be used for comparing items in a stream. They are:

- `sorted()`: An intermediate operation that sorts a stream.
- `min()`: A terminal operation that returns the minimum element based on a comparison.
- `max()`: A terminal operation that returns the maximum element based on a comparison.
- `distinct()`: An intermediate operation that eliminates duplicates in a stream based on the `equals()` method of the stream elements.

The following demo gives a little more detail on each one.

### Demo: Comparison-Based Stream Operations

In this demo, we'll be using the same `Person` class from the previous demos:

    public class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person {" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

### `sorted()` 

We saw this in the "Intermediate Stream Operations" section. The following example sorts the `Person` list alphabetically:

    List<ComparisonBasedStreamOperations.Person> largePersonList =
            Arrays.asList(
                    Person("Tom", 30),
                    Person("John", 29),
                    Person("Jenny", 20),
                    Person("Mark", 35),
                    Person("Chris", 37),
                    Person("Paige", 31),
                    Person("Helen", 60),
                    Person("Erin", 50),
                    Person("Zach", 10),
                    Person("Jane", 45),
                    Person("Jeff", 70));

    List<ComparisonBasedStreamOperations.Person> sortedList =
            largePersonList.stream()
                    .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
                    .collect(Collectors.toList());

    sortedList.forEach(person -> System.out.println(person));

The output is:  

`Person {name='Chris', age=37}`  
`Person {name='Erin', age=50}`  
`Person {name='Helen', age=60}`  
`Person {name='Jane', age=45}`  
`Person {name='Jeff', age=70}`  
`Person {name='Jenny', age=20}`  
`Person {name='John', age=29}`  
`Person {name='Mark', age=35}`  
`Person {name='Paige', age=31}`  
`Person {name='Tom', age=30}`  
`Person {name='Zach', age=10}`  

### `min()`

The example below will return the youngest person. We can use `Comparator.comparing` for the lambda expression to specify which class property to use:

    Person youngestPerson =
        largePersonList.stream()
            .min(Comparator.comparing(Person::getAge))
            .orElse(null);

    System.out.println(youngestPerson);

The output is:   

`Person {name='Zach', age=10}`  

### `max()`

This example will return the oldest person: 

    Person oldestPerson =
            largePersonList.stream()
                    .max(Comparator.comparing(Person::getAge))
                    .orElse(null);

    System.out.println(oldestPerson);

The output is:  

`Person {name='Jeff', age=70}`  

### `distinct()`

The following example will take a stream of integers that contains duplicates and return a clone of that stream with duplicates removed:

    Stream<Integer> integerStream = Stream.of(1, 1, 2, 2, 3, 4, 5);

    integerStream
        .distinct()
        .forEach(integer -> System.out.println(integer));

The output is:  

`1`  
`2`  
`3`  
`4`  
`5`  

-----

## More Collectors (15 min)

In the previous module, we talked briefly about collectors. Again, collectors are used to collect data out of a stream and put it into a data structure. In this section, we'll look at other data structures to which we can output. They are:  

- `toSet()`: Similar to `toList()` but returns a `Set` (which is an unordered collection of unique elements).
- `toMap()`: Creates a `Map` of key-value pairs.
- `groupingBy()`: Allows you to partition a stream into groups. Each key can have **one or more** values (a `Collection`).

### Demo: More Collectors

We'll use the following class in some examples:

    public static class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person {" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            
            if (!(o instanceof Person)) return false;
            
            Person person = (Person) o;
            
            return age == person.age &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

### `toSet()`

The example below will take a list that contains one duplicate and create a `Set` that discards the duplicates. Like usual, duplicates are defined as values that return `true` when compared using the `equals()` method:

    List<CollectorsDemo.Person> duplicatePersonList =
        Arrays.asList(
                new CollectorsDemo.Person("Tom", 30),
                new CollectorsDemo.Person("Tom", 30),
                new CollectorsDemo.Person("Jenny", 20));
                
    Set<Person> personSet =
        duplicatePersonList.stream()
            .collect(Collectors.toSet());

    personSet.stream().forEach(person -> System.out.println(person));

The output is:  

`Person {name='Tom', age=30}`  
`Person {name='Jenny', age=20}`  

### `toMap()`

The example below will create a `Map`, where the key is the person's name and the value is the age:
    
    Map<String, Integer> nameToAgeMap =
        largePersonList.stream()
            .collect(Collectors.toMap(person -> person.getName(), person -> person.getAge()));

    nameToAgeMap.forEach((key, value) -> System.out.println("Name is " + key + " and age is " + value));

Notice that each entry in the `Map` contains **one** `String` key and **one** `Integer` value. Two lambdas are used for each element: one to define the key and the second to define the value.

The output is: 

`Name is Erin and age is 50`  
`Name is Paige and age is 31`  
`Name is Tom and age is 30`  
`Name is Zach and age is 10`  
`Name is Chris and age is 37`  
`Name is Jeff and age is 70`  
`Name is John and age is 29`  
`Name is Mark and age is 35`  
`Name is Jenny and age is 20`  
`Name is Jane and age is 45`  
`Name is Helen and age is 60`  
`Name is Tom and age is 30`  
`Name is Jenny and age is 20`  

### A Special Note About `toMap()` and Duplicate Keys

If `toMap()` encounters a duplicate key, then an exception will be thrown. For example:  

    Map<String, Integer> nameToAgeDuplicateMap =
        duplicatePersonList.stream()
                .collect(Collectors
                        .toMap(person -> person.getName(),
                                person -> person.getAge()));

The output is:  

`Exception in thread "main" java.lang.IllegalStateException: Duplicate key 30`  

You can avoid this by passing a lambda expression as the third argument to `toMap()`, which will define the value to use when a duplicate is encountered. For example:

    Map<String, Integer> nameToAgeDuplicateMap =
            duplicatePersonList.stream()
                    .collect(Collectors
                            .toMap(person -> person.getName(),
                                    person -> person.getAge(),
                                    (first, second) -> second));

    nameToAgeDuplicateMap.forEach((key, value) -> System.out.println("Name is " + key + " and age is " + value));

The output is:  

`Name is Tom and age is 30`  
`Name is Jenny and age is 20`  

If you look at the lambda expression that's provided to `toMap()`:

    (first, second) -> second

This is essentially saying to take the last duplicate encountered. If we specified `first`, then it would take the first value and ignore all duplicates from that point on. 

### `groupingBy()`

`groupingBy()` is an advanced partitioning method that allows you to create custom groupings. The example below will group all of the persons based on the first letter of their name. The lambda expression passed to the `groupingBy()` method tells the collector what to use for the `Map` key. Notice that the value of the `Map` is a list of **one or more `Person` instances**:

    Map<Character, List<Person>> firstInitialMap =
            largePersonList.stream()
                .collect(Collectors.groupingBy(person -> person.getName().charAt(0)));


    // Check the number of elements in the map. It should be 8.
    System.out.println("Number of distinct first initials in the map is " + firstInitialMap.size());

    System.out.println("Persons with initial T");
    firstInitialMap.get('T').forEach(person -> System.out.println(person));

    System.out.println("Persons with initial J");
    firstInitialMap.get('J').forEach(person -> System.out.println(person));

    System.out.println("Persons with initial M");
    firstInitialMap.get('M').forEach(person -> System.out.println(person));

    System.out.println("Persons with initial C");
    firstInitialMap.get('C').forEach(person -> System.out.println(person));

    System.out.println("Persons with initial P");
    firstInitialMap.get('P').forEach(person -> System.out.println(person));

    System.out.println("Persons with initial H");
    firstInitialMap.get('H').forEach(person -> System.out.println(person));

    System.out.println("Persons with initial E");
    firstInitialMap.get('E').forEach(person -> System.out.println(person));

    System.out.println("Persons with initial Z");
    firstInitialMap.get('C').forEach(person -> System.out.println(person));

The output is:  

`Number of distinct first initials in the map is 8`  
`Persons with initial T`  
`Person {name='Tom', age=30}`  
`Persons with initial J`  
`Person {name='John', age=29}`  
`Person {name='Jenny', age=20}`  
`Person {name='Jane', age=45}`  
`Person {name='Jeff', age=70}`  
`Persons with initial M`  
`Person {name='Mark', age=35}`  
`Persons with initial C`  
`Person {name='Chris', age=37}`  
`Persons with initial P`  
`Person {name='Paige', age=31}`  
`Persons with initial H`  
`Person {name='Helen', age=60}`  
`Persons with initial E`  
`Person {name='Erin', age=50}`  
`Persons with initial Z`  
`Person {name='Zach', age=10}`  

-----

## Independent Practice (15 min)

For this independent practice, we'll take what we've learned in this module and put them to use. 

**Hint**: You'll need to use...
- `filter()`
- `min()` 
- `max()` 
- `toMap()` 
- `groupingBy()`

### Independent Practice Template

    package com.ga.examples;

    import java.util.Arrays;
    import java.util.List;
    import java.util.Objects;

    public class IndependentPractice {

        public static class Person {

            private String name;
            private String gender;
            private int age;
            private int salary;

            public Person(String name, String gender, int age, int salary) {
                this.name = name;
                this.gender = gender;
                this.age = age;
                this.salary = salary;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getSalary() {
                return salary;
            }

            public void setSalary(int salary) {
                this.salary = salary;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                
                if (!(o instanceof Person)) return false;
                
                Person person = (Person) o;
                
                return age == person.age &&
                        salary == person.salary &&
                        Objects.equals(name, person.name) &&
                        Objects.equals(gender, person.gender);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, gender, age, salary);
            }

            @Override
            public String toString() {
                return "Person {" +
                        "name='" + name + '\'' +
                        ", gender='" + gender + '\'' +
                        ", age=" + age +
                        ", salary=" + salary +
                        '}';
            }
        }

        public static void main(String[] args) {

            List<Person> largePersonList = Arrays.asList(
                new IndependentPractice.Person("Tom", "Male", 30, 50000),
                new IndependentPractice.Person("John", "Male", 30, 60000),
                new IndependentPractice.Person("Jenny", "Female",  20, 70000),
                new IndependentPractice.Person("Mark", "Male", 35, 30000),
                new IndependentPractice.Person("Chris", "Male", 37, 20000),
                new IndependentPractice.Person("Paige", "Female",  31, 25000),
                new IndependentPractice.Person("Helen", "Female",  60, 100000),
                new IndependentPractice.Person("Erin", "Female",  50, 500000),
                new IndependentPractice.Person("Zach", "Male", 10, 1000),
                new IndependentPractice.Person("Jane", "Female",  45, 200000),
                new IndependentPractice.Person("Jeff", "Male", 70, 80000)
            );

            // TODO: Find the person with the highest salary.

            // TODO: Find the person with the lowest salary.

            // TODO: Create a map where the key is age and the value is the name of the person. For any duplicate keys, use
            // the first entry and ignore any duplicates.

            // TODO: Create a map where the key is the gender and the value is a list of Persons. Filter the results to only
            // include persons over the age of 30 who have a salary greater than 20,000.
        }
    }

-----

## Conclusion (5 min)

To recap, we've learned a great deal about streams and how to manipulate them using pipelines of intermediate and terminal operations. After going through this module, you can understand the power of streams and pipelines. Their biggest benefit is saving you from having to write a lot of boilerplate code to convert data structures, making your code much more readable and maintainable. The syntax can get tricky at times, so the best thing is to go slowly, try things out, let the IDE recommend changes, and work through examples.

With that said, here are a few review questions:

 - Let's say you only wanted your stream to contain objects of a certain criteria. What stream operation might you use?
 - If you have a scenario where you have to both sort and filter your stream, how would you order the pipeline to ensure you don't do more processing than what's needed?
 - If you wanted to partition stream data into a `Map`, where the value of the `Map` was a list, would you use a `Collectors.toMap()` or `Collectors.groupingBy()`?


### Additional Resources
- [A Guide to Streams in Java 8: An In-Depth Tutorial With Examples](https://stackify.com/streams-guide-java-8/)
