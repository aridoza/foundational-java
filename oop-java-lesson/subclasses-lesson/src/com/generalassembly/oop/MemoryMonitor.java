package com.generalassembly.oop;


import java.io.IOException;
import java.util.Date;

public class MemoryMonitor extends AbstractMonitor {

    @Override
    public String process() {
        return new Date() + ":" + Runtime.getRuntime().freeMemory();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new MemoryMonitor().startMonitoring("test.txt");
    }
}
