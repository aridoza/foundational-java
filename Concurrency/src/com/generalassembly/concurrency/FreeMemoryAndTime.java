package com.generalassembly.concurrency;

import java.time.LocalTime;

public class FreeMemoryAndTime {
    public static void main(String[] args) {
        spinThread("Free memory: " + Runtime.getRuntime().freeMemory() + " Total memory: " + Runtime.getRuntime().totalMemory(), 60_000);
        spinThread("Current time: " + LocalTime.now(), 15_000);
    }

    private static void spinThread(String message, long delay) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(message);
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
