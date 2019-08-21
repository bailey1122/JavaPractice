package multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyBarier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Run1()); // 3 means how many
        new Man(cyclicBarrier);
        new Man(cyclicBarrier);
        new Man(cyclicBarrier);
    }

    static class Run1 extends Thread {
        @Override
        public void run() {
            System.out.println("smth've just started");
        }
    }

    static class Man extends Thread {
        CyclicBarrier barrier;

        public Man(CyclicBarrier barrier) {
            this.barrier = barrier;
            start();
        }

        @Override
        public void run() {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
