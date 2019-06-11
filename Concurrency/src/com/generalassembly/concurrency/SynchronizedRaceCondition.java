package com.generalassembly.concurrency;

public class SynchronizedRaceCondition {
    private long someSharedVariable;
    private final Object MUTEX = new Object();
    private void launch() {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MUTEX) {
                    while (true) {
                        someSharedVariable = 0;
                        if (someSharedVariable != 0) {
                            System.out.println("huh? Expected " + 0 + " but got " + 0 + "!");
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MUTEX) {
                    while (true) {
                        someSharedVariable = -1;
                        if (someSharedVariable != -1) {
                            System.out.println("huh? Expected " + -1 + " but got " + -1 + "!");
                        }
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        new SynchronizedRaceCondition().launch();
    }
}
