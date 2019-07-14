package com.generalassembly.concurrency;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Holding class contains the ticker, shares, and prices of a stock in a portfolio
 */
class Holding {
    private String ticker;

    public int getShares() {
        return shares;
    }

    private int shares;

    public void setShares(int shares) {
        this.shares = shares;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public Holding(String ticker, int shares, double price) {
        this.ticker = ticker;
        this.shares = shares;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

public class ReadWriteLockLesson {
    private final List portfolio = new ArrayList();
    ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        new ReadWriteLockLesson().launch();
    }

    // create our read write lock
//    ReadWriteLock readWriteLock = new ReentrantReadWriteLock(); // uncomment

    private void launch() {
        createPortfolio();
        for (int i = 0; i < 10; i++) {
            // create 10 reader threads, that continue without pause
            createReaderThread();
        }
        createWriterThread();
    }

    private void createReaderThread() {
        executor.execute(() -> read());
    }

    private Map<Double, Integer> values = new ConcurrentHashMap<>();

    private void read() {
        while (true) {
//            readWriteLock.readLock().lock(); // uncomment
            double value = 0;
            for (int i = 0; i < portfolio.size(); i++) {
                Holding holding = (Holding) portfolio.get(i);
                double holdingValue = holding.getShares() * holding.getPrice();
                value += holdingValue;
            }
            synchronized (ReadWriteLockLesson.class) {
                int size = values.size();
                values.put(value, 0);
                if (values.size() != size) {
                    // only save the value if it is changed. If the program works, there should only ever be one value
                    System.out.println(values);
                }
            }
//            readWriteLock.readLock().unlock(); // uncomment
        }
    }

    private void createWriterThread() {
        executor.execute(() -> write());
    }

    // for the fun, we will first sell FB and buy ABC, then we will buy FB and sell ABC
    volatile int plusMinus = 1;

    private void write() {
        while (true) {
//            readWriteLock.writeLock().lock(); // uncomment
            Holding fb = (Holding) portfolio.get(0);
            fb.setShares(fb.getShares() - plusMinus * 500);
            Holding abc = (Holding) portfolio.get(1);
            abc.setShares(abc.getShares() + plusMinus * 1000);
            plusMinus *= -1;
//            readWriteLock.writeLock().unlock(); // uncomment
            try {
                // sleep briefly to give readers a chance to read
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Create a one time portfolio
     */
    private void createPortfolio() {
        portfolio.add(new Holding("FB", 10_000, 176.00));
        portfolio.add(new Holding("ABC", 5_000, 88.00));
        portfolio.add(new Holding("MSFT", 700, 131.00));
    }
}
