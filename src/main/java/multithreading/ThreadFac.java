package multithreading;

import java.util.concurrent.ThreadFactory;

public class ThreadFac {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setPriority(Thread.MAX_PRIORITY);
//                thread.setPriority(10);
                return thread;
            }
        };
        threadFactory.newThread(new MyRunTh()).start();
    }

    static class MyRunTh extends Thread {
        @Override
        public void run() {
            System.out.println(1);
        }
    }
}
