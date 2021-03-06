package reactiveprog;

import rx.Observable;
import rx.Subscriber;

import java.util.List;

public class StockServer2 {

    public static Observable<Integer> getData() {
        return Observable.create(subscriber -> processRequest2(subscriber)); // start sending some data to a subscriber
    }

    private static void processRequest2(Subscriber<? super Integer> subscriber) {
        int count = 0;

        while (true) {
            subscriber.onNext(count++);
            sleep(1000);
        }
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Observable<StockInfo> getFeed(List<String> symbols) {

        // Observable will process the request as soon as we connect and subscribe to it. When the subscriber comes in,
        // Observable will do all work
        return Observable.create(subscriber -> processRequest(subscriber, symbols));
    }

    // we can manage how the subscription happens
    public static void processRequest(Subscriber<? super StockInfo> subscriber, List<String> symbols) {
        System.out.println("processing..."); // processing the request

//        // constantly sending data to the data channel
//        while (true) {
//        symbols.stream()
//                .map(StockFetcher::fetch) // OnErrorNotImplementedException because we haven't opened the error channel
//                .forEach(subscriber::onNext);
//        }

        while (!subscriber.isUnsubscribed()) { // allow sending data
            symbols.stream()
                    .map(StockFetcher::fetch)
                    .forEach(subscriber::onNext);
        }

//        int count = 0;
//        while (count++ < 3) {
//            symbols.stream()
//                    .map(StockFetcher::fetch2)
//                    .forEach(subscriber::onNext);
//        }
//
//        subscriber.onCompleted();
//        subscriber.onNext(new StockInfo("smth", 0.0));

//        while (true) {
//            for (String symbol : symbols) {
//                // catch the stock for the symbol
//                subscriber.onNext(StockFetcher.fetch2(symbol)); // onNext is the data channel
//            }
//        }
    }
}
