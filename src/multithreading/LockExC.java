package multithreading;

// class level lock. When a thread wants to execute a static synchronized method
public class LockExC implements Runnable {
    @Override
    public void run() {
        Lock();
    }

    public void Lock() {
        System.out.println(Thread.currentThread().getName());
        synchronized (LockExC.class) {
            System.out.println("in block "
                + Thread.currentThread().getName());
            System.out.println("in block "
                + Thread.currentThread().getName() + " end");
        }
    }

    public static void main(String[] args) {
        LockExC lec = new LockExC();
        Thread t1 = new Thread(lec);
        Thread t2 = new Thread(lec);

        LockExC lec1 = new LockExC();
        Thread t3 = new Thread(lec1);

        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
