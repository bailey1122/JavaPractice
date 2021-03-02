package multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndRunnable {

    // both interfaces represent a task that can be executed by multiple threads.

    // Runnable represents multi-threaded tasks. It can be run using Thread or ExecutorService. We can use it when we are
    // not looking for a result of the thread execution, for example, incoming event logging. The method signature does not
    // have the “throws” clause specified, there is no way to propagate checked exceptions

    // task can be launched using ExecutorService:
    public void executeTask() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // a future result of an asynchronous computation. In this case, the Future object will not hold any value.
        Future future = executorService.submit(new EventLoggingTask());

        executorService.shutdown();
    }

    // Callable is an improved version of Runnable. It can be run using ExecutorService. Callable's call() method contains
    // “throws Exception” clause so we can easily propagate checked exceptions further. The exceptions are collected in the
    // Future object, which can be checked by making a call to the Future.get() method. This will throw an
    // ExecutionException – which wraps the original exception. If we don't make the call to the get() method of Future
    // class – then the exception thrown by call() method will not be reported back, and the task will still be marked as completed
    public void calculate() throws ExecutionException, InterruptedException {
        CallableTask task = new CallableTask();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(task);
        System.out.println(future.get().intValue());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableAndRunnable c = new CallableAndRunnable();
        c.calculate();
    }
}
