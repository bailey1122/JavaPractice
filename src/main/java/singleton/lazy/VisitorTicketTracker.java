package singleton.lazy;

// Lazy instantiation(not thread-safe). It improves performance and reduces memory usage. Delay creation of the singleton until
// the first time the getInstance() method is called
public class VisitorTicketTracker {

    private static VisitorTicketTracker instance;

    private VisitorTicketTracker(){
    }

    public static VisitorTicketTracker getInstance() {
        if (instance == null) {
            instance = new VisitorTicketTracker(); // not thread-safe
        }
        return instance;
    }

    // data access methods
}
