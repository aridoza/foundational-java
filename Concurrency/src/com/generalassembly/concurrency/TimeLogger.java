package com.generalassembly.concurrency;

import java.time.LocalTime;

public class TimeLogger extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(LocalTime.now());
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        TimeLogger timeLogger = new TimeLogger();
        timeLogger.start();
        System.out.println("Thread was started");
    }
}

// TODO: probably worth breaking these out as separate programs


//    public static void main(String[] args) {
//        class TimeRunnable implements Runnable {
//            @Override
//            public void run() {
//                try {
//                    while (true) {
//                        System.out.println(LocalTime.now());
//                        Thread.sleep(2000);
//                    }
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                    System.out.println(e);
//                }
//            }
//        }
//        TimeRunnable timeRunnable = new TimeRunnable();
//        Thread thread = new Thread(timeRunnable);
//        thread.start();
//        System.out.println("Thread was started");
//    }
//
//    public static void main3(String[] args) {
//        Runnable timeRunnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (true) {
//                        System.out.println(LocalTime.now());
//                        Thread.sleep(2000);
//                    }
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                    System.out.println(e);
//                }
//            }
//        };
//        Thread thread = new Thread(timeRunnable);
//        thread.start();
//        System.out.println("Thread was started");
//    }
//
//    public static void main4(String[] args) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (true) {
//                        System.out.println(LocalTime.now());
//                        Thread.sleep(2000);
//                    }
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                    System.out.println(e);
//                }
//            }
//        });
//        thread.start();
//        System.out.println("Thread was started");
//    }
//
//    public static void main5(String[] args) {
//        Thread thread = new Thread(() -> {
//            try {
//                while (true) {
//                    System.out.println(LocalTime.now());
//                    Thread.sleep(2000);
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.out.println(e);
//            }
//        });
//        thread.start();
//        System.out.println("Thread was started");
//    }
//
//
//}
