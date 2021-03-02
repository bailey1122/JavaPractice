package multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// Future can be quite useful when working with asynchronous calls and concurrent processing. the Future class represents
// a future result of an asynchronous computation – a result that will eventually appear in the Future after the processing is complete.
// How to leverage the power of thread pools to trigger multiple parallel operations
public class FutureEx {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        SquareCalculator sc = new SquareCalculator();

        // tasks start and finish running simultaneously

        Future<Integer> f1 = sc.calculate(10);
        Future<Integer> f2 = sc.calculate(100);

        while (!(f1.isDone() && f2.isDone())) {
            System.out.println(String.format(
                    "f1 is %s and f2 is %s",
                    f1.isDone() ? "done" : "not done",
                    f2.isDone() ? "done" : "not done"
            ));
            Thread.sleep(300);
        }

        Integer res1 = f1.get();
        Integer res2 = f2.get();

        System.out.println(res1 + " and " + res2);


//        Future<Integer> future = new SquareCalculator().calculate(10);
//
//        while (!future.isDone()) {
//            System.out.println("Calculating...");
//            Thread.sleep(1000);
//        }
//
//        // blocks the execution until the task is complete. In this example future.get() will always return immediately since
//        Integer result = future.get(); // future.isDone() was called before (the task is finished)
////        Integer result2 = future.get(500, TimeUnit.MILLISECONDS); // throws TimeoutException if the task doesn't return before 500
//
//        System.out.println(result);
////        System.out.println(result2);
//
//
//        Future<Integer> future2 = new SquareCalculator().calculate(4);
//
//        // we do not care about the result anymore. It stops the operation and interrupts its underlying thread. It's possible
//        boolean canceled = future2.cancel(true); // that cancel fails. In that case, its returned value will be false
//
//        System.out.println(future2.get()); // throws CancellationException
//        System.out.println(canceled);
    }
}
