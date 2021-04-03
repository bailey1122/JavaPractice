package jvsreactstreams;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// Java: imperative + object-oriented style. In OOP we encapsulate the moving parts; in FP we eliminate the moving parts
// Java (today): imperative + functional + object oriented style
// shared mutability is purely evil
// do not expose the database instead export the data


// reactive programming is dataflow computing. It's built based on functional composition and lazy evaluation and takes the
// abstraction further

// producer (emits data)
//     .filter(...)
//     .map(...)
//     ....
//     .subscribe(...)

// Java Streams                   Reactive Streams
//                 Pipeline
//                 Push Data
//                 Lazy
//           Zero, one, or more data
// Data only                     3 channels (Data, Error, Complete)
// Exceptions: good luck         Deal with exceptions downstream (circuit breaks)
//                               Error is like data
// Sequential vs. Parallel       Sync vs. Async
// single pipeline               multiple subscribers

// Reactive Streams               CompletableFuture/Promises
// zero, one, or more data        zero or one data
// 3 channels                     2 channels


// 4 interfaces
// Reactive Streams API                                             Java 9 Reactive Stream API
// Publisher emits data                                                   Publisher
// Subscriber receives the data                                           Subscriber
// Subscription is a session between an emitter and a subscriber          Subscription
// Processor = Publisher + subscribers                                    Processors
// io.reactivex.*                                                         java.util.concurrent.Flow.*
public class Ex {

    public static void process(int value) {
        System.out.println(value);
        sleep(1000);
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // double of even numbers
        List<Integer> doubled = new ArrayList<Integer>();

//        Flowable<Long> feed = Flowable.interval(1, 1, TimeUnit.SECONDS);
        Flowable<Long> feed = Flowable.interval(1, 1, TimeUnit.SECONDS).share();

        feed.subscribe(data -> process("S1: " + data));

        sleep(5000);

        feed.subscribe(data -> process("S2: " + data));

        sleep(10000);

//        Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
//        Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.DROP) // process if not busy
        Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.LATEST) // keep the latest data
                .observeOn(Schedulers.computation(), true, 2)
                .map(data -> data * 1)
//                .subscribe(System.out::println,
                    .subscribe(Ex::process,
                        err -> System.out.println(err),
                        () -> System.out.println("DONE"));
        sleep(10000);

        // gives a value every second
        Flowable.interval(1, 1, TimeUnit.SECONDS)
                .filter(e -> isEven2(e))
                .map(e -> e * 2)
                .subscribe(System.out::println);
        sleep(10000);

        // imperative == tell me what and how

        // imperative style. It's complex. It has accidental complexity
        for (int i = 0; i < numbers.size(); i++) { // external iteration
            if (numbers.get(i) % 2 == 0) {
                doubled.add(numbers.get(i) * 2);
            }
        }
        System.out.println(doubled);

        // declarative == tell me what (no how)

        // functional == declarative + higher-order functions + lazy evaluation. Immutability. Functional composition
        // stream is not a data structure. It's an abstraction of function. It isn't reusable

        // bucket     vs     pipeline
        // List/Set          Stream

        // functional style. Declarative & function: code reads like the problem statement. Single pass through. It has less
        // complexity and is easier to parallelize
        numbers.stream() // internal iteration (what to do smth rather than how to do smth)
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .collect(toList());

        numbers.stream()
                .filter(e -> isEven(e)) // lazy evaluation
                .map(e -> e * 2)
                .collect(toList());

        // the structure of sequential code is the same as the structure of concurrent code
        numbers.parallelStream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .collect(toList()); // if there is no such method then it does nothing

        Stream<Integer> stream = numbers.stream();

        stream.filter(e -> isEven(e))
                .map(e -> e * 2)
                .collect(toList());

//        stream.filter(e -> e > 6) // exception. Stream has already been operated upon or closed
//                .map(e -> e * 2)
//                .collect(toList());
    }

    private static void process(String msg) {
        System.out.println(msg);
    }

    private static void emit(FlowableEmitter<Integer> emitter) {
        int count = 0;

        while (count < 10) {
            count++;

            System.out.println("Emitting... " + count);
            emitter.onNext(count);
            sleep(500);
        }
        emitter.onComplete();
    }

    private static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    public static boolean isEven(int i) {
        System.out.println("call for " + i); // is not called if collect is not called
        return i % 2 == 0;
    }

    public static boolean isEven2(long i) {
        System.out.println("call for " + i); // is not called if collect is not called
        return i % 2 == 0;
    }
}
