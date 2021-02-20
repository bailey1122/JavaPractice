package singleton;

// a singleton using a static initialization block when
// the class is loaded
public class StaffRegister {

    private static final StaffRegister instance;
    static {
        instance = new StaffRegister();
        // preform additional steps
    }

    private StaffRegister() {
    }

    public static StaffRegister getInstance() {
        return instance;
    }

    // data access methods
}
