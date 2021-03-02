package multithreading;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// ForkJoinTask is an abstract class which implements Future and is capable of running a large number of tasks hosted by
// a small number of actual threads in ForkJoinPool. ForkJoinTask spawns new subtasks as part of the work required to complete
// its main task. It generates new tasks by calling fork() and it gathers all results with join(), thus the name of the class.
// There are two abstract classes that implement ForkJoinTask: RecursiveTask which returns a value upon completion, and
// RecursiveAction which doesn't return anything

// calculate the sum squares for all its factorial elements. So, for instance, if we pass the number 4 to our calculator,
// we should get the result from the sum of 4² + 3² + 2² + 1² which is 30
public class FactorialSquareCalculator extends RecursiveTask<Integer> {

    private Integer n;

    public FactorialSquareCalculator(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }

        FactorialSquareCalculator calculator = new FactorialSquareCalculator(n - 1);

        calculator.fork(); // we ask ForkJoinPool to initiate the execution of this subtask

        return n * n + calculator.join(); // calculator.join() returns the result from the calculation
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(); // to handle the execution and thread management

        FactorialSquareCalculator calculator = new FactorialSquareCalculator(10);

        forkJoinPool.execute(calculator);
    }
}
