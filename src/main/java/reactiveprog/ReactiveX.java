package reactiveprog;

import io.reactivex.*;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// observables representing sources of data.
// Subscribers (or observers) listening to the observables.
// A set of methods for modifying and composing the data.
// An observable emits items; a subscriber consumes those items
public class ReactiveX {
    private static String res = "";

    public static void main(String[] args) {
        List<String> symbols = Arrays.asList("GOOG", "AAPL", "MSFT", "INTC");

        Observable<Integer> o = Observable.just(1, 2, 3, 4, 5)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NotNull Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                });
        o.subscribe(System.out::println);


        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        };

        Observable<String> observer = Observable.just("Hello"); // provides data
        observer.subscribe(s -> res = s); // callable as subscriber

        System.out.println(res);

        // the usage of Flowable, is when you process touch events. You cannot control the user who is doing these touch
        // events, but you can tell the source to emit the events on a slower rate in case you cannot process them at the rate
        // the user produces them

        // the creation of an observable. It emits 0 or n items and terminates with an success or an error event
        Observable<StockInfo> observable = Observable.create(new ObservableOnSubscribe<StockInfo>() {
            @Override
            public void subscribe(ObservableEmitter<StockInfo> emitter) throws Exception {
                try {
                    symbols.stream()
                            .map(StockFetcher::fetch2) // get the stock price
                            // onNext() is called on our observer each time a new event is published to the attached Observable.
                            // This is the method where we'll perform some action on each event
                            .forEach(emitter::onNext);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
        System.out.println("Regular Observable");
        observable.subscribe(System.out::println);

        System.out.println();

        // using lamdas
        Observable<StockInfo> observableLam = Observable.create(emitter -> {
            try {
                symbols.stream()
                        .map(StockFetcher::fetch2)
                        .forEach(emitter::onNext);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        System.out.println("Lamda Observable");


        // subscribing in RxJava

        // to receive the data emitted from an observable we need to subscribe to it
        observable.subscribe(System.out::println);

        // subscribe with a io.reactivex.functions.Consumer<T>, which will be informed onNext()
        Disposable disposable = observable.subscribe(t -> System.out.println(t));
        disposable.dispose(); // dispose the subscription when not interested in the emitted data any more

        // handle the error case with a second io.reactivex.functions.Consumer<T>
        Disposable subscribe = observable.subscribe(t -> System.out.println(t), e -> e.printStackTrace());


        // disposing subscriptions and using CompositeDisposable

        // due to some state change the event being emitted by an observable might be not interesting anymore
        Single<List<StockInfo>> listSingle = (Single<List<StockInfo>>) symbols;

        Disposable disposable1 = listSingle.subscribeWith(new DisposableSingleObserver<List<StockInfo>>() {

            @Override
            public void onSuccess(@NotNull List<StockInfo> stocks) {
                // work
                stocks.stream()
                        .forEach(System.out::println);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                // handle the error case
                System.out.println(throwable);
            }
        });

        // continue working and dispose when value of the Single is not interesting any more
        disposable1.dispose();

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Single<List<StockInfo>> listSingle1 = (Single<List<StockInfo>>) symbols;
        Single<List<StockInfo>> listSinglesymb = (Single<List<StockInfo>>) symbols;

        compositeDisposable.add(listSingle.subscribeWith(new DisposableSingleObserver<List<StockInfo>>() {

            @Override
            public void onSuccess(@NotNull List<StockInfo> stocks) {
                // work
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                // handle the error case
            }
        }));
        compositeDisposable.add(listSinglesymb.subscribeWith(new DisposableSingleObserver<List<StockInfo>>() {

            @Override
            public void onSuccess(@NotNull List<StockInfo> stocks) {
                // work
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                // handle the error case
            }
        }));
        // continue working and dispose all subscriptions when the values from the Single objects are not interesting any more
        compositeDisposable.dispose();


        // caching values of completed observables

        // sometimes async calls on every subscription on an observable is not necessary
        Single<StockInfo> listSingle2 = Single.create(emitter -> {
            Thread thread = new Thread(() -> {
                try {
                    symbols.stream()
                            .map(StockFetcher::fetch) // get the stock price
                            .forEach(emitter::onSuccess); // data channel through which the data is sent

                } catch (Exception e) {
                    emitter.onError(e);
                }
            });
            thread.start();
        });

        // cache the result of the single, so that the web query is only done once
        Single<StockInfo> cachedSingle = listSingle2.cache();

        listSingle2.subscribe();
        // do smth and pass cachedSingle
        listSingle2.subscribe();
        // do smth and pass cachedSingle


        System.out.println();

        // the creation of a Maybe using lamdas. It succeeds with an item, or no item, or errors. The reactive version
        // of an Optional
        Maybe<StockInfo> maybe = Maybe.create(emitter -> {
            try {
                if (symbols != null && !symbols.isEmpty()) {
                    symbols.stream()
                            .map(StockFetcher::fetch2)
                            .forEach(emitter::onSuccess); // has a value
                } else {
                    emitter.onComplete(); // contains no value
                }
            } catch (Exception e) {
                emitter.onError(e); // an error occurred. onError() is called when an unhandled exception is thrown during
                // the RxJava framework code or our event handling code
            }
        });
        System.out.println("Maybe");
        maybe.subscribe(System.out::println);

        System.out.println();

        String[] arr = {"A", "B", "C"};

        // methods to create observables
        Observable<String> ob1 = Observable.just("Hello"); // create an observable as wrapper around other data types
//        Observable<> ob2 = Observable.fromIterable(arg); // emits their values in their order in the data structure
        Observable<String> ob3 = Observable.fromArray(arr); // takes an array and emits their values in their order in the data structure
//        Observable<> ob4 = Observable.fromCallable(T); // allows to create an observable for a Callable
//        Observable<> ob5 = Observable.fromFuture(T); // allows to create an observable for a Future
//        Observable<Long> ob6 = Observable.interval(..); // an observable that emits Long objects in a given interval
    }
}
