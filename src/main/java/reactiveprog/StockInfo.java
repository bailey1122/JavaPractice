package reactiveprog;

// pojo
public class StockInfo {

    public String ticker;
    public double value;

    public StockInfo(String ticker, final double value) {
        this.ticker = ticker;
        this.value = value;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "ticker='" + ticker + '\'' +
                ", value=" + value +
                '}';
    }
}
