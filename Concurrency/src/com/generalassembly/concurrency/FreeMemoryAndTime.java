package com.generalassembly.concurrency;

import java.time.LocalTime;

public class FreeMemoryAndTime {
    public static void main(String[] args) {
        spinThread(new MemoryPrinter(), 6_000);
        spinThread(new TimePrinter(), 1_500);
    }

    interface Printer {
        void printMessage();
    }
    static class TimePrinter implements Printer {

        @Override
        public void printMessage() {
            System.out.println("Current time: " + LocalTime.now());
        }
    }
    static class MemoryPrinter implements Printer {

        @Override
        public void printMessage() {
            System.out.println("Free memory: " + Runtime.getRuntime().freeMemory() + " Total memory: " + Runtime.getRuntime().totalMemory());
        }
    }
    private static void spinThread(Printer printer, long delay) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    printer.printMessage();
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        thread.start();
    }
}
