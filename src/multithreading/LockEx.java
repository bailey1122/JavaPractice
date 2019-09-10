package multithreading;

// object level lock. When a thread wants to execute synchronized method
public class LockEx implements Runnable {
    public void run() {
        Lock();
    }

    public void Lock() {
        System.out.println(Thread.currentThread().getName());
        synchronized (this) { // synchronized block
            System.out.println("in block "
                + Thread.currentThread().getName());
            System.out.println("in block " +
                    Thread.currentThread().getName() + " end");
        }
    }

    public static void main(String[] args) {
        LockEx le = new LockEx();
        Thread t1 = new Thread(le);
        Thread t2 = new Thread(le);

        LockEx le1 = new LockEx();
        Thread t3 = new Thread(le1);

        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
