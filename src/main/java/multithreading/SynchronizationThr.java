package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationThr {
    public static void main(String[] args) throws Exception {
        Resource resource = new Resource();
        resource.setI(5);

        SynchThr synchThr = new SynchThr();
        synchThr.setName("one");
        synchThr.setResource(resource);
        SynchThr synchThr2 = new SynchThr();
        synchThr2.setResource(resource);

        synchThr.start();
        synchThr2.start();
        synchThr.join();
        synchThr2.join();
        System.out.println(resource.getI());
    }
}

class SynchThr extends Thread {
    Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.changeI();
    }
}

class Resource {
    private int i;
//    private int j;

//    Lock lock = new ReentrantLock();

    public int getI() {
        return i;
    }

    public synchronized void setI(int i) {
        this.i = i;
    }

    // you can use this with lock without 'synchronized'
    public synchronized void changeI() { // necessarily
//        lock.lock();
        int i = this.i;
        if (Thread.currentThread().getName().equals("one")) {
            Thread.yield();
        }
        i++;
        this.i = i;
    }

//    public synchronized void changeJ() { // necessarily
//        int j = this.j;
//        if (Thread.currentThread().getName().equals("one")) {
//            Thread.yield();
//        }
//        j++;
//        this.j = j;
//        lock.unlock();
//    }
}
