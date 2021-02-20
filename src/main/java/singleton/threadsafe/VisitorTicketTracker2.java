package singleton.threadsafe;

// thread-safe only one thread will be
// allowed in the method at a time, ensuring that only one object is created
public class VisitorTicketTracker2 {

    private static VisitorTicketTracker2 instance;

    private VisitorTicketTracker2(){
    }

    public static synchronized VisitorTicketTracker2 getInstance() {
        if (instance == null) {
            instance = new VisitorTicketTracker2(); // not thread-safe
        }
        return instance;
    }

    // data access methods
}
