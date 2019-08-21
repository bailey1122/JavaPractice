package multithreading;

import java.util.concurrent.Semaphore;

public class SemaphThr {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        Person person = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        Person person4 = new Person();
        Person person5 = new Person();
        Person person6 = new Person();
        Person person7 = new Person();

        person.semaphore = semaphore;
        person2.semaphore = semaphore;
        person3.semaphore = semaphore;
        person4.semaphore = semaphore;
        person5.semaphore = semaphore;
        person6.semaphore = semaphore;
        person7.semaphore = semaphore;

        person.start();
        person2.start();
        person3.start();
        person4.start();
        person5.start();
        person6.start();
        person7.start();

    }
}

class Person extends Thread {
    Semaphore semaphore;

    @Override
    public void run() {
        System.out.println(this.getName() + " waiting for smth");
        try {
            semaphore.acquire();
            System.out.println(this.getName() + " do smth");
            Thread.sleep(1000);
            System.out.println(this.getName() + " release");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
