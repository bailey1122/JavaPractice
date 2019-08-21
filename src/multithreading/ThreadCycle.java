package multithreading;

public class ThreadCycle {
    public static void main(String[] args) {
        ThreadC threadC = new ThreadC();
        threadC.start();
//        threadC.join();
//        Thread.yield(); // causes the currently executing thread to sleep
        try {
            Thread.sleep(3000); // sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread main");
    }
}

class ThreadC extends Thread {
    @Override
    public void run() {
        System.out.println("thread 0");
    }
}
