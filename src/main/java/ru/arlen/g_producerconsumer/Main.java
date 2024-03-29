package ru.arlen.g_producerconsumer;

/**
 * producer-consumer pattern in Java using the ArrayBlockingQueue Java class.
 * Producer-Consumer is the situation where one or more threads are producing
 * data items and adding them to a shared data store of some kind while one or
 * more other threads process those items, removing them from the data store.
 */
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    /**
     * Thread safe implementation of Queue data structure so you do not need to
     * worry about synchronization. More specifically BlockingQueue
     * implementations are thread-safe. All queuing methods are atomic in nature
     * and use internal locks or other forms of concurrency control. If
     * BlockingQueue is not used queue is shared data structure either
     * sychronized or wait() notify() should be used. Java 1.5
     * introduced a new concurrency library (in the java.util.concurrent
     * package) which was designed to provide a higher level abstraction over
     * the wait/notify mechanism.
     */
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private static void producer() throws InterruptedException {
        Random random = new Random();
        while (true) {//loop indefinitely
            queue.put(random.nextInt(100));//if queue is full (10) waits
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            //Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                Integer value = queue.take();//if queue is empty waits
                System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
            }
        }
    }
}