package com.generalassembly.concurrency;

import java.util.concurrent.*;

public class ConcurrencyComponents {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(getRunnable(" This is job 0"));
        threadPool.execute(getRunnable(" This is job 1"));
        threadPool.execute(getRunnable(" This is job 2"));
        threadPool.execute(getRunnable(" This is job 3"));
//        Future future = threadPool.submit(getRunnable("From submit"), "Done");
//        try {
//            String message = (String) future.get();
//            System.out.println(message);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Returns a runnable that executes 5 times then exits
     * @param message
     * @return
     */
    private static Runnable getRunnable(String message) {
        return new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 3; i++) {
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
