package multithreading;

public class BadlyOrdered {
    // declare volatile to get the ordering you want.
    // Using volatile means that the changes made in one
    // thread are immediately reflect on other thread
    volatile boolean a = false;
    volatile boolean b = false;

    void threadOne() {
        a = true;
        b = true;
    }

    boolean threadTwo() {
        boolean r1 = b; // sees true
        boolean r2 = a; // sees false
        boolean r3 = a; // sees true
        return (r1 && !r2) && r3; // return true
    }
}
