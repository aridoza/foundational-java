package com.generalassembly.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerLesson {
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final AtomicInteger hitCounter = new AtomicInteger(0);

    private void hit() {
        int value = hitCounter.incrementAndGet();
        System.out.println(value);
    }

    private void spinThread() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    AtomicIntegerLesson.this.hit();
                }
            }
        });
    }

    public static void main(String[] args) {
        AtomicIntegerLesson lesson = new AtomicIntegerLesson();
        lesson.launch(lesson);
    }

    private void launch(AtomicIntegerLesson atomicIntegerLesson) {
        for(int i = 0; i < 100; i++) {
            atomicIntegerLesson.spinThread();
        }
    }
}
