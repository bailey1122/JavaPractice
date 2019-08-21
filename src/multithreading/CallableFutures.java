package multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableFutures {
    public static void main(String[] args) throws Exception{
        Callable<Integer> callable = new MyCallable(); // to return some result
        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int j = 0;
            for (int i = 0; i < 10; i++, j++) {
                Thread.sleep(500);
            }
            return j;
        }
    }
}
