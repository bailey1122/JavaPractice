package reactiveprog;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// subscriber

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
    // then (the number of cores / (1 - 0.5) = 2 * the number of cores). Blocking factor is between 0 and less than 1
    public static void main(String[] args) throws InterruptedException {
        List<String> symbols = Arrays.asList("GOOG", "AAPL", "MSFT", "INTC");

        Iterator<String> iterator = symbols.iterator();
        while (iterator.hasNext()) {
            String data = iterator.next();
        }

        Observable<StockInfo> feed = StockServer2.getFeed(symbols); // it's lazy so it's not doing any work until subscribing
        System.out.println("got observable");

        feed.subscribeOn(Schedulers.io())
                .subscribe(ObservableEx::printStockInfo);

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

    public static void printStockInfo(StockInfo stockInfo) {
        System.out.println("Thread: " + Thread.currentThread());
        System.out.println(stockInfo);
    }
}
