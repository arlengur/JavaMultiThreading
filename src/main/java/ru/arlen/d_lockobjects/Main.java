package ru.arlen.d_lockobjects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Synchronized Objects: ");
        WorkerObjectsSynchronized worker = new WorkerObjectsSynchronized();
        worker.main();
        System.out.println("Synchronized Methods: ");
        WorkerMethodsSynchronized worker2 = new WorkerMethodsSynchronized();
        worker2.main();
    }
}
