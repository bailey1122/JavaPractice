package multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDL {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3); // how many time this is invoked
        new Work(countDownLatch); // a thread
        new Work(countDownLatch);
        new Work(countDownLatch);

        countDownLatch.await();
        System.out.println("all job done");
    }
}

class Work extends Thread {
    CountDownLatch countDownLatch;

    public Work(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("work done");
        countDownLatch.countDown();
    }
}