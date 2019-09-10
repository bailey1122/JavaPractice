package multithreading;

// yield(). It means that the thread is not doing anything particularly important and if any other
// threads or processes need to be run, they should run. Otherwise, the current thread will continue to run.
// Thread scheduler decides whether it get chance for execution after yield() again or not
class YThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " in control");
        }
    }
}

public class YieldEx {
    public static void main(String[] args) {
        YThread t = new YThread();
        t.start();
        for (int i = 0; i < 5; i++) {
            // control passes to child thread.
            // Doesn't have any affect if object has a lock. Thread doesn't lose any lock if
            // it has acquired a lock earlier.
            Thread.yield(); // Will enter in the Runnable state from Running state

            // after execution of child Thread main thread takes over
            System.out.println(Thread.currentThread().getName() + " in control");
        }
    }
}
