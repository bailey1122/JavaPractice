package reactiveprog;

import rx.Observable;

import java.util.List;

// consumer
public class ComputeFunction {
    public static void compute(Integer v) {
        try {
            System.out.println("compute integer v: " + v);
            // we are doing it to emulate some long running task that will cause Observable to fill up with items quicker
            Thread.sleep(1000); // that Observer can consume them
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void compute(List<Integer> integers) {
    }

    public static void compute(Observable<Integer> integerObservable) {
    }
}
