package multithreading;

// join(). Join the start of a thread's execution to end of other thread's execution such that
// a thread doesn't start running until another thread ends. If join() is called on a Thread instance,
// the currently running method will block until the Thread instance has finished executing
class JThread implements Runnable {
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("Current thread: " +
                t.getName());
        // checks if current thread is alive
        System.out.println("Is Alive? " +
                t.isAlive());
    }
}

public class JoinEx {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new JThread());
        t.start();

        // waits for 1000ms this thread to die
        t.join(1000);

        System.out.println("\nJoining after 1000" +
                                " mill seconds: \n");
        System.out.println("Current thread: " +
                                    t.getName());

        // checks if this thread is alive
        System.out.println("Is alive? " + t.isAlive());
    }
}
