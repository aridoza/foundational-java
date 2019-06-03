package com.company;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LabTest {
    private Lab lab;

    @Before
    public void setUp() throws Exception {
        lab = new Lab();
    }

    /*
        Write a function that takes in a number and returns true if the number is even or false if the number is odd.
        You may use control flow and multiple conditionals.
     */
    @Test
    public void testMod(){
        boolean result = 100 % 2 == 0 ? true : false;
        assertEquals(result, lab.mod(100));
        //TODO: add false assertion
    }

    /*
        Write a function that takes in two words and compares them. If the spelling is the same,
        the functions returns true, otherwise false.
     */

    @Test
    public void testCompareStrings(){
        boolean result = false;
        String[] names;
        names = new String[10];

        names[0] = "Syed";
        names[1] = "Syed";
        names[2] = "Salahuddin";

        result = names[0].equalsIgnoreCase(names[1]) ? true : false;
        assertEquals(result, lab.isEqual("Syed", "Syed"));

        result = names[0].equalsIgnoreCase(names[2]) ? true : false;
        assertEquals(result, lab.isEqual("Syed", "Salahuddin"));
    }

    /*
        Write a function that takes in a word and prints out its every letter on a new line before it sees the letter "w".
        If it encounters "w" the function should stop executing.
     */

    @Test
    public void testCheckForLetter(){

    }

    /*
        Write a function that generates a random number. Print it out. If the number is greater than 50, return it,
        otherwise return -1. You may use control flow and multiple conditionals.
     */

    @Test
    public void testCheckForNumber(){

    }

    /*
        Write a function that takes in 3 integers: day, month, and year and computes day, month, and year of the next day.
        The function should return a string (e.g. day:12 month:11 year:1988)
     */

    @Test
    public void testDate(){

    }

    /*
        Write a function that takes in a word, omits every other letter in that word and returns a new word.
        You can use control flow and multiple conditionals.
     */
    @Test
    public void testLetterReplace(){

    }

    /*
        Write a function that takes in a number greater than 5 and prints out all the numbers from 1 to that
        number except number 4.
     */
    @Test
    public void testNumber(){

    }

    /*
        Write a function that takes in 2 numbers, and checks if they are the same.
        If they are not the same, it adds 1 to every number and returns its sum.
        If the numbers are the same, it adds 2 to every number and returns its sum.

     */

    @Test
    public void testSameSum(){

    }

    /*
        Write a function that asks the user to enter a country domain. If the user types in "us", the function
        prints out "United States", if the user enters "de", the function prints out "Germany", if the user types in "hu"
        the response should be "Hungary". In all other cases the function should print out "Unknown".
        Please try to use a switch statement and make sure the function works with a
        user's input of lower and upper case strings.
     */

    @Test
    public void testStringInput(){

    }

}
