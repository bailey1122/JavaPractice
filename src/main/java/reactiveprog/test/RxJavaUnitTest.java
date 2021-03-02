package reactiveprog.test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.Test;
import reactiveprog.StockInfo;
import reactiveprog.StockServer;

import java.util.Arrays;
import java.util.List;

// events are data and data are events
public class RxJavaUnitTest {
    String result = "";

    // subscription to a fix value
    @Test
    public void returnAValue() {
        result = "";
        Observable<String> observable = Observable.just("Smth");
        observable.subscribe(s -> result = s);
        assert(result.equals("Smth"));
    }

    @Test
    public void expectRE() {
        List<String> symbols = Arrays.asList("GOOG", "AAPL", "MSFT", "INTC");
        Observable<StockInfo> observable = StockServer.getFeed(symbols);

        TestObserver<StockInfo> testObserver = new TestObserver<StockInfo>();
        observable.subscribeWith(testObserver);

        testObserver.assertError(RuntimeException.class);
    }
}
