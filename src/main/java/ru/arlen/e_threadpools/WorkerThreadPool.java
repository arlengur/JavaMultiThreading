package ru.arlen.e_threadpools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Worker implements Runnable {

    private Random random = new Random();
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    public List<Integer> list1 = new ArrayList<Integer>();
    public List<Integer> list2 = new ArrayList<Integer>();

    @Override
    public void run() {
        process();
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void stageOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }
}

public class WorkerThreadPool {

    public static void main(String[] args) {
        ExecutorService executer = Executors.newFixedThreadPool(2);//two threads, try setting by 1 to observe time
        System.out.println("Starting ...");
        long start = System.currentTimeMillis();
        Worker worker = new Worker();
        for (int i = 0; i < 2; i++) {//worker.run is called 2 (threads started) times by two threads
            executer.submit(worker);
        }
        executer.shutdown(); //prevents new tasks from being accepted by the ExecutorService
        try {
            executer.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + worker.list1.size() + "; List2: " + worker.list2.size());
    }

}