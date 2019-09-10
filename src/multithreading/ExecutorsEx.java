package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

// keeping the threads alive and them reusing
public class ExecutorsEx {
    public static void main(String[] args) {
        // of there is more task they're stored on linkedBlockingQueue. A fixed number of threads
        ExecutorService es = Executors.newFixedThreadPool(4);

        // the pool creates and add a new thread to the executor if all the threads are busy,
        ExecutorService exC = Executors.newCachedThreadPool(); // and there're new tasks

        // use hen we have a task needs to be run ar regular intervals or if we wish delay a task
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        // irrespective of when the previous task ended
//        ses.scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);

        // after the currently task completes
//        ses.scheduleWithFixedDelay(Runnable command, long initialDelay, long period, TimeUnit unit);

        // access the result of the task submitted for execution to an executor
//        Future<String> result = es.submit(callableTask);
    }
}
