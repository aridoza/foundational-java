package com.generalassembly.intro;

import java.io.*;
import java.net.MalformedURLException;
import java.net.SocketException;

public class ExceptionHandling {
    private String name;
    private void launch() {
        try {
            System.out.println("Name length=" + name.length());
        }
        catch (NullPointerException e) {
            System.out.println("You need to assign a value to the name variable before you can reference its length!!!");
        }
    }
    private void launchAgain() {
        if(name == null) {
            System.out.println("You need to assign a value to the name variable before you can reference its length!!!");
        }
        else {
            System.out.println("Name length=" + name.length());
        }
    }

    /**
     * Reads the specified file and prints the output
     * @throws IOException if the file is not found, or cannot be read
     */
    public void readFromFileSystem(String fileName) throws IOException {
        // code to read the file
        File file = new File(fileName);
        FileInputStream in = new FileInputStream(file);
        int character = 0;
        while(character != -1) {
            character = in.read();
            System.out.print((char)character);
        }
        System.out.println();
    }
    public void test() {
        try {
            if(true) {
                throw new SocketException();
            }else{
                throw new MalformedURLException();
            }
        } catch (
                MalformedURLException | SocketException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        new ExceptionHandling().readFromFileSystem("java-basics-lesson/resources/test.txt");
    }
}
