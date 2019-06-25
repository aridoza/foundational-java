

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
| 5 min |     Demo     |                                      Comparison Based Streams Operations               
|  5 min |         Introduction         |      More Collectors     |
| 10 min |     Demo     |                                      Demo the different ways to use collectors                                       
| 5 min |     Introduction     |                                      Parallel Streams                                       |
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
- **Intermediate stream operations do not produce and end result. Stream processing does not activate until a terminal stream operation is specified.**
- **The order of your stream operations matter.  Not ordering them correctly could have performance impacts.**

## Demo - Intermediate Stream Operations  
In this demo will first take a look at some of the commonly used intermediate stream operations.  After that we will see that no processing happens until a terminal operation occurs. Lastly, we will look at what happens when you don't order your intermediate operations correctly.

### Commonly Used Intermediate Stream Operations

**Filter: selects elements based on a condition you provide **  
    
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

###Stream Processing Doesn't Start Until We Have A Terminal Stream Operation  
We mentioned earlier that processing doesn't start until a terminal stream operation is encountered.  See the example below:

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