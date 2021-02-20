package multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
//    static int i;
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        for (int j = 0; j < 10000; j++) {
            new AtThread().start();
        }
        Thread.sleep(2000);
//        System.out.println(i);
        System.out.println(atomicInteger.get());
    }

    static class AtThread extends Thread {

        @Override
        public void run() {
//            i++;
            atomicInteger.incrementAndGet();
        }
    }
}
