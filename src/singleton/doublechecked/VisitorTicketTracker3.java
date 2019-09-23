package singleton.doublechecked;

// a singleton class with Double‐Checked Locking
public class VisitorTicketTracker3 {

    private static volatile VisitorTicketTracker3 instance;

    private VisitorTicketTracker3(){
    }

    public static VisitorTicketTracker3 getInstance() {
        if (instance == null) {
            synchronized (VisitorTicketTracker3.class) { // we first test if synchronization is
                if (instance == null) { // needed before actually acquiring any locks.
                    instance = new VisitorTicketTracker3(); // not thread-safe
                }
            }
        }
        return instance;
    }

    // data access methods
}
