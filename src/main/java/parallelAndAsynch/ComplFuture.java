package parallelAndAsynch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

// Famous or popular functional interfaces
//                                   Stream
// Supplier<T> T get()            - factories
// Predicate<T> boolean test(T)   - filter
// Function<T, R> R apply(T)      - map
// Consumer<T> void accept(T)     - forEach

// Stream                         CompletableFuture
// pipeline                         pipeline
// zero, one, or more data          zero or one
// only data channel                data channel and error channel
// forEach                          thenAccept
// map                              thenApply
// exceptions - oops                error channel
// (((zip)))                        thenCombine
// flatmap                          thenCompose
// function returns data - map             function returns data - thenAccept/ thenApply
// function returns collection - flatmap   function returns CompletableFuture - thenCompose

// map one-to-one Stream<T> ==> Stream<Y>
// map one-to-many Stream<T> ==> Stream<List<Y>>
// flatMap one-to-many Stream<T> ==> Stream<Y> ???

public class ComplFuture {
    public static int compute() {
//        System.out.println("compute: " + Thread.currentThread());
//        sleep(1000);
        return 2;
    }

    public static CompletableFuture<Integer> create() {
//        return CompletableFuture.supplyAsync(() -> 2); // returns 2

//        return CompletableFuture.supplyAsync(() -> compute());
        // specify the pool explicitly
        ForkJoinPool pool = new ForkJoinPool(10);
        return CompletableFuture.supplyAsync(() -> compute(), pool); // returns 2
    }

    public static void printIt(int value) {
        System.out.println(value + "--" + Thread.currentThread());
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = new CompletableFuture<Integer>();

        future
                .thenApply(data -> data * 2)
                .thenApply(data -> data + 1)
                .thenAccept(data -> System.out.println(data));
        System.out.println("Built the pipeline");

        System.out.println("prepared to print");

        sleep(1000);
        future.complete(2); // pass the data. Pushing the data to the pipeline
        sleep(1000);
//        create()
//                .thenApply(data -> data * 10) // perform a mapping operation, receive the data and multiply it by 10
//                .thenAccept(data -> System.out.println(data))
//                .thenRun(() -> System.out.println("That went well")); // make sure that the previous operation was successful
//        System.out.println("In main " + Thread.currentThread());
//
//        CompletableFuture<Integer> future = create();
//        sleep(1000); // by the time we get out of sleep, the completable future is already completed
//        // we aren't blocking on it and waiting on it, we continue with that and keep processing it depending on the
//        // state of the completable future at the particular time
//        // the execution may run in a completely different thread
//        future.thenAccept(data -> printIt(data)); // the execution is in the main thread because the data is available here already
//        System.out.println("here");
//        sleep(1000);

//        // CompletableFutures never die, they keep giving, they are immortal
//        CompletableFuture<Integer> future = create();
//        System.out.println("got it");
//
//        System.out.println(future.getNow(0)); // if the completable future has resolved, get me the result of it, give 0 otherwise
//
////        System.out.println(create().get()); // bad idea because get() is a blocking call. The best thing to do with get() is to forget
//        System.out.println("here");
//
//
//        CompletableFuture<Void> future2 = future.thenAccept(data -> System.out.println(data)); // Void because accept is not returning any data
//
//        create()
//                .thenAccept(data -> System.out.println(data))
//                // keeps on going
//                .thenRun(() -> System.out.println("This never dies"))
//                .thenRun(() -> System.out.println("Really, this never dies"));

    }

    private static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
