package multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorEx2 {
    public static void main(String[] args) {
        Task task = new Task("World");

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> result = executorService.submit(task);

        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error occured while executing the submitted task");
            e.printStackTrace();
        }

        executorService.shutdown(); // terminate all the threads and return back
    }
}
