package multithreading;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// Runnable cannot return result when it terminates, i.e, when run() completes, so
// we use Callable. Run on another thread
class CallableEx implements Callable {
    // return a random number on 0-4 seconds
    public Object call() throws Exception {
        Random generator = new Random();

        Integer randomNumber = generator.nextInt(5);

        // delay the thread
        Thread.sleep(randomNumber * 1000);
        return randomNumber;
    }
}

// Future is used to store and obtain the result from a different thread
public class CallableFutureEx {
    public static void main(String[] args) throws Exception {
        // a concrete class. Implements both Runnable and Future
        FutureTask[] randomNumberTasks = new FutureTask[5];
        for (int i = 0; i < 5; i++) {
            Callable callable = new CallableEx();

            // FutureTask with callable
            randomNumberTasks[i] = new FutureTask(callable);

            // we can create Thread with FutureTask because it implements Runnable
            Thread t = new Thread(randomNumberTasks[i]);
            t.start();
        }

        for (int i = 0; i < 5; i++) {
            // as it implements Future, call get()
            System.out.println(randomNumberTasks[i].get());
            // this method blocks till the result is obtained.
            // The get method can throw checked exceptions like when it
            // is interrupted. Add the throws clause to main
        }
    }
}













