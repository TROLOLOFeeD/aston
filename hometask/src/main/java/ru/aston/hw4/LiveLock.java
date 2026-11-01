package ru.aston.hw4;

public class LiveLock {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (resourceA) {
                    System.out.println("T1 захватил A, пытается захватить B...");
                    try { Thread.sleep(10); } catch (InterruptedException e) {}
                    
                    System.out.println("T1: T2 мешает");
                }
                Thread.yield();
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (resourceB) {
                    System.out.println("T2 захватил B, пытается захватить A...");
                    try { Thread.sleep(10); } catch (InterruptedException e) {}
                    
                    System.out.println("T2: T1 мешает");
                }
                Thread.yield(); 
            }
        });

        t1.start();
        t2.start();
    }
}