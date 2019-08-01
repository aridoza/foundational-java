package com.ga.streams;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    static AtomicInteger IDGenerator = new AtomicInteger(0);
    private final int id = IDGenerator.incrementAndGet();
    private final String name;
    private final LocalDate hireDate;
    private final Double salary;
    private final String gender;

    public Employee(String name, LocalDate hireDate, Double salary, String gender) {
        this.name = name;
        this.hireDate = hireDate;
        this.salary = salary;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", gender=" + gender +
                '}';
    }
}
