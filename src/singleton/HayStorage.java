package singleton;

// a singleton class at the time the class is loaded
public class HayStorage {

    private int quantity = 0;

    // the singleton class is effectively final. We cannot create a subclass with a valid constructor
    private HayStorage() {} // by marking the constructors private, we have implicitly marked the class final

    private static final HayStorage instance = new HayStorage();

    public static HayStorage getInstance() {
        return instance;
    }

    public synchronized void addHay(int amount) {
        quantity += amount;
    }

    public synchronized boolean removeHay(int amount) {
        if (quantity < amount) return false;
        quantity -= amount;
        return true;
    }

    public synchronized int getHayQuantity() {
        return quantity;
    }
}
