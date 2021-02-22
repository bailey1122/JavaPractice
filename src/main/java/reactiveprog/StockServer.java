package reactiveprog;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.util.List;

public class StockServer {

    public static Observable<StockInfo> getFeed(List<String> symbols) {
        System.out.println("created...");

//        return null;
        // the method is not called because it's lazy. It will start processing a request as soon as an emitter connects
        // to observable
        return Observable.create(emitter -> emitPrice(emitter, symbols));
    }

    // ask it to do some work
    private static void emitPrice(ObservableEmitter<StockInfo> emitter, List<String> symbols) {
        System.out.println("Ready to emit...");

        int count = 0;

        while (count < 5) {
            symbols.stream()
                    .map(StockFetcher::fetch) // get the stock price
                    .forEach(emitter::onNext); // data channel through which the data is sent

            // take a break sleep
            sleep(1000);
            count++;
        }

        // terminates the data channel
        emitter.onComplete(); // stop producing any data

        // the minute complete channel sends a signal, the data channel slows us up which means the following line is not
        // sending anything to the observer
        emitter.onNext(new StockInfo("smth", 0.0));
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
