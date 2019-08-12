---
Title: Lambdas and Streams
Type: Lab
Duration: "1:30"
Creator:
  Name: Victor Grazi
  City: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Lambdas and Streams

## Introduction

This lab provides an opportunity to practice filtering, mapping, and statistics. It can be a pair-programming activity or done independently.

The skeleton of the lab can be found in the attached lab directory. The solution can be found in the attached solution directory.

------

## Exercise

### Requirements

Write code that uses lambdas and streams to satisfy the requirements listed below.

1. First, complete the following method to print out all the elements in the supplied list:

    ```
    private <T> void printList(List<T> list) {

    }
    ```

2. Next, print a list of all employees that earn 50K or more. 

    ```
    @Test
    public void getEmployeesOver50k() {
        List<Employee> employees = null;
        printList(employees);
    }
    ```

3. Next, print a list of the names (not the `Employee` instances) of all employees that were hired on or after Jan. 1, 2012.

    ```
    @Test
    public void getEmployeeNamesHiredAfter2012() {
        List<Employee> employees = null;
        printList(employees);
    }
    ```

4. Now, print the maximum salary of all employees ...

    ```
    @Test
    public void getMaxSalary() {
        double max = 0;
        System.out.println("Max: " + max);
    }
    ```

5. ... and the minimum salary.
    
    ```
    @Test
    public void getMinSalary() {
        double min = this.employees.stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);
        System.out.println("Min:" + min);
    }
    ```

6. Now, print the average salary of all employees.

    ```
    @Test
    public void getAverageSalaries() {
        double averageMale = 0;
        double averageFemale = 0;

        System.out.println("Averages: Male: " + averageMale + " Female: " + averageFemale);
     }
     ```

7. Finally, use the `reduce` operation to find the `Employee` instance of the employee with the highest salary.

    ```
    @Test
    public void getMaximumPaidEmployee() {
        Employee highest = null;
        System.out.println(highest);
    }
    ```

### Starter and Solution Code

See the accompanying lab directory, which contains the required classes and testers for each exercise.

See the accompanying solution directory for the solutions.

### Deliverable

We'll take about 20 minutes at the end of the class to review solutions.

----

## Additional Resources

- [Oracle Java Docs: Defining Methods](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- [Oracle Java Docs: A Closer Look at the "Hello World!" Application](https://docs.oracle.com/javase/tutorial/getStarted/application/)
- [Princeton: Java Programming Cheat Sheet](https://introcs.cs.princeton.edu/java/11cheatsheet/)
- [Java Modifier Types](http://www.tutorialspoint.com/java/java_modifier_types.htm)
- [Princeton: Recursion](http://introcs.cs.princeton.edu/java/23recursion/)
