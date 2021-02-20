package reactiveprog;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Observable is a data stream (data flow). Push data to the client when data is ready. May be synchronous and asynchronous.
// It generates data and it can send a signal end of data stream. Propagate error. It has the data channel and the error channel.
// The data channel gets closed the minute we have an error close signal
public class ObservableEx {

    public static void main(String[] args) {
        List<String> symbols = Arrays.asList("GOOG", "AAPL", "MSFT", "INTC");

        Iterator<String> iterator = symbols.iterator();
        while (iterator.hasNext()) {
            String data = iterator.next();
        }

        StockServer.getFeed(symbols)
    }
}
