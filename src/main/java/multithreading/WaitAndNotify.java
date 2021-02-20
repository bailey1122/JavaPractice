package multithreading;

public class WaitAndNotify {
    public static void main(String[] args) throws InterruptedException {
        ThreadWN threadWN = new ThreadWN();
        threadWN.start();
        synchronized (threadWN) {
            threadWN.wait();
        }
        System.out.println(threadWN.total);
    }

    static class ThreadWN extends Thread {
        int total;

        @Override
        public void run() {
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    total += i;
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notify();
            }
        }
    }
}
