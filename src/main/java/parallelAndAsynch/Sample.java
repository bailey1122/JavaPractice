package parallelAndAsynch;

import java.util.concurrent.CompletableFuture;

// there are 3 stated for a CompletableFuture:
// Pending
// Resolved (final) final means once you resolve, you can never change it
// Rejected (final)

public class Sample {
    public static int compute() {
        throw new RuntimeException("something went wrong");
//        return 2;
    }

    public static Void handleException(Throwable throwable) {
        System.out.println(throwable);
        throw new RuntimeException("exception");
    }

    public static int handleException2(Throwable throwable) {
        System.out.println(throwable);
//        return -1; // recover from the error
        return 0;
    }

    public static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(() -> compute());
    }

    public static CompletableFuture<Integer> create2(int val) {
        return CompletableFuture.supplyAsync(() -> val);
    }

    public static CompletableFuture<Integer> inc(int val) {
        return CompletableFuture.supplyAsync(() -> val + 1);
    }


    public static void main(String[] args) {
        create2(2)
//                .thenApply(e -> inc(e)) // do not use it because it returns a completable future
                .thenCompose(e -> inc(e))
                .thenAccept(e -> System.out.println(e));

        create2(2).thenCombine(create2(3), (result1, result2) -> result1 + result2) // 5
            .thenAccept(data -> System.out.println(data));
        // if we don't blow up, it means we've recovered from the exception. Otherwise, continue on the error path because it's
        // beyond repair. If an exception is not returned, but a normal value is, then switch back to the data path and continue on the
        // data chain

        // ---- f ---- f ---- f                        f ---- f ---- thens
        //                     \                     /
        //                      ---- f ---- f ---- f                 exceptionals
        //                              blowup
        CompletableFuture<Void> future = create()
                // this pipeline is never evaluated
                // when something is good, finds the nearest 'then', chooses 'exceptionally' otherwise // recover from the error and go
                .thenApply(data -> data * 2) // is not executed
                .exceptionally(e -> handleException2(e)) // goes to this block
                .thenApply(data -> data + 1)
                .thenAccept(data -> System.out.println(data)); // the nearest then here. Prints 1
//                .exceptionally(e -> handleException(e));


        sleep(1000);
        future.completeExceptionally(new RuntimeException("completeExceptionally"));
        sleep(1000);
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
