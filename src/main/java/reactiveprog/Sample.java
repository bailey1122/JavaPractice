package reactiveprog;

import io.reactivex.Observable;

import java.util.Arrays;

// reactive programming uses a functional pipeline. It give a lazy evaluation and a pipeline
// efficiency is attained not by doing tasks faster, but by avoiding those that shouldn't be done in the first place
// exceptions are perfectly normal to deal with in reactive programming. Observable doesn't deal with back pressure, whereas
// the flow does. If the source is sending the data at a rate faster than we can handle, we can buffer it and pick it up, and run
// with it
public class Sample {

    // emitting the data
    public static void main(String[] args) {
        String[] symbols = {"A", "B", "C"};

        // Observable has three channels (the data channel, the error channel, and the complete channel
        Observable<StockInfo> feed = StockServer.getFeed(Arrays.asList(symbols));
        System.out.println("got observable...");


        // data channel. Become the observer to the observable. We can communicate from the observable down to the observer
        // that we don't have any data and vice versa that we don't want more data by unsubscribing the description to do that
        // call a method from here and say unsubscribe or dispose the resource
        feed // observable
                // we can filter the data by putting intermediaries between observable above and observer below. Observers to
                // the upstream observable and become observable to downstream observers. We can be an observer of what's coming
                // up here and we can be an observable of what's down here
                .filter(stockInfo -> stockInfo.value > 1) // send it down if the condition is satisfied not otherwise. Get
                // the area we're interested in

                // when a service fails, we'd like to switch to another service. We can provide a backup service to switch over.
                // As the various flowing through, keep sending it. If the source fails, the take care of the failure and start
                // generating the new data
                .subscribe( // observer
                // the three channels come from the observable
                stockInfo -> System.out.println(stockInfo), // the data channel
//                System.out::println, // the error channel. The minute an error comes up, the data channel stops releasing any data
                err -> System.out.println("ERROR: " + err), // the error channel. Print the error
                // when the observable sends the complete signal, we can get the data here on the other side. We'll output
                // done and take a look at the output signal
                () -> System.out.println("DONE") // the complete channel
        );
    }
}
