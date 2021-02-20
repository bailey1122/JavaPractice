package reactiveprog;

public class StockInfo {

    public String ticker;
    public double value;

    public StockInfo() {
    }

    public StockInfo(String ticker, double value) {
        this.ticker = ticker;
        this.value = value;
    }

    public static StockInfo fetch(String ticker) {
        if (Math.random() > 0.9) throw new RuntimeException("smth just happened...");

        return new StockInfo("Price", 2);
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "ticker='" + ticker + '\'' +
                ", value=" + value +
                '}';
    }
}
