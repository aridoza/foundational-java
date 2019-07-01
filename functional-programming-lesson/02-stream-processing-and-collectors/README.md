

|                     Title                    |  Type  | Duration |  Creator |
|:-------------------------------------------:|:------:|:--------:|:--------:|
| Functional Programming - Stream Processing and Collectors | lesson |   1:30   | Kyle Dye |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Functional Programming | Stream Processing and Collectors

### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
* Have a strong understanding about intermediate and terminal stream operations
* Know how to leverage different collectors to create your end result
* Understand the importance of ordering your intermediate stream operations

### STUDENT PRE-WORK
*Before this lesson, you should already be able to:*
- Understand the Java Basics lessons
- Understand the OOP in Java lessons
- Understand the Intro to Lambda Expressions

### INSTRUCTOR PREP
*Before this lesson, instructors will need to:*
- Read through the lesson
- Add additional instructor notes as needed
- Edit language or examples to fit your ideas and teaching style
- Open, read, run, and edit (optional) the starter and solution code to ensure it's working and that you agree with how the code was written

---

### LESSON GUIDE

| TIMING |         TYPE         |                                           TOPIC                                          |
|:------:|:--------------------:|:----------------------------------------------------------------------------------------:|
|  5 min |        Opening       |                                 Recap of Streams and Pipelines                                |
| 5 min |     Demo     |                         Show the different ways to create a stream                        |
| 5 min |         Introduction         |   Intermediate Stream Operations   |
| 10 min |     Demo     |                          Ordering of Intermediate Stream operations                         |
|  5 min |         Introduction         |                      Terminal Stream Operations                     |
| 5 min |     Demo     |                         Common Terminal Stream Operations                        |
|  5 min |         Introduction         |      Comparison Based Stream Operations      |
| 10 min |     Demo     |                                      Comparison Based Stream Operations               
|  5 min |         Introduction         |      More Collectors     |
| 10 min |     Demo     |                                      Demo the different ways to use collectors                                       
| 20 min | Independent Practice | Take what we've learned and complete a program using streams, pipelines, and collecting |
|  5 min |      Conclusion      |                                       Review/Recap                                       |

## Opening - Recap of Streams and Pipelines  
To recap, in the previous lesson we learned that:
- a stream is a sequence of elements that carries values from a source through a pipeline via lambda expressions 
- We learned that a pipeline is a sequence of stream operations, such as filtering and aggregation operations.
- We learned the difference between intermediate and terminal stream operations

In this lesson we will go in more detail about how to create streams, the different intermediate and terminal stream operations, and collectors.  

The first demo will show you the different ways that you can create a Stream.

## Demo - Different Ways to Create Streams
In our lessons thus far, you've seen one way to create a stream via a collection.

    List<String> stringList = Arrays.asList("hello", "world");
    Stream stringStream = stringList.stream();
    stringStream.forEach(value -> System.out.println(value));

You can create a stream from an array:

    Integer[] intArray = new Integer[]{1,2,3,4};
    Stream intArrayStream = Stream.of(intArray);
    intArrayStream.forEach(value -> System.out.println(value));

You can create a stream from individual objects:

    Stream objectStream = Stream.of(100,200,300);
    objectStream.forEach(value -> System.out.println(value));

You can create streams by leveraging the Stream Builder:

    Stream.Builder<String> stringStreamBuilder = Stream.builder();

    stringStreamBuilder.accept("I really love ");
    stringStreamBuilder.accept("streams and ");
    stringStreamBuilder.accept("lambda expressions!!! ");

    Stream stringBuilderStream = stringStreamBuilder.build();
    stringBuilderStream.forEach(value -> System.out.println(value));


## Introduction - Intermediate Stream Operations  
In the previous lesson we learned that stream operations are used to modify streams to create an end result. We also learned that intermediate stream operations take a stream as input, modifies the stream, and outputs a result stream. In this section, we will discuss some of the commonly used intermediate stream operations.  

The key takeaways that you should have about intermediate stream operations after this section should be:  
- **Intermediate stream operations do not produce an end result. They use lazy evaluation, meaning stream processing does not activate until a terminal stream operation is specified.**
- **The order of your stream operations matter.  Not ordering them correctly could have performance impacts.**

## Demo - Intermediate Stream Operations  
In this demo will first take a look at some of the commonly used intermediate stream operations.  After that we will see that no processing happens until a terminal operation occurs. Lastly, we will look at what happens when you don't order your intermediate operations correctly.

### Commonly Used Intermediate Stream Operations

**Filter: selects elements based on a condition you provide**  
    
    //Filter - Get numbers greater than 50
    List<Integer> numberList = Arrays.asList(1,2,50,100);
    List<Integer> filteredNumberList =
            numberList.stream()
            .filter(number -> number > 50)
            .collect(Collectors.toList());
    filteredNumberList.stream().forEach(number -> System.out.println(number));
    
Outputs:  
100

For the next demo examples, assume we have the following Person class:

    public class Person {

        private String name;
        private int age;

        public Person(String name,
                      int age) {
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
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


**Map: is used to transform one object into a different object by using a lambda expression**  

    //Map - Take the Person list and create a list of their ages.
    List<Person> personList =
            Arrays.asList(
                    new Person("Tom", 30),
                    new Person("Jane", 45),
                    new Person("Jeff", 70));

    List<Integer> ageList =
            personList.stream()
            .map(person -> person.getAge())
            .collect(Collectors.toList());
    ageList.stream().forEach(age -> System.out.println(age));


Output is:  
30  
45  
70  

**Flatmap: is used to flatten data structures such as collections to help with stream operations**  
The following example will flatten a list of person lists, List<List<Person>>, into just a list of persons, List<Person>.  

    //FlatMap - flatten the list of person lists
    List<Person> personList1 =
            Arrays.asList(
                    new Person("Tom", 30),
                    new Person("Jeff", 70));

    List<Person> personList2 =
            Arrays.asList(
                    new Person("Jane", 45));

    List<List<Person>> listOfPersonLists = Arrays.asList(personList1, personList2);

    List<Person> flatPersonList =
            listOfPersonLists.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    flatPersonList.stream().forEach(person -> System.out.println(person));

Output is:  
Person{name='Tom', age=30}  
Person{name='Jeff', age=70}  
Person{name='Jane', age=45}  

The Collection:stream line basically says take the list streams from both lists and merge them.  

  
**Sorted: is used to sort elements in a stream based on a given comparator**  
The following example will sort the list of persons by name. 

    //Sorted - Sort the person list by name alphabetically
    List<Person> sortedList =
            personList.stream()
            .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
            .collect(Collectors.toList());
    sortedList.stream().forEach(person -> System.out.println(person));

Output is:  
Person{name='Jane', age=45}  
Person{name='Jeff', age=70}  
Person{name='Tom', age=30}  

### Stream Processing Doesn't Start Until We Have A Terminal Stream Operation  
We mentioned earlier that stream processing doesn't start until a terminal stream operation is encountered, due to lazy evaluation.  See the example below:

    personList.stream()
        .filter(person -> {
            System.out.println("Person is: " + person);
            return person.getAge() > 30;
        });

If we tried to run the code above, there would be no output to the console. Now let's add a terminal operation and see what happens:

    personList.stream()
        .filter(person -> {
            System.out.println("Person is: " + person);
            return person.getAge() > 30;
        })
        .collect(Collectors.toList());


The output is:  
Person is: Person{name='Tom', age=30}  
Person is: Person{name='Jane', age=45}  
Person is: Person{name='Jeff', age=70}  

### How Ordering is Important With Intermediate Operations   
The last example for this demo will show you how not properly ordering your intermediate operations can cause unnecessary processing.  Take a look at the following example:

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

    sortedAndFilteredList.stream().forEach(person -> System.out.println(person));

Output is:  
Person{name='Jane', age=45}  
Person{name='Jeff', age=70}  

If you notice, we do the sort first and then the filtering.  The sort has to look through all 11 Person objects.  The filter by age has to look through all 11 Person objects.  The last filter by names that start with "J" has to look through 6 Person objects due to the previous age filter.  That's 28 total iterations.  

What if we reordered the intermediate stream operations in the pipeline by putting the filters first.  Which filter should be first?  I would say to pick the filter that produces the least results.  Which filter would that be? The age filter returns 6 results, where as, the name filter returns 4.  Let's redo the example and see how many total iterations it will be after reordering.

    List<Person> filteredAndSortedList =
        largePersonList.stream()
            .filter(person -> person.getName().startsWith("J"))
            .filter(person -> person.getAge() > 31)
            .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
            .collect(Collectors.toList());

    filteredAndSortedList.stream().forEach(person -> System.out.println(person));


Output is:  
Person{name='Jane', age=45}  
Person{name='Jeff', age=70}  

The first name filter takes 11 iterations to filter the stream down to 4.  The age filter takes 4 iterations to filter the stream down to 2 Person objects.  Lastly, you have the sort of those 2 Person objects in the stream.  That's a total of 17 iterations.  We've cut out 11 unnecessary iterations just by reordering the pipeline!

## Introduction - Terminal Stream Operations
In the last section we learned about intermediate stream operations and how they can be used to manipulate streams. We also learned that stream processing doesn't start until there is a terminal stream operation. We will use this section to discuss terminal stream operations. As mentioned in the last functional programming module, terminal operations, such as forEach(), mark the stream as consumed, after which point it can no longer be used further.  

Some of the commonly used terminal stream operations are:  
- forEach - used to iterate through a stream
- findFirst - returns the first entry in a stream
- collect - repackages the stream to a different data structure
- toArray - converts the stream to an array

There are other useful terminal operations that will be discussed further in the "Comparison Based Stream Operations" section.

## Demo - Terminal Stream Operations  
For this short demo, we are going to show examples of using forEach, findFirst, collect, and toArray.  We will also leverage the same Person class that was used in the previous demo.

    public class Person {

        private String name;
        private int age;

        public Person(String name,
                      int age) {
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
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

### forEach
Print out each person's name.

    List<TerminalOperations.Person> largePersonList =
        Arrays.asList(
                new TerminalOperations.Person("Tom", 30),
                new TerminalOperations.Person("John", 29),
                new TerminalOperations.Person("Jenny", 20),
                new TerminalOperations.Person("Mark", 35),
                new TerminalOperations.Person("Chris", 37),
                new TerminalOperations.Person("Paige", 31),
                new TerminalOperations.Person("Helen", 60),
                new TerminalOperations.Person("Erin", 50),
                new TerminalOperations.Person("Zach", 10),
                new TerminalOperations.Person("Jane", 45),
                new TerminalOperations.Person("Jeff", 70));

    //forEach - print out each person's name
    largePersonList.stream()
        .forEach(person -> System.out.println("Hello my name is " + person.getName()));

Output:  
Hello my name is Tom  
Hello my name is John  
Hello my name is Jenny  
Hello my name is Mark  
Hello my name is Chris  
Hello my name is Paige  
Hello my name is Helen  
Hello my name is Erin  
Hello my name is Zach  
Hello my name is Jane  
Hello my name is Jeff  


### findFirst
Find the first person over the age of 35.

    Person personOverThirtyFive =
        largePersonList.stream()
            .filter(person -> person.getAge() > 35)
            .findFirst()
            .orElse(null);

    System.out.println(personOverThirtyFive);

Output:  
Person{name='Chris', age=37}

### collect
Find persons whose name starts with the letter "J". Notice that since the collect is a terminal operation, we can immediately call another terminal operation "forEach" to print the results.

    largePersonList.stream()
        .filter(person -> person.getName().startsWith("J"))
        .collect(Collectors.toList())
    .forEach(person -> System.out.println(person));

Output:  
Person{name='John', age=29}  
Person{name='Jenny', age=20}  
Person{name='Jane', age=45}  
Person{name='Jeff', age=70}

### toArray
Create an array from the largePersonList.

   Person[] personArray =
        largePersonList.stream()
            .toArray(Person[]::new);

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

Output:  
Person{name='Tom', age=30}  
Person{name='John', age=29}  
Person{name='Jenny', age=20}  
Person{name='Mark', age=35}  
Person{name='Chris', age=37}  
Person{name='Paige', age=31}  
Person{name='Helen', age=60}  
Person{name='Erin', age=50}  
Person{name='Zach', age=10}  
Person{name='Jane', age=45}  
Person{name='Jeff', age=70}

## Introduction - Comparison Based Stream Operations
In the past sections of this module, we discussed commonly used intermediate and terminal operations.  In this section, we will discuss useful stream operations that can be used for comparing items in a stream.  They are:

- sorted - intermediate operation that will sort a stream
- min - terminal operation that will return the minimum element based on a comparator
- max - terminal operation that will return the maximum element based on a comparator
- distinct - intermediate operation that will eliminate duplicates in a stream based on the equals() method of the stream elements
- allMatch - terminal operation that given a predicate, returns a boolean true if all elements in the stream satisfy the predicate
- anyMatch - terminal operation that given a predicate, returns a boolean true if one of elements in the stream satisfy the predicate
- noneMatch - terminal operation that given a predicate, returns a boolean true if none of the elements in the stream satisfy the predicate

The following demo will go into a little more detail of each one.

## Demo - Comparison Based Stream Operations
In this demo, we will take a look at each of the comparison based stream operations mentioned in the introduction. We will also be using the same Person class from the previous demos.

    public class Person {

        private String name;
        private int age;

        public Person(String name,
                      int age) {
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
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

### sorted 
We saw an example of this in the intermediate stream operation section.  The following example sorts the list of persons by name.

    List<ComparisonBaseStreamOperations.Person> largePersonList =
            Arrays.asList(
                    new ComparisonBaseStreamOperations.Person("Tom", 30),
                    new ComparisonBaseStreamOperations.Person("John", 29),
                    new ComparisonBaseStreamOperations.Person("Jenny", 20),
                    new ComparisonBaseStreamOperations.Person("Mark", 35),
                    new ComparisonBaseStreamOperations.Person("Chris", 37),
                    new ComparisonBaseStreamOperations.Person("Paige", 31),
                    new ComparisonBaseStreamOperations.Person("Helen", 60),
                    new ComparisonBaseStreamOperations.Person("Erin", 50),
                    new ComparisonBaseStreamOperations.Person("Zach", 10),
                    new ComparisonBaseStreamOperations.Person("Jane", 45),
                    new ComparisonBaseStreamOperations.Person("Jeff", 70));

    List<ComparisonBaseStreamOperations.Person> sortedList =
            largePersonList.stream()
                    .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
                    .collect(Collectors.toList());

    sortedList.stream().forEach(person -> System.out.println(person));

Output:  
Person{name='Chris', age=37}  
Person{name='Erin', age=50}  
Person{name='Helen', age=60}  
Person{name='Jane', age=45}  
Person{name='Jeff', age=70}  
Person{name='Jenny', age=20}  
Person{name='John', age=29}  
Person{name='Mark', age=35}  
Person{name='Paige', age=31}  
Person{name='Tom', age=30}  
Person{name='Zach', age=10}  

### min
This example will return the youngest person. We can use Comparator.comparing for the lambda expression below to specify which class property to use.

    Person youngestPerson =
        largePersonList.stream()
            .min(Comparator.comparing(Person::getAge))
            .orElse(null);

    System.out.println(youngestPerson);

Output:   
Person{name='Zach', age=10}  

**Note:** Some stream operations, such as min, max, findFirst, return an Optional object. This will be covered in detail in another module, but for now Optionals are used in the case where none of the stream elements satisfy the predicate.  This is where the "orElse" clause comes in the previous example.  If no, result is found, it will return null.  However, we did find a result in our example.

### max
This example will return the oldest person. We can use 

    Person oldestPerson =
            largePersonList.stream()
                    .max(Comparator.comparing(Person::getAge))
                    .orElse(null);

    System.out.println(oldestPerson);

Output:  
Person{name='Jeff', age=70}  

### distinct
The following example will take a stream of integers that contain duplicates and then return it without any duplicates.

    Stream<Integer> integerStream = Stream.of(1,1,2,2,3,4,5);

    integerStream
        .distinct()
        .forEach(integer -> System.out.println(integer));

Output:  
1  
2  
3  
4  
5  

### allMatch
This example will return true if all the persons are over the age of 9.

    List<ComparisonBaseStreamOperations.Person> largePersonList =
        Arrays.asList(
                new ComparisonBaseStreamOperations.Person("Tom", 30),
                new ComparisonBaseStreamOperations.Person("John", 29),
                new ComparisonBaseStreamOperations.Person("Jenny", 20),
                new ComparisonBaseStreamOperations.Person("Mark", 35),
                new ComparisonBaseStreamOperations.Person("Chris", 37),
                new ComparisonBaseStreamOperations.Person("Paige", 31),
                new ComparisonBaseStreamOperations.Person("Helen", 60),
                new ComparisonBaseStreamOperations.Person("Erin", 50),
                new ComparisonBaseStreamOperations.Person("Zach", 10),
                new ComparisonBaseStreamOperations.Person("Jane", 45),
                new ComparisonBaseStreamOperations.Person("Jeff", 70));

    Boolean isAllOverTheAgeOfNine =
            largePersonList.stream()
                .allMatch(person -> person.getAge() > 9);

    System.out.println(isAllOverTheAgeOfNine);

Output:  
true

### anyMatch
This example will return true if any person is over the age of 50.

    List<ComparisonBaseStreamOperations.Person> largePersonList =
        Arrays.asList(
                new ComparisonBaseStreamOperations.Person("Tom", 30),
                new ComparisonBaseStreamOperations.Person("John", 29),
                new ComparisonBaseStreamOperations.Person("Jenny", 20),
                new ComparisonBaseStreamOperations.Person("Mark", 35),
                new ComparisonBaseStreamOperations.Person("Chris", 37),
                new ComparisonBaseStreamOperations.Person("Paige", 31),
                new ComparisonBaseStreamOperations.Person("Helen", 60),
                new ComparisonBaseStreamOperations.Person("Erin", 50),
                new ComparisonBaseStreamOperations.Person("Zach", 10),
                new ComparisonBaseStreamOperations.Person("Jane", 45),
                new ComparisonBaseStreamOperations.Person("Jeff", 70));

    Boolean isAnyOverTheAgeOfFifty =
            largePersonList.stream()
                .anyMatch(person -> person.getAge() > 50);

    System.out.println(isAnyOverTheAgeOfFifty);

Output:  
true

### noneMatch
This example will return true if there is not a person over the age of 70.

    List<ComparisonBaseStreamOperations.Person> largePersonList =
        Arrays.asList(
                new ComparisonBaseStreamOperations.Person("Tom", 30),
                new ComparisonBaseStreamOperations.Person("John", 29),
                new ComparisonBaseStreamOperations.Person("Jenny", 20),
                new ComparisonBaseStreamOperations.Person("Mark", 35),
                new ComparisonBaseStreamOperations.Person("Chris", 37),
                new ComparisonBaseStreamOperations.Person("Paige", 31),
                new ComparisonBaseStreamOperations.Person("Helen", 60),
                new ComparisonBaseStreamOperations.Person("Erin", 50),
                new ComparisonBaseStreamOperations.Person("Zach", 10),
                new ComparisonBaseStreamOperations.Person("Jane", 45),
                new ComparisonBaseStreamOperations.Person("Jeff", 70));

    Boolean isNoneOverTheAgeOfSeventy =
            largePersonList.stream()
                .noneMatch(person -> person.getAge() > 70);

    System.out.println(isNoneOverTheAgeOfSeventy);

Output:  
true

## Introduction - More Collectors
In the previous module, we talked briefly about collectors.  In the section, we will look at other data structures that we can collect to. They are:  

- toSet - similar to toList but will create a Set
- toCollection - used to create implementations of the Collection interface 
- toMap - creates a Map
- joining - allows you to concatenate elements in a stream using a delimiter, prefix, and suffix
- partitioningBy - breaks a stream into 2 groups based on whether the elements satisfy certain criteria or not. 
- groupingBy - allows you to partition a stream into more than 2 groups
- mapping - similar to map, this will allow you to convert the result of a groupingBy into another data structure

## Demo - More Collectors
For this demo, we will take a look at an example of each of the collector types mentioned in the introduction.  We will use the following class in some of the examples.

    public static class Person {

        private String name;
        private int age;

        public Person(String name,
                      int age) {
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
            return "Person{" +
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

### toSet
This example will take a list that contains one duplicate and create a Set, which will discard the duplicates.

    Set<Person> personSet =
        duplicatePersonList.stream()
            .collect(Collectors.toSet());

    personSet.stream().forEach(person  -> System.out.println(person));

Output:  
Person{name='Tom', age=30}  
Person{name='Jenny', age=20}  

### toCollection
This example will take a list of persons and create a Vector of persons that are over the age of 50.

    List<CollectorsDemo.Person> largePersonList =
        Arrays.asList(
                new CollectorsDemo.Person("Tom", 30),
                new CollectorsDemo.Person("John", 29),
                new CollectorsDemo.Person("Jenny", 20),
                new CollectorsDemo.Person("Mark", 35),
                new CollectorsDemo.Person("Chris", 37),
                new CollectorsDemo.Person("Paige", 31),
                new CollectorsDemo.Person("Helen", 60),
                new CollectorsDemo.Person("Erin", 50),
                new CollectorsDemo.Person("Zach", 10),
                new CollectorsDemo.Person("Jane", 45),
                new CollectorsDemo.Person("Jeff", 70));

    Vector<Person> personVector =
        largePersonList.stream()
            .filter(person -> person.getAge() > 50)
            .collect(Collectors.toCollection(Vector::new));

    personVector.stream().forEach(person  -> System.out.println(person));

Output:  
Person{name='Helen', age=60}  
Person{name='Jeff', age=70}  

### toMap
This example will create a map where the key is the person's name and the value is the age.  
    
    Map<String, Integer> nameToAgeMap =
        largePersonList.stream()
            .collect(Collectors.toMap(person -> person.getName(), person -> person.getAge()));

    nameToAgeMap.forEach((key, value) -> System.out.println("Name is " + key + " and age is " + value));

Output:  
Name is Erin and age is 50  
Name is Paige and age is 31  
Name is Tom and age is 30  
Name is Zach and age is 10  
Name is Chris and age is 37  
Name is Jeff and age is 70  
Name is John and age is 29  
Name is Mark and age is 35  
Name is Jenny and age is 20  
Name is Jane and age is 45  
Name is Helen and age is 60  
Name is Tom and age is 30  
Name is Jenny and age is 20  

### Special Note about toMap and duplicate keys
If toMap encounters a duplicate key, then an exception will be thrown. ie.  

    List<CollectorsDemo.Person> duplicatePersonList =
        Arrays.asList(
                new CollectorsDemo.Person("Tom", 30),
                new CollectorsDemo.Person("Tom", 30),
                new CollectorsDemo.Person("Jenny", 20));

    Map<String, Integer> nameToAgeDuplicateMap =
        duplicatePersonList.stream()
                .collect(Collectors
                        .toMap(person -> person.getName(),
                                person -> person.getAge()));

Output:  
Exception in thread "main" java.lang.IllegalStateException: Duplicate key 30  

You can avoid this by passing a lambda expression as the 3rd argument to toMap which will tell it how to handle the duplicate. ie.

    Map<String, Integer> nameToAgeDuplicateMap =
            duplicatePersonList.stream()
                    .collect(Collectors
                            .toMap(person -> person.getName(),
                                    person -> person.getAge(),
                                    (first, second) -> second));

    nameToAgeDuplicateMap.forEach((key, value) -> System.out.println("Name is " + key + " and age is " + value));

Output:  
Name is Tom and age is 30  
Name is Jenny and age is 20  

If you look at the lambda expression that's provided to toMap:

    (first, second) -> second

This is essentially saying to take the last duplicate encountered.  If we specified "first", then it would take the first value and ignore all duplicates from that point on.

### joining
This example will take all of the person's names and concatenate them together using ", " as the delimiter.

    String names =
        largePersonList.stream()
            .map(person -> person.getName())
            .collect(Collectors.joining(", ", "", ""));

    System.out.println(names);

Output:  
Tom, John, Jenny, Mark, Chris, Paige, Helen, Erin, Zach, Jane, Jeff  

The first parameter to Collectors.joining is the delimiter. The 2nd is the prefix, and the 3rd is the suffix.

### partitioningBy
As mentioned before, partitioningBy will create 2 groups based on a criteria.  The result will be a Map with 2 keys, a Boolean true and a Boolean false.  The values for the Boolean true will be a List of stream elements that pass the criteria.  The values for Boolean false will be a List of stream elements that DO NOT pass the criteria.  The following example will take the large list of persons and partition them based on those persons that are over the age of 40 and those that are not.

    Map<Boolean, List<Person>> youngerAndOlderThanFortyMap =
        largePersonList.stream()
            .collect(Collectors.partitioningBy(person -> person.getAge() >= 40));

    //Let's iterate the person's older than 40.
    System.out.println("Persons older than 40");
    youngerAndOlderThanFortyMap.get(true).forEach(person -> System.out.println(person));

    System.out.println("Persons younger than 40");
    youngerAndOlderThanFortyMap.get(false).forEach(person -> System.out.println(person));

Output:  
Persons older than 40  
Person{name='Helen', age=60}  
Person{name='Erin', age=50}  
Person{name='Jane', age=45}  
Person{name='Jeff', age=70}  
Persons younger than 40  
Person{name='Tom', age=30}  
Person{name='John', age=29}  
Person{name='Jenny', age=20}  
Person{name='Mark', age=35}  
Person{name='Chris', age=37}  
Person{name='Paige', age=31}  
Person{name='Zach', age=10}  

### groupingBy
GroupingBy is advanced partitioning that will allow you to create custom groupings.  The following example will group all the persons based of their first initial. The lambda expression passed to the groupingBy method tells the collector what to use for the map key.

    Map<Character, List<Person>> firstInitialMap =
            largePersonList.stream()
                .collect(Collectors.groupingBy(person -> new Character(person.getName().charAt(0))));

    //Check the number of elements in the map. It should be 8.
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

    System.out.println("Persons with initial C");
    firstInitialMap.get('C').forEach(person -> System.out.println(person));

Output:  
Number of distinct first initials in the map is 8  
Persons with initial T  
Person{name='Tom', age=30}  
Persons with initial J  
Person{name='John', age=29}  
Person{name='Jenny', age=20}  
Person{name='Jane', age=45}  
Person{name='Jeff', age=70}  
Persons with initial M  
Person{name='Mark', age=35}  
Persons with initial C  
Person{name='Chris', age=37}  
Persons with initial P  
Person{name='Paige', age=31}  
Persons with initial H  
Person{name='Helen', age=60}  
Persons with initial E  
Person{name='Erin', age=50}  
Persons with initial C  
Person{name='Chris', age=37}  

### mapping
Mapping is typically used with groupingBy to convert the resulting map values to some of the data structure.  The following example will create a map where the key is the person name and the value is a single element list that contains their age.  If we didn't use mapping here, the map value would have been a list of the whole Person object.

    Map<String, List<Integer>> anotherNameToAgeMap =
        largePersonList.stream()
            .collect(
                    Collectors.groupingBy(person -> new String(person.getName()),
                        Collectors.mapping(Person::getAge, Collectors.toList())
            ));

    anotherNameToAgeMap.forEach((key, value) -> System.out.println("Name is " + key + " and age is " + value.get(0)));

Output:  
Name is Erin and age is 50  
Name is Paige and age is 31  
Name is Tom and age is 30  
Name is Zach and age is 10  
Name is Chris and age is 37  
Name is Jeff and age is 70  
Name is John and age is 29  
Name is Mark and age is 35  
Name is Jenny and age is 20  
Name is Jane and age is 45  
Name is Helen and age is 60  


## Independent Practice
For the independent practice, we will take what we have learned in this module and put them to use. 

**Hint:**
You will need to use:
- filters
- min 
- max 
- toMap 
- groupingBy

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
                return "Person{" +
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

            //TODO: Find the person with the highest salary.

            //TODO: Find the person with the lowest salary.

            //TODO: Create a map where the key is age and the value if the name of the person. For any duplicate keys, use
            // the first entry and ignore any duplicates.

            //TODO: Create a map where the key is the gender and the value is a list of Persons.  Filter the results to only
            //include persons over the age of 30 and that have a salary greater than 20,000.
        }
    }


## Conclusion - Review Recap
To recap, we have learned a great deal about streams and how to manipulate them using pipelines of intermediate and terminal operations. After going through this module, the hope is that you now see how powerful streams and pipelines are.  They make the code more readable. The biggest benefit is that it saves you from having to write a lot of boiler plate code to convert data structures.

## References
[Stackify Streams Guide](https://stackify.com/streams-guide-java-8/)