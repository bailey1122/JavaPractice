package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new TryThread1().start();
        new TryThread2().start();
    }

    static class TryThread1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            System.out.println(getName() + " start working");
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " stop working");
            lock.unlock();
            System.out.println(getName() + " lock is released");
        }
    }

    static class TryThread2 extends Thread {
        @Override
        public void run() {
            System.out.println(getName() + " begin working");
            while (true) {
                if (lock.tryLock()) {
                    System.out.println(" working");
                    break;
                } else {
                    System.out.println(getName() + " waiting");
                }
            }
        }
    }
}
