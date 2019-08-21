package multithreading;

import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinFramew {

    static long numOfOperations = 10_000_000_000L;

    static int numOfThreads = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        System.out.println(numOfThreads); // how many cores
        System.out.println(new Date());
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new MyFork(0, numOfOperations)));
//        long j = 0;
//        for (long i = 0; i < numOfOperations; i++) {
//            j += i;
//        }
//        System.out.println(j);
        System.out.println(new Date());
    }

    static class MyFork extends RecursiveTask<Long> {
        long from, to;

        public MyFork(long from, long to) {
            this.from = from;
            this.to = to;
        }
        @Override
        protected Long compute() {
            if ((to - from) <= numOfOperations/numOfThreads) {
        long j = 0;
        for (long i = from; i < to; i++) {
            j += i;
        }
        return j;
            } else {
                long middle = (to + from)/2; // divide by 2 parts
                MyFork firstHalf = new MyFork(from, middle); // from 0 to 5 000 000 000
                firstHalf.fork();
                MyFork secondHalf = new MyFork(middle + 1, to); // from 5... to 10 000 000 000
                long secondValue = secondHalf.compute();
                return firstHalf.join() + secondValue;
            }
        }
    }
}
