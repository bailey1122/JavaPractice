package multithreading;

// Visibility
public class LoopMayNeverEnd {
    // to ensure this doesn't happen use 'volatile'
    volatile boolean done = false; // synchronization between two threads

    void work() {
        while (!done) {
            // do work
        }
    }

    void stopWork() {
        done = true;
    }
}
