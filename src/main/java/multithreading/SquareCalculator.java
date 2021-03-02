package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculator {

//    // the process is not parallel. The second task starts once the first task is completed
//    private ExecutorService executor = Executors.newSingleThreadExecutor(); // It takes care of starting the task in a new thread
//    // and gives us back the valuable Future object

    // the process is multi-threaded. It uses 2 simultaneous threads
    private ExecutorService executor = Executors.newFixedThreadPool(2);

    // calculates the square of an Integer
    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> { // create an instance of Callable using a lamda expression. It returns a FutureTask object.
            Thread.sleep(1000); // The bit of code that actually performs the calculation is contained within
            return input * input; // the call() method, supplied as a lambda expression
        });
    }
}
