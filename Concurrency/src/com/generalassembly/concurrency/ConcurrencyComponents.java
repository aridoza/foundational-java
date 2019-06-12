package com.generalassembly.concurrency;

import java.util.concurrent.*;

public class ConcurrencyComponents {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        threadPool.execute(getRunnable(" This is thread 0"));
        threadPool.execute(getRunnable(" This is thread 1"));
        threadPool.execute(getRunnable(" This is thread 2"));
        threadPool.execute(getRunnable(" This is thread 3"));
        threadPool.execute(getRunnable(" This is thread 4"));
        threadPool.execute(getRunnable(" This is thread 5"));
        threadPool.execute(getRunnable(" This is thread 6"));
        threadPool.execute(getRunnable(" This is thread 7"));
        threadPool.execute(getRunnable(" This is thread 8"));
        threadPool.execute(getRunnable(" This is thread 9"));
        Future future = threadPool.submit(getRunnable("From submit"), "Done");
        try {
            String message = (String) future.get();
            System.out.println(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Runnable getRunnable(String message) {
        return new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i++) {
                    System.out.println(message + " iteration " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
    }

    private void checkFtpServer(long period) {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
        scheduledExecutor.scheduleAtFixedRate(checkAndProcessFile(), 0, period, TimeUnit.SECONDS);
    }

    private Runnable checkAndProcessFile() {
        return null;
    }

}
