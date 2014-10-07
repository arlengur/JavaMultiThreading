package ru.arlen.k_deadlock;

/**
 * Deadlock can occur in a situation when a thread is waiting for an object
 * lock, that is acquired by another thread and second thread is waiting for an
 * object lock that is acquired by first thread. Since, both threads are waiting
 * for each other to release the lock, the condition is called deadlock. If you
 * make sure that all locks are always taken in the same order by any thread,
 * deadlocks cannot occur.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final Runner runner = new Runner();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        runner.finished();
    }

}