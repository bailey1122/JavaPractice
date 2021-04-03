package parallelAndAsynch;

import java8.lambdasandother.Gender;
import java8.lambdasandother.Person;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.*;

// parallel and asynchronous programming

// Streams                                          Reactive Stream
// sequential vs parallel                           Synch vs async
// entire pipeline is sequential or parallel        Depends
// no segments                                      subscribeOn - no segments (provide a thread for it to run. The last one wins (ex. parallel and sequential))
//                                                  observeOn - segments (one part runs sequentially, another part on a different thread, one more part in a completely
//                                                  different thread)

// Computation intensive         vs        IO intensive
// # of Threads <= # of cores         # of Threads may be greater than # of cores
//                                                       # of cores
//                                    # of Threads <= --------------------
//                                         1 - blocking factor
//                                         0 <= blocking factor < 1== (deadlock)

// Promises have two channels. They carry zero or one piece of data, whereas a stream is for zero, one, or many pieces.
//      data ----->
//      error (treat error as another form of data. They are first class citizens) ----->
//
// f means function

//   --f---f------------------------f--------- data track
//         \(smth went wrong)     /(f can recover here)
//   -------f--------------------f---------- error track
public class Ex {
    public static void process(Stream<Integer> stream) throws Exception {
//        stream.forEach(e -> {}); // take the elements and discard the value. Terminal operation is called here

        ForkJoinPool pool = new ForkJoinPool(100);
        pool.submit(() -> stream.forEach(e -> {})); // terminal operation is running withing another pool

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }

    public static int transform(int number) {
        System.out.println("t: " + number + "--" + Thread.currentThread()); // take a peak at the thread of execution of the transform method
//        System.out.println("called for " + number);
        sleep(1000);
        return number * 1;
    }

    public static boolean check(int number) {
        System.out.println("t: " + number + "--" + Thread.currentThread());
        sleep(1000);
        return true;
    }

    public static void use(Stream<Integer> stream) {
        stream
//                .parallel() // rather than calling the parallel stream we can call this method
                .sequential()
                .map(e -> transform(e))
                .forEach(System.out::println);
    }

    public static void use2(Stream<Integer> stream) {
        stream
                .parallel() // no op because of sequential below
                .map(e -> transform(e))
                .sequential() // the last one wins. It affects a whole pipeline
                .forEach(System.out::println);
    }

    public static List<Person> createPeople() {
        return Arrays.asList(
                new Person("Sara", Gender.FEMALE, 20),
                new Person("Brenda", Gender.FEMALE, 20),
                new Person("Sara", Gender.FEMALE, 22),
                new Person("Bob", Gender.MALE, 20),
                new Person("Paula", Gender.FEMALE, 32),
                new Person("Paul", Gender.MALE, 32),
                new Person("Jack", Gender.MALE, 2),
                new Person("Jack", Gender.MALE, 72),
                new Person("Jill", Gender.FEMALE, 80)
        );
    }

    // imperative style the structure of sequential code is very different from the structure of concurrent code

    // using streams the structure of sequential code is identical to the structure of concurrent code

    public static void main(String[] args) throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(createPeople().parallelStream()
                .filter(p -> p.getAge() > 30)
                .filter(p -> p.getGender() == Gender.FEMALE)
                .map(Person::getFirstName)
//                .findFirst()
                .findAny()
                .orElse("there is no such a person")
        );

        // the pool executes code. It's not the pool in which the stream is created. It's the pool which your terminal operation is
        process(numbers.parallelStream() // executed from
        .map(e -> transform(e)));

        numbers.parallelStream()
                .map(e -> transform(e))
                .forEach(e -> {});

        System.out.println(Runtime.getRuntime().availableProcessors()); // the number of processors on the machine
        System.out.println(ForkJoinPool.commonPool()); // what a parallel stream uses by default

        // starts with the value, performs the operation with the first element, takes the result and passes over to the next stage
        //             e1      e2      e3
        //             |       |       |
        // init ---> op -->  op -->  op --> .... --> result
        numbers.stream()
                .reduce(0, (total, e) -> add(total, e));
        // reduce doesn't take an initial value, it takes an identity value
        // int + identity is 0   x + 0 = x
        // int * identity is 1   x * 1 = x

//        numbers.stream()
//                .map(e -> transform(e))
//                .forEach(e -> {}); // throw it away. Do not do anything
//
////        use(numbers.stream());
//        use(numbers.parallelStream()); // even though we ask the stream to run in parallel, it's going to be run sequentially
//
////        numbers.stream()
////                .map(e -> transform(e))
////                .forEach(System.out::println);
//
//        // make it parallel
//        numbers.parallelStream() // there's no guarantee on ordering
//                .map(e -> transform(e))
//                .forEach(System.out::println);
//
//        numbers.parallelStream()
//                .map(e -> transform(e))
//                // it doesn't convert the execution pipeline into sequential. This is concurrent execution. It doesn't run until
//                // the one before me is completed
//                .forEachOrdered(System.out::println); // impose ordering in the terminal operation but not sequential execution
//
//        numbers.parallelStream()
//                .map(e -> transform(e))
//                .forEachOrdered(e -> printIt(e));
//
//        numbers.parallelStream()
//                .filter(e -> check(e))
//                .forEachOrdered(e -> {});
    }

    private static int add(int a, int b) {
        int result = a + b;
        System.out.println("a= " + a + " b= " + b + " result= " + result + " -- " + Thread.currentThread());
        return result;
    }

    private static void printIt(int e) {
        System.out.println("t: " + e + "--" + Thread.currentThread());
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
