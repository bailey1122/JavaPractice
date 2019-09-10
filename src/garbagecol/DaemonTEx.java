package garbagecol;

public class DaemonTEx extends Thread {
    public DaemonTEx(String name) {
        super(name);
    }

    public void run() {
        // checking whether the thread is Daemon or not
        if (Thread.currentThread().isDaemon()) {
            System.out.println(getName() + " is Daemon thread");
        }

        else {
            System.out.println(getName() + " is User thread");
        }
    }

    public static void main(String[] args) {
        DaemonTEx t1 = new DaemonTEx("t1");
        DaemonTEx t2 = new DaemonTEx("t2");
        DaemonTEx t3 = new DaemonTEx("t3");

        t1.setDaemon(true); // set to Daemon

        t1.start();
        t2.start();

        t3.setDaemon(true);
        t3.start();

//        t3.setDaemon(true); // get Exception 'cause that's already started
    }
}
