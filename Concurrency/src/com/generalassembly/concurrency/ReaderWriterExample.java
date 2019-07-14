package com.generalassembly.concurrency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReaderWriterExample {
    private final Object MUTEX = new Object();
    private volatile int index = 0;
    String[] files = {
            "Concurrency/resources/flatland.txt",
            "Concurrency/resources/war-and-peace.txt",
            "Concurrency/resources/sherlock-holmes.txt",
    };

    String[] values = new String[3]; // allocate 3 slots, but for now, leave them null
    public void launch() {
        System.out.println(new File(".").getAbsolutePath());
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                for(index = 0; index < files.length; index++) {
                    try {
                        byte[] strings = Files.readAllBytes(Paths.get(files[index]));
                        String string = new String(strings);
                        values[index] = string;
                        synchronized (MUTEX) {
                            MUTEX.notify();
                        }
                        Thread.sleep(2000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.exit(0);
            }
        });
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        synchronized (MUTEX) {
                            MUTEX.wait();
                            System.out.println(values[index]);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        writer.start();
        reader.start();
    }

    public static void main(String[] args) {
        new ReaderWriterExample().launch();
    }
}
