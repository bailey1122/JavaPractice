package multithreading;

// interrupt(). It sets the interrupted flag to true, which can be used by java programmer later.
// Generally, interrupt() gives the chance to another thread to execute corresponding run() of another thread.
// It results into high performance and reduces the waiting time of the thread
class INThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }
}

public class InterruptN {
    public static void main(String[] args) {
        INThread t = new INThread();
        t.start();

        t.interrupt();
    }
}
