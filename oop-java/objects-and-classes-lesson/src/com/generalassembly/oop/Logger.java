package com.generalassembly.oop;

public class Logger {
    private static Logger logger = new Logger();

    private Logger() {
    }

    public static Logger getInstance() {
        return logger;
    }

    public void info(String message) {
        System.out.println(message);
    }
}
