package multithreading;

// sleep(). Causes the thread to stop executing for a given amount of time. It will be idle if there'is no
// other thread or process to be run
public class SleepEx implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                // sleep for 1000 milliseconds
                Thread.sleep(1000); // Doesn't have any effect if has lock or not
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new SleepEx());
        t.start();

        Thread t2 = new Thread(new SleepEx());
        t2.start();
    }
}
