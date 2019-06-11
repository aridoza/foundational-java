package com.generalassembly.concurrency;

public class WaitNotifyExample {
    private String value;
    private volatile Object mutex = new Object();
    public void launch() {
        synchronized (this) {
            try {
                this.wait(100);
                System.out.println("Continuing");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Continuing");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mutex) {
                    try {
                        mutex.wait();

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

    }

    public static void main(String[] args) {
        new WaitNotifyExample().launch();
    }
}
