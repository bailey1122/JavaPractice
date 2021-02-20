package multithreading;

// interrupt(). After interrupting currently executing thread, we're throwing a new exception so it'll stop
// working
class ISThread extends Thread {
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("Hi");
        }
        catch (InterruptedException e) {
            throw new RuntimeException("Thread " +
                                      "interrupted");
        }
    }
}

public class InterruptSW {
    public static void main(String[] args) {
        ISThread t = new ISThread();
        t.start();
        try {
            t.interrupt();
        } catch (Exception e) {
            System.out.println("Exception handled");
        }
    }
}

