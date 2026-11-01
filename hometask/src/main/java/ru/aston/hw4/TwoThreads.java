package ru.aston.hw4;

public class TwoThreads {
    private static final Object monitor = new Object();
    private static boolean turnOne = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    while (!turnOne) {
                        try { monitor.wait(); } catch (InterruptedException e) {}
                    }
                    System.out.print("1 ");
                    turnOne = false;
                    monitor.notify();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    while (turnOne) {
                        try { monitor.wait(); } catch (InterruptedException e) {}
                    }
                    System.out.print("2 ");
                    turnOne = true;
                    monitor.notify();
                }
            }
        });

        t1.start();
        t2.start();
    }
}