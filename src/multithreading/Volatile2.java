package multithreading;

// volatile
public class Volatile2 {
    static int sharedVar = 6; // changes made to this variable in one thread may not immediately reflect in other thread

    // don't confuse volatile with static
    static volatile int sharedV = 6; // changes made in one thread are immediately reflect in other thread

    // Difference.
    // (Synchronized word) locks and synchronization provides 1. Mutual Exclusion(execute a block of code at a time)
    // 2. Visibility (changes made by one thread to shared data are visible to other threads)

    // Volatile provides Visibility only
}
