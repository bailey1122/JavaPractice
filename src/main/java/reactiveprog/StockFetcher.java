package reactiveprog;

public class StockFetcher {

    public static StockInfo fetch(String ticker) {
        if (Math.random() > 0.9) throw new RuntimeException("smth just happened...");

        return new StockInfo("Price", 2);
    }

    public static StockInfo fetch2(String ticker) {
        return new StockInfo("Price", 2);
    }
}
