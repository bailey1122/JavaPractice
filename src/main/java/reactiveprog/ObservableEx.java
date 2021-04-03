package reactiveprog;

import rx.*;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// subscriber

// CAP it is not possible to has consistency, availability, and partition tolerance all together
// Observable is a data stream (data flow). Push data to the client when data is ready. May be synchronous and asynchronous.
// It generates data and it can send a signal end of data stream. Propagate error. It has the data channel and the error channel.
// The data channel gets closed the minute we have an error close signal
public class ObservableEx {

    // difference between Stream vs. Observable

    // Stream is a fancy iterator. It's lazy. It could push data to us but we don't know when the stream ends.
    // Java 8 doesn't have a good way to handle exceptions. Therefore, exceptions don't go well with streams. When we use a
    // parallel stream, we can have as many threads in the pool as the number of cores there are in the system. If your task
    // is computation intensive, then give no more threads than the number of cores

    // Observable is good for handling exceptions. It knows when the data stream is over. There is a complete message.
    // It has the data channel and the error channel. There is a complete message. It could compose with other
    // functions (Subscriber, multiple Subscribers).We can multiple views on it. Ability to support multiple subscribers.
    // High level abstraction. We can control the iteration and unsubscribing. If your task is IO intensive, then have no more
    // threads than the number of cores (the number of cores / (1 - blocking factor)). If the thread is blocked 50% of the time,
    // then (the number of cores / (1 - 0.5) = 2 * the number of cores). Blocking factor is between 0 and less than 1. We can
    // make it asynchronous

    private static String result = "", res ="", res1 = "", res2 = "", res3 = "", res4 = "";
    private static String[] titleList = {"A", "B", "C", "D"}, rr = {""}, rr1 = {""}, rr2 = {""};
    private static Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private static Integer subscriber1 = 0, subscriber2 = 0;

    public static void main(String[] args) throws InterruptedException {
        List<String> symbols = Arrays.asList("GOOG", "AAPL", "MSFT", "INTC");

        String[] letters = {"a", "b", "c", "d", "e", "f"};

        // backpressures

//        // cold Observable is lazy. Cold Observables do not need to have any form of a backpressure because they work in a pull fashion.
//        // Examples of items emitted by a cold Observable might include the results of a database query, file retrieval, or web request.
//        // It emits its sequence when its Observer finds it to be convenient. Items don't need to be buffered in an Observable
//        // because they are requested in a pull fashion. It will emit the same sequence of items no matter how frequently those items are observed
//        Observable.range(1, 1000000)
//                .observeOn(Schedulers.computation()) // run our Observer within a computation thread pool in RxJava
//                .subscribe(ComputeFunction::compute);

        // hot Observable begins generating items and emits them immediately at its own pace when they are created, and it's up to
        // its observers to keep up. When the Observer is not able to consume items as quickly as they are produced by an Observable,
        // they need to be buffered or handled in some other way, as they will fill up the memory, causing OutOfMemoryException
        PublishSubject<Integer> source = PublishSubject.<Integer>create();

//        source.observeOn(Schedulers.computation())
//                // compute method in the Observer takes some time to process every item, the Observable is starting to fill up a memory
//                .subscribe(ComputeFunction::compute, Throwable::printStackTrace); // with items, causing the program to fail. MissingBackpressureException
//        // because we didn't define a way of handling overproducing Observable. Examples of items emitted by a hot Observable might include mouse & keyboard events,
//        // system events, or stock prices
//        IntStream.range(1, 1000000).forEach(source::onNext);

        // buffering overproducing Observable. Define a buffer for elements that cannot be processed by an Observer
        // the buffer will store items that were not yet processed. It gives an Observer some time to catch up to an overproducing source.
        source.buffer(1024) // This may be only a temporary fix as the overflow can still happen if the source overproduces the predicted buffer size
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

        // batching emitted items (overproduced items) in windows of N elements. It can reduce a problem of overproducing Observable when Observer is
        // able to process a batch of elements quicker comparing to processing elements one by one.
        // When Observable is producing elements quicker than Observer can process them, we can alleviate this by grouping produced
        source.window(500) // elements together and sending a batch of elements to Observer that is able to process a collection of elements
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

        // skipping elements. It reduces the rate of value reception by the downstream Observer and thus they may still lead to MissingBackpressureException
        // periodically looks into the sequence of elements and emits the last item that was produces withing the duration specified
        source.sample(100, TimeUnit.MILLISECONDS) // as a parameter.
                // The duration is a time after which one specific element is picked from the sequence of produced elements
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

        // handling a filling (overflowing) Observable buffer. Drop the oldest element in a buffer and add newest item
        // produced by an Observable. It causes a discontinuity in the stream. It won't signal BufferOverflowException
        Observable.range(1, 1000000)
                .onBackpressureBuffer(16, () -> {}, BackpressureOverflow.ON_OVERFLOW_DROP_OLDEST) //  to prevent BufferOverflowException.
                .observeOn(Schedulers.computation())
                .subscribe(e -> {}, Throwable::printStackTrace);

        // dropping all overproduced elements. Whenever the downstream Observer is not ready to receive an element
        Observable.range(1, 1000000)
                .onBackpressureDrop() // eliminates a problem of overproducing Observable
                .observeOn(Schedulers.computation())
                .doOnNext(ComputeFunction::compute)
                .subscribe(v -> {}, Throwable::printStackTrace);

        Observable<Character> values = Observable.using( // allows us to associate resources such as a JDBC database connection,
                // a network connection, or open files to our observables
                () -> "MyResource",
                r -> {
                    return Observable.create(o -> {
                        for (Character c : r.toCharArray()) {
                            o.onNext(c);
                        }
                        o.onCompleted();
                    });
                },
                r -> System.out.println("Disposed: " + r)
        );
        values.subscribe(
            v -> rr2[0] += v,
            e -> rr2[0] += e
        );
        System.out.println(rr2[0]);

        // a Subject is simultaneously two elements, a subscriber and an observable. As a subscriber, a subject can be used
        // to publish the events coming from more than one observable. The events can from multiple subscribers can be reemitted
        // as its events to anyone observing them
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.subscribe(getFirstObserver());
        System.out.println("subscriber1 " + subscriber1);
        System.out.println("subscriber2 " + subscriber2);
        subject.onNext(1);
        System.out.println("subscriber1 " + subscriber1);
        System.out.println("subscriber2 " + subscriber2);
        subject.onNext(2);
        System.out.println("subscriber1 " + subscriber1);
        System.out.println("subscriber2 " + subscriber2);
        subject.onNext(3);
        System.out.println("subscriber1 " + subscriber1);
        System.out.println("subscriber2 " + subscriber2);
        subject.subscribe(getSecondObserver());
        System.out.println();
        System.out.println("getSecondObserver()");
        System.out.println("subscriber1 " + subscriber1);
        System.out.println("subscriber2 " + subscriber2);
        subject.onNext(4);
        System.out.println("subscriber1 " + subscriber1);
        System.out.println("subscriber2 " + subscriber2);
        subject.onCompleted();
        System.out.println("subscriber1 " + subscriber1);
        System.out.println("subscriber2 " + subscriber2);

        // emits one value or an error notification
        Single<String> single = Observable.just("smth")
                .toSingle()
                .doOnSuccess(i -> rr1[0] += i) // returns a Single that also calls a method we specify
                .doOnError(error -> { // returns a Single that immediately notifies subscribers of an error
                    throw new RuntimeException(error.getMessage());
                });
        single.subscribe(System.out::println);

        // resembles an ordinary Observable, except that it doesn't begin emitting items when it' subscribed to, but only when the
        // connect operator is applied to it. We can wait for all intended observers to subscribe to the Observable before the Observable
        // begins emits items
        ConnectableObservable<Long> connectable =
                Observable.interval(200, TimeUnit.MILLISECONDS).publish();

        connectable.subscribe(i -> rr[0] += i);
        System.out.println(rr[0]);

        connectable.connect();
        Thread.sleep(700);
        System.out.println(rr[0]);

        Observable.from(numbers)
                .takeWhile(i -> i < 5) // discards items emitted by an Observable after a specified condition becomes false
                .subscribe(i -> numbers[0] += i);
        System.out.println(numbers[0]);

        Observable.empty()
                .defaultIfEmpty("Observable is empty") // emits item from the source Observable, or a default item if the source Observable is empty
                .subscribe(i -> res4 += i);
        System.out.println(res4);

        Observable.from(numbers)
                .filter(i -> (i % 2 == 1)) // emits only those items from an observable that pass a predicate test
                .subscribe(i -> res3 += i);
        System.out.println(res3);

//        Observable.from(numbers)
//                .groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD") // allows us to classify the events in the input Observable into output categories
//                .subscribe(group ->
//                        group.subscribe((number) -> {
//                            if (group.getKey().toString().equals("EVEN")) {
//                                EVEN[0] += number;
//                            } else {
//                                ODD[0] += number;
//                            }
//                        })
//                );

        Observable.from(letters)
                // applies a function to each item emitted by an Observable sequentially and emits each successive value. It
                // allows us to carry forward state from event to event
                .scan(new StringBuilder(), StringBuilder::append)
                .subscribe(total -> res2 += total.toString());
        System.out.println(res2);

        // print for each string from a new Observable the list of titles based on what Subscriber sees
        Observable.just("book1", "book2")
                .flatMap(s -> getTitle())
                .subscribe(l -> res1 += l);
        System.out.println(res1);

        System.out.println();

        Observable.from(letters)
                .map(String::toUpperCase) // transforms items emitted by an Observable by applying a function to each item
                .subscribe(leter -> res += leter);
        System.out.println(res);

        Observable<String> obs = Observable.from(letters);
        obs.subscribe(
                i -> result += i, // onNext
                Throwable::printStackTrace, // onError
                () -> result += "_Completed" // onCompleted
        );
        System.out.println(result);

        Observable<Integer> o = Observable
                .just(1, 2, 3, 4, 5)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
                    }
                });
        o.subscribe(System.out::println);

        Action1<Integer> onNextAction = new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        };


        Iterator<String> iterator = symbols.iterator();
        while (iterator.hasNext()) {
            String data = iterator.next();
        }

//        Observable<Integer> feed2 = StockServer2.getData();
//        System.out.println("got observable");
//
//        // in cold observable every subscriber starts fresh (subscription)
//        Observable<Integer> feed3 = feed2.subscribeOn(Schedulers.io()); // fresh subscribers
//        System.out.println("got observable");


        // in hot observable every subscriber comes and joins
//        Observable<Integer> feed3 = StockServer2.getData()
//                .subscribeOn(Schedulers.io())
//                .share(); // share it across multiple subscribers.
//                System.out.println("got observable");


//        Observable<Integer> feed3 = StockServer2.getData()
//                .onBackpressureBuffer(); // buffer them while its busy processing. Process every one of them

        Observable<Integer> feed3 = StockServer2.getData()
                .onBackpressureDrop(); // doesn't care what happened in the past. It cares what happens now. If its busy and
        // the data is coming and there is no response, just give me fresh data
        System.out.println("got observable");

        // two subscribers (observers) are listening to the same observable
        feed3.subscribe(System.out::println);
        Thread.sleep(5000);

        // the second one
        feed3.subscribe(System.out::println);
        Thread.sleep(30000);



        Observable<StockInfo> feed = StockServer2.getFeed(symbols); // it's lazy so it's not doing any work until subscribing
        System.out.println("got observable");

        // keep taking stocks while their price is higher than 80
        feed.takeWhile(stockInfo -> stockInfo.value > 80) // it's in the middle. It sends uncompleted downstream and unsubscribed upstream
                .subscribe(System.out::println, ObservableEx::handleError, () -> System.out.println("DONE"));


        // keep taking stocks while their price is not higher than 80
        feed.skipWhile(stockInfo -> stockInfo.value > 80)
                .subscribe(System.out::println, ObservableEx::handleError, () -> System.out.println("DONE"));

        // skip the first 30
        feed.skip(30)
                .subscribe(System.out::println, ObservableEx::handleError, () -> System.out.println("DONE"));

        // take the first 10
        feed.take(10)
                .subscribe(System.out::println, ObservableEx::handleError);

//        feed.map(stockInfo -> new StockInfo(stockInfo.ticker, stockInfo.value * 0.9))
//                .filter(stockInfo -> stockInfo.value > 100)
//                .subscribe(System.out::println, ObservableEx::handleError);

//        // error handling, the second argument opens the error channel. We can take one Observable and attach another one to it
//        // and keep going that way
//        feed.onErrorResumeNext(throwable -> callABack(throwable, symbols)) // if there's an error, just resume the next service
//                .subscribe(System.out::println, ObservableEx::handleError);
        // we can have a pool of the objects and return from the pool as objects recover


//        feed.subscribeOn(Schedulers.io())
//                .subscribe(ObservableEx::printStockInfo);

        Thread.sleep(10000);

//        feed.subscribe(new Subscriber<StockInfo>() {
//            @Override
//            public void onCompleted() { // the complete channel
//                System.out.println("DONE");
//            }
//
//            @Override
//            public void onError(Throwable throwable) { // the error channel
//                System.out.println("The error channel: " + throwable);
//            }
//
//            @Override
//            public void onNext(StockInfo stockInfo) { // the data channel
//                System.out.println(stockInfo);
//                if (stockInfo.ticker.equals("GOOG") && stockInfo.value > 3) {
//                    System.out.println("Thanks, no more data");
//                    unsubscribe(); // we tell the service we don't want any more data. The service can release its resources
//                    // whenever the resources are committed to the subscriber, it can say that it's not sending any more data
//                }
//            }
//        });

//        feed.subscribe(System.out::println, // the data channel
//                System.out::println, // the error channel
//                () -> System.out.println("DONE")); // the complete channel

//        // subscribe, start pushing the data
//        feed.subscribe(System.out::println);

//        // subscribe, start pushing the data
//        feed.subscribe(new Action1<StockInfo>() {
//            @Override
//            public void call(StockInfo stockInfo) {
//                System.out.println(stockInfo); // we get 'processing' here because the request is processing
//            }
//        });
    }

    private static Observable<StockInfo> callABack(Throwable throwable, List<String> symbols) {
        System.out.println(throwable); // show the error
        return StockServer2.getFeed(symbols); // return a new backup service
    }

    private static void handleError(Throwable throwable) {
        System.out.println(throwable); // the error message comes from this line. The minute it happens, it closes the data channel
    }

    public static void printStockInfo(StockInfo stockInfo) {
        System.out.println("Thread: " + Thread.currentThread());
        System.out.println(stockInfo);
    }

    public static Observable<String> getTitle() {
        return Observable.from(titleList);
    }

    public static Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("subscriber1 completed");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onNext(Integer integer) {
                subscriber1 += integer;
            }
        };
    }

    public static Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("subscriber2 completed");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onNext(Integer integer) {
                subscriber2 += integer;
            }
        };
    }
}
