package com.adi;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    rollTheBall();
//        rollTheBallWCase();
    }

    public static void rollTheBall() {
        countdown();
        int num = getRandomNumber();
        if( num == 0 ) {
            System.out.println("It is certain");
        }
        else if( num == 1 ) {
            System.out.println("Without a doubt");
        }
        else if( num == 2 ) {
            System.out.println("Outlook good");
        }
        else if( num == 3 ) {
            System.out.println("Reply hazy try again");
        }
        else if( num == 4 ) {
            System.out.println("Better not tell you now");
        }
        else if( num == 5 ) {
            System.out.println("Most likely");
        }
        else if( num == 6 ) {
            System.out.println("My reply is no");
        }
        else if( num == 7 ) {
            System.out.println("Outlook not so good");
        }
        else if( num == 8 ) {
            System.out.println("Very doubtful");
        }
        rollAgain();
    }

    public static void rollTheBallWCase() {
        countdown();
        int num = getRandomNumber();
        switch (num) {
            case 0: System.out.println("It is certain");
                break;
            case 1: System.out.println("Without a doubt");
                break;
            case 2: System.out.println("Outlook good");
                break;
            case 3: System.out.println("Reply hazy try again");
                break;
            case 4: System.out.println("Better not tell you now");
                break;
            case 5: System.out.println("Most likely");
                break;
            case 6: System.out.println("My reply is no");
                break;
            case 7: System.out.println("Outlook not so good");
                break;
            case 8: System.out.println("Very doubtful");
        }
        rollAgain();
    }

    public static int getRandomNumber() {
        return (int) (Math.random() * 8);
    }

    public static void rollAgain() {
        System.out.println("Would you like to play again? y or n?");
        String userInput = getUserInput();
        if(userInput.equals("y")) {
            rollTheBall();
        }
    }

    public static void countdown() {
        System.out.println("Focus on your question...");
        try {
            Thread.sleep(500);
            System.out.println("5...");
            Thread.sleep(500);
            System.out.println("4...");
            Thread.sleep(500);
            System.out.println("3...");
            Thread.sleep(500);
            System.out.println("2...");
            Thread.sleep(500);
            System.out.println("1...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String cleanedInput = scanner.nextLine().trim().toLowerCase();
        return cleanedInput;
    }
}

/* https://en.wikipedia.org/wiki/Magic_8-Ball */
