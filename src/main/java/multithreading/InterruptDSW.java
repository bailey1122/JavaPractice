package multithreading;

// interrupt(). Whenever any thread interrupt currently executing thread it'll comes out from the sleeping
//// state but it won't stop working
class IThread extends  Thread {
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Child Thread executing");

                // sleep. Another threads get chance to execute
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted exception occur");
        }
    }
}

public class InterruptDSW {
    public static void main(String[] args) throws InterruptedException {
        IThread t = new IThread();
        t.start();

        // main thread calls interrupt() method on child thread
        t.interrupt();

        System.out.println("Main thread execution completes");
    }
}
