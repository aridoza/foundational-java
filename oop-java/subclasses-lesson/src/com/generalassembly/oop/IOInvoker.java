package com.generalassembly.oop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class IOInvoker {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream in = new FileInputStream(".");
    }
}
