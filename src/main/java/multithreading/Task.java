package multithreading;

import java.util.concurrent.Callable;

// creating and executing Executor
public class Task implements Callable<String> {
    private String message;

    public Task(String message) {
        this.message = message;
    }

    @Override
    public String call() throws Exception {
        return "Hello " + message;
    }
}
