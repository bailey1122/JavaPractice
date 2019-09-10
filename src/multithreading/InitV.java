package multithreading;

// returns the current thread's initial value for this thread-local variable
class IVThread extends Thread {
    private static ThreadLocal tl = new ThreadLocal() { // declare private static
        protected Object initialValue() {
            return new Integer(question--);
        }
    };

    private static int question = 15;
    IVThread(String name) {
        super(name);
        start();
    }

    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println(getName() + " " + tl.get());
        }
    }
}

public class InitV {
    public static void main(String[] args) {
        IVThread t = new IVThread("first");
        IVThread t2 = new IVThread("second");
    }
}
